package university.utils;

import university.models.Student;
import university.models.Professor;
import university.models.Course;
import university.models.Department;
import university.models.Assessment;
import university.models.Exam;
import university.models.Assignment;
import university.models.Project;
import university.models.Payment;
import university.services.StudentService;
import university.services.CourseService;
import university.services.EnrollmentService;
import university.services.PaymentService;
import university.services.ReportService;
import university.exceptions.StudentNotFoundException;
import university.exceptions.InvalidPaymentException;
import university.exceptions.InvalidGradeException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ConsoleUI — Part 6 (Main System & Integration)
 *
 * Responsibilities:
 *   - Display all menus.
 *   - Read user input via InputValidator.
 *   - Delegate ALL logic to the appropriate Service.
 *   - Handle navigation between menus.
 *
 * ConsoleUI does NOT store any data itself.
 * ConsoleUI does NOT contain any business logic.
 */
public class ConsoleUI {

    // ── Services (injected from Main) ────────────────────────────────────
    private final StudentService    studentService;
    private final CourseService     courseService;
    private final EnrollmentService enrollmentService;
    private final PaymentService    paymentService;
    private final ReportService     reportService;

    // ── Input handling ───────────────────────────────────────────────────
    private final Scanner       scanner;
    private final InputValidator input;

    // ── Constructor ──────────────────────────────────────────────────────
    public ConsoleUI(StudentService    studentService,
                     CourseService     courseService,
                     EnrollmentService enrollmentService,
                     PaymentService    paymentService,
                     ReportService     reportService) {

        this.studentService    = studentService;
        this.courseService     = courseService;
        this.enrollmentService = enrollmentService;
        this.paymentService    = paymentService;
        this.reportService     = reportService;

        this.scanner = new Scanner(System.in);
        this.input   = new InputValidator(scanner);
    }

    // ════════════════════════════════════════════════════════════════════
    //  MAIN MENU
    // ════════════════════════════════════════════════════════════════════
    public void start() {
        printBanner();
        boolean running = true;

        while (running) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║       MAIN MENU — SUMS               ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Student Management               ║");
            System.out.println("║  2. Professor Management             ║");
            System.out.println("║  3. Course Management                ║");
            System.out.println("║  4. Registration                     ║");
            System.out.println("║  5. Grades                           ║");
            System.out.println("║  6. Payments                         ║");
            System.out.println("║  7. Reports                          ║");
            System.out.println("║  8. Exit                             ║");
            System.out.println("╚══════════════════════════════════════╝");

            int choice = input.readInt("Choose an option: ");

            switch (choice) {
                case 1: menuStudentManagement();  break;
                case 2: menuProfessorManagement(); break;
                case 3: menuCourseManagement();    break;
                case 4: menuRegistration();        break;
                case 5: menuGrades();              break;
                case 6: menuPayments();            break;
                case 7: menuReports();             break;
                case 8:
                    System.out.println("\nGoodbye! See you next time.");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("-> Invalid option. Please choose 1–8.");
            }
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  1. STUDENT MANAGEMENT
    // ════════════════════════════════════════════════════════════════════
    private void menuStudentManagement() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display Students");
            System.out.println("6. Back");

            int choice = input.readInt("Choose an option: ");
            switch (choice) {
                case 1: doAddStudent();     break;
                case 2: doSearchStudent();  break;
                case 3: doUpdateStudent();  break;
                case 4: doDeleteStudent();  break;
                case 5: doDisplayStudents(); break;
                case 6: back = true;        break;
                default: System.out.println("-> Invalid option.");
            }
        }
    }

    private void doAddStudent() {
        System.out.println("\n-- Add Student --");
        String id           = input.readNonEmptyString("Student ID   : ");
        String name         = input.readNonEmptyString("Full Name    : ");
        String email        = input.readNonEmptyString("Email        : ");
        String phone        = input.readNonEmptyString("Phone        : ");
        String major        = input.readNonEmptyString("Major        : ");
        double gpa          = input.readDouble("GPA (0.0–4.0): ");
        int    level        = input.readInt("Academic Level (1–4): ");

        Student student = new Student(id, name, email, phone, major, gpa, level);
        studentService.addStudent(student);
        System.out.println("-> Student added successfully.");
    }

    private void doSearchStudent() {
        System.out.println("\n-- Search Student --");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        int choice = input.readInt("Choose: ");

        if (choice == 1) {
            String id = input.readNonEmptyString("Enter Student ID: ");
            try {
                Student s = studentService.searchStudent(id);
                if (s != null) s.printDetails();
                else System.out.println("-> No student found with ID: " + id);
            } catch (StudentNotFoundException e) {
                System.out.println("-> " + e.getMessage());
            }
        } else if (choice == 2) {
            String name = input.readNonEmptyString("Enter Student Name: ");
            try {
                Student s = studentService.searchStudent(name, true);
                if (s != null) s.printDetails();
                else System.out.println("-> No student found with name: " + name);
            } catch (StudentNotFoundException e) {
                System.out.println("-> " + e.getMessage());
            }
        } else {
            System.out.println("-> Invalid option.");
        }
    }

    private void doUpdateStudent() {
        System.out.println("\n-- Update Student --");
        String id = input.readNonEmptyString("Enter Student ID to update: ");
        try {
            Student s = studentService.searchStudent(id);
            if (s == null) { System.out.println("-> Student not found."); return; }

            System.out.println("Leave field blank to keep current value.");
            String name  = input.readString("New Name  (current: " + s.getName()  + "): ");
            String email = input.readString("New Email (current: " + s.getEmail() + "): ");
            String phone = input.readString("New Phone (current: " + s.getPhone() + "): ");
            String major = input.readString("New Major (current: " + s.getMajor() + "): ");

            if (!name.isEmpty())  s.setName(name);
            if (!email.isEmpty()) s.setEmail(email);
            if (!phone.isEmpty()) s.setPhone(phone);
            if (!major.isEmpty()) s.setMajor(major);

            System.out.println("-> Student updated successfully.");
            s.printDetails();
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    private void doDeleteStudent() {
        System.out.println("\n-- Delete Student --");
        String id = input.readNonEmptyString("Enter Student ID to delete: ");
        boolean confirm = input.readBoolean("Are you sure you want to delete student " + id + "?");
        if (confirm) {
            studentService.deleteStudent(id);
            System.out.println("-> Student deleted.");
        } else {
            System.out.println("-> Delete cancelled.");
        }
    }

    private void doDisplayStudents() {
        System.out.println("\n-- All Students --");
        ArrayList<Student> list = studentService.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("-> No students in the system yet.");
        } else {
            studentService.displayStudents();
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  2. PROFESSOR MANAGEMENT
    // ════════════════════════════════════════════════════════════════════
    private void menuProfessorManagement() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Professor Management ---");
            System.out.println("1. Add Professor");
            System.out.println("2. Search Professor");
            System.out.println("3. Update Professor");
            System.out.println("4. Delete Professor");
            System.out.println("5. Display Professors");
            System.out.println("6. Back");

            int choice = input.readInt("Choose an option: ");
            switch (choice) {
                case 1: doAddProfessor();      break;
                case 2: doSearchProfessor();   break;
                case 3: doUpdateProfessor();   break;
                case 4: doDeleteProfessor();   break;
                case 5: doDisplayProfessors(); break;
                case 6: back = true;           break;
                default: System.out.println("-> Invalid option.");
            }
        }
    }

    private void doAddProfessor() {
        System.out.println("\n-- Add Professor --");
        String id             = input.readNonEmptyString("Professor ID      : ");
        String name           = input.readNonEmptyString("Full Name         : ");
        String email          = input.readNonEmptyString("Email             : ");
        String phone          = input.readNonEmptyString("Phone             : ");
        String specialization = input.readNonEmptyString("Specialization    : ");
        String deptId         = input.readNonEmptyString("Department ID     : ");
        String deptName       = input.readNonEmptyString("Department Name   : ");
        String deptDesc       = input.readNonEmptyString("Department Desc   : ");

        Department dept = new Department(deptId, deptName, deptDesc);
        Professor prof  = new Professor(id, name, email, phone, specialization, dept);
        studentService.addProfessor(prof);
        System.out.println("-> Professor added successfully.");
    }

    private void doSearchProfessor() {
        System.out.println("\n-- Search Professor --");
        String id = input.readNonEmptyString("Enter Professor ID: ");
        Professor p = studentService.searchProfessor(id);
        if (p != null) p.printDetails();
        else System.out.println("-> No professor found with ID: " + id);
    }

    private void doUpdateProfessor() {
        System.out.println("\n-- Update Professor --");
        String id = input.readNonEmptyString("Enter Professor ID to update: ");
        Professor p = studentService.searchProfessor(id);
        if (p == null) { System.out.println("-> Professor not found."); return; }

        System.out.println("Leave field blank to keep current value.");
        String name  = input.readString("New Name  (current: " + p.getName()  + "): ");
        String email = input.readString("New Email (current: " + p.getEmail() + "): ");
        String phone = input.readString("New Phone (current: " + p.getPhone() + "): ");
        String spec  = input.readString("New Spec  (current: " + p.getSpecialization() + "): ");

        if (!name.isEmpty())  p.setName(name);
        if (!email.isEmpty()) p.setEmail(email);
        if (!phone.isEmpty()) p.setPhone(phone);
        if (!spec.isEmpty())  p.setSpecialization(spec);

        System.out.println("-> Professor updated.");
        p.printDetails();
    }

    private void doDeleteProfessor() {
        System.out.println("\n-- Delete Professor --");
        String id = input.readNonEmptyString("Enter Professor ID to delete: ");
        boolean confirm = input.readBoolean("Are you sure?");
        if (confirm) {
            studentService.deleteProfessor(id);
            System.out.println("-> Professor deleted.");
        } else {
            System.out.println("-> Delete cancelled.");
        }
    }

    private void doDisplayProfessors() {
        System.out.println("\n-- All Professors --");
        studentService.displayProfessors();
    }

    // ════════════════════════════════════════════════════════════════════
    //  3. COURSE MANAGEMENT
    // ════════════════════════════════════════════════════════════════════
    private void menuCourseManagement() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. Search Course");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("5. Display Courses");
            System.out.println("6. Back");

            int choice = input.readInt("Choose an option: ");
            switch (choice) {
                case 1: doAddCourse();     break;
                case 2: doSearchCourse();  break;
                case 3: doUpdateCourse();  break;
                case 4: doDeleteCourse();  break;
                case 5: doDisplayCourses(); break;
                case 6: back = true;       break;
                default: System.out.println("-> Invalid option.");
            }
        }
    }

    private void doAddCourse() {
        System.out.println("\n-- Add Course --");
        String courseId   = input.readNonEmptyString("Course ID        : ");
        String courseName = input.readNonEmptyString("Course Name      : ");
        int    credits    = input.readInt("Credit Hours     : ");
        int    maxStudents = input.readInt("Max Students     : ");
        String profId     = input.readNonEmptyString("Professor ID     : ");
        String deptId     = input.readNonEmptyString("Department ID    : ");
        String deptName   = input.readNonEmptyString("Department Name  : ");
        String deptDesc   = input.readNonEmptyString("Department Desc  : ");

        Professor prof = studentService.searchProfessor(profId);
        if (prof == null) {
            System.out.println("-> Professor not found. Add the professor first.");
            return;
        }
        Department dept = new Department(deptId, deptName, deptDesc);
        Course course   = new Course(courseId, courseName, credits, maxStudents, prof, dept);
        courseService.addCourse(course);
        System.out.println("-> Course added successfully.");
    }

    private void doSearchCourse() {
        System.out.println("\n-- Search Course --");
        System.out.println("1. Search by Course ID");
        System.out.println("2. Search by Course Name");
        int choice = input.readInt("Choose: ");

        if (choice == 1) {
            String id = input.readNonEmptyString("Enter Course ID: ");
            Course c = courseService.searchCourse(id);
            if (c != null) c.printDetails();
            else System.out.println("-> No course found with ID: " + id);
        } else if (choice == 2) {
            String name = input.readNonEmptyString("Enter Course Name: ");
            Course c = courseService.searchCourse(name, true);
            if (c != null) c.printDetails();
            else System.out.println("-> No course found with name: " + name);
        } else {
            System.out.println("-> Invalid option.");
        }
    }

    private void doUpdateCourse() {
        System.out.println("\n-- Update Course --");
        String id = input.readNonEmptyString("Enter Course ID to update: ");
        Course c = courseService.searchCourse(id);
        if (c == null) { System.out.println("-> Course not found."); return; }

        System.out.println("Leave blank to keep current value.");
        String newName = input.readString("New Course Name (current: " + c.getCourseName() + "): ");
        if (!newName.isEmpty()) c.setCourseName(newName);

        System.out.println("-> Course updated.");
        c.printDetails();
    }

    private void doDeleteCourse() {
        System.out.println("\n-- Delete Course --");
        String id = input.readNonEmptyString("Enter Course ID to delete: ");
        boolean confirm = input.readBoolean("Are you sure?");
        if (confirm) {
            courseService.deleteCourse(id);
            System.out.println("-> Course deleted.");
        } else {
            System.out.println("-> Delete cancelled.");
        }
    }

    private void doDisplayCourses() {
        System.out.println("\n-- All Courses --");
        courseService.displayCourses();
    }

    // ════════════════════════════════════════════════════════════════════
    //  4. REGISTRATION
    // ════════════════════════════════════════════════════════════════════
    private void menuRegistration() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Registration ---");
            System.out.println("1. Register Student in Course");
            System.out.println("2. Drop Course");
            System.out.println("3. View Student Courses");
            System.out.println("4. View Course Students");
            System.out.println("5. Back");

            int choice = input.readInt("Choose an option: ");
            switch (choice) {
                case 1: doRegisterStudent();     break;
                case 2: doDropCourse();          break;
                case 3: doViewStudentCourses();  break;
                case 4: doViewCourseStudents();  break;
                case 5: back = true;             break;
                default: System.out.println("-> Invalid option.");
            }
        }
    }

    private void doRegisterStudent() {
        System.out.println("\n-- Register Student --");
        String studentId = input.readNonEmptyString("Student ID : ");
        String courseId  = input.readNonEmptyString("Course ID  : ");

        try {
            Student s = studentService.searchStudent(studentId);
            Course  c = courseService.searchCourse(courseId);

            if (s == null) { System.out.println("-> Student not found."); return; }
            if (c == null) { System.out.println("-> Course not found.");  return; }

            enrollmentService.registerStudent(s, c);
            System.out.println("-> " + s.getName() + " registered in " + c.getCourseName() + " successfully.");
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    private void doDropCourse() {
        System.out.println("\n-- Drop Course --");
        String studentId = input.readNonEmptyString("Student ID : ");
        String courseId  = input.readNonEmptyString("Course ID  : ");

        try {
            Student s = studentService.searchStudent(studentId);
            Course  c = courseService.searchCourse(courseId);

            if (s == null) { System.out.println("-> Student not found."); return; }
            if (c == null) { System.out.println("-> Course not found.");  return; }

            enrollmentService.dropCourse(s, c);
            System.out.println("-> Course dropped successfully.");
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    private void doViewStudentCourses() {
        System.out.println("\n-- View Student Courses --");
        String studentId = input.readNonEmptyString("Student ID: ");
        try {
            Student s = studentService.searchStudent(studentId);
            if (s == null) { System.out.println("-> Student not found."); return; }
            enrollmentService.displayStudentCourses(s);
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    private void doViewCourseStudents() {
        System.out.println("\n-- View Course Students --");
        String courseId = input.readNonEmptyString("Course ID: ");
        Course c = courseService.searchCourse(courseId);
        if (c == null) { System.out.println("-> Course not found."); return; }
        enrollmentService.displayCourseStudents(c);
    }

    // ════════════════════════════════════════════════════════════════════
    //  5. GRADES
    // ════════════════════════════════════════════════════════════════════
    private void menuGrades() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Grades ---");
            System.out.println("1. Add Assessment");
            System.out.println("2. Add Grade");
            System.out.println("3. View Grades");
            System.out.println("4. Calculate GPA");
            System.out.println("5. View Result");
            System.out.println("6. Back");

            int choice = input.readInt("Choose an option: ");
            switch (choice) {
                case 1: doAddAssessment(); break;
                case 2: doAddGrade();      break;
                case 3: doViewGrades();    break;
                case 4: doCalculateGPA();  break;
                case 5: doViewResult();    break;
                case 6: back = true;       break;
                default: System.out.println("-> Invalid option.");
            }
        }
    }

    private void doAddAssessment() {
        System.out.println("\n-- Add Assessment --");
        System.out.println("Assessment Type:");
        System.out.println("1. Exam");
        System.out.println("2. Assignment");
        System.out.println("3. Project");
        int type = input.readInt("Choose type: ");

        String aId     = input.readNonEmptyString("Assessment ID  : ");
        String title   = input.readNonEmptyString("Title          : ");
        double maxScore = input.readDouble("Max Score      : ");
        double weight   = input.readDouble("Weight (0–100) : ");
        String courseId = input.readNonEmptyString("Course ID      : ");

        Course c = courseService.searchCourse(courseId);
        if (c == null) { System.out.println("-> Course not found."); return; }

        Assessment assessment = null;
        try {
            switch (type) {
                case 1:
                    String examType = input.readNonEmptyString("Exam Type (Midterm/Final): ");
                    assessment = new Exam(aId, title, maxScore, weight, examType);
                    break;
                case 2:
                    String subDate = input.readNonEmptyString("Submission Date (e.g. 2025-05-01): ");
                    assessment = new Assignment(aId, title, maxScore, weight, subDate);
                    break;
                case 3:
                    String projType = input.readNonEmptyString("Project Type (Individual/Group): ");
                    assessment = new Project(aId, title, maxScore, weight, projType);
                    break;
                default:
                    System.out.println("-> Invalid assessment type.");
                    return;
            }
            reportService.addAssessment(c, assessment);
            System.out.println("-> Assessment added successfully.");
        } catch (InvalidGradeException e) {
            System.out.println("-> Error: " + e.getMessage());
        }
    }

    private void doAddGrade() {
        System.out.println("\n-- Add Grade --");
        String studentId    = input.readNonEmptyString("Student ID    : ");
        String courseId     = input.readNonEmptyString("Course ID     : ");
        String assessmentId = input.readNonEmptyString("Assessment ID : ");
        double score        = input.readDouble("Score         : ");

        try {
            Student    s = studentService.searchStudent(studentId);
            Course     c = courseService.searchCourse(courseId);
            Assessment a = reportService.findAssessment(c, assessmentId);

            if (s == null) { System.out.println("-> Student not found.");    return; }
            if (c == null) { System.out.println("-> Course not found.");     return; }
            if (a == null) { System.out.println("-> Assessment not found."); return; }

            reportService.addGrade(s, c, a, score);
            System.out.println("-> Grade added successfully.");
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        } catch (InvalidGradeException e) {
            System.out.println("-> Error: " + e.getMessage());
        }
    }

    private void doViewGrades() {
        System.out.println("\n-- View Grades --");
        String studentId = input.readNonEmptyString("Student ID: ");
        try {
            Student s = studentService.searchStudent(studentId);
            if (s == null) { System.out.println("-> Student not found."); return; }
            reportService.displayStudentResults(s);
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    private void doCalculateGPA() {
        System.out.println("\n-- Calculate GPA --");
        String studentId = input.readNonEmptyString("Student ID: ");
        try {
            Student s = studentService.searchStudent(studentId);
            if (s == null) { System.out.println("-> Student not found."); return; }
            double gpa = reportService.calculateGPA(s);
            System.out.printf("-> GPA for %s: %.2f%n", s.getName(), gpa);
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    private void doViewResult() {
        System.out.println("\n-- View Result --");
        String studentId = input.readNonEmptyString("Student ID: ");
        String courseId  = input.readNonEmptyString("Course ID : ");
        try {
            Student s = studentService.searchStudent(studentId);
            Course  c = courseService.searchCourse(courseId);
            if (s == null) { System.out.println("-> Student not found."); return; }
            if (c == null) { System.out.println("-> Course not found.");  return; }
            boolean passed = reportService.isPassed(s, c);
            double  pct    = reportService.calculateCoursePercentage(s, c);
            System.out.printf("-> %s in %s: %.1f%% — %s%n",
                    s.getName(), c.getCourseName(), pct, passed ? "PASSED" : "FAILED");
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  6. PAYMENTS
    // ════════════════════════════════════════════════════════════════════
    private void menuPayments() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Payments ---");
            System.out.println("1. Make Payment");
            System.out.println("2. View Payments");
            System.out.println("3. View Remaining Fees");
            System.out.println("4. Back");

            int choice = input.readInt("Choose an option: ");
            switch (choice) {
                case 1: doMakePayment();         break;
                case 2: doViewPayments();        break;
                case 3: doViewRemainingFees();   break;
                case 4: back = true;             break;
                default: System.out.println("-> Invalid option.");
            }
        }
    }

    private void doMakePayment() {
        System.out.println("\n-- Make Payment --");
        String studentId = input.readNonEmptyString("Student ID  : ");
        double amount    = input.readDouble("Amount      : ");

        try {
            Student s = studentService.searchStudent(studentId);
            if (s == null) { System.out.println("-> Student not found."); return; }

            // Create a payment record if the student doesn't have one yet
            Payment existing = paymentService.getPaymentByStudentId(studentId);
            if (existing == null) {
                double totalFees = input.readDouble("Total Fees (first payment): ");
                Payment payment  = new Payment(s, totalFees);
                paymentService.addPayment(payment);
            }

            paymentService.makePayment(studentId, amount);
            System.out.println("-> Payment of " + amount + " recorded successfully.");
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        } catch (InvalidPaymentException e) {
            System.out.println("-> Payment error: " + e.getMessage());
        }
    }

    private void doViewPayments() {
        System.out.println("\n-- All Payments --");
        paymentService.displayPayments();
    }

    private void doViewRemainingFees() {
        System.out.println("\n-- Remaining Fees --");
        String studentId = input.readNonEmptyString("Student ID: ");
        try {
            Student s = studentService.searchStudent(studentId);
            if (s == null) { System.out.println("-> Student not found."); return; }
            double remaining = paymentService.getRemainingFees(studentId);
            System.out.printf("-> Remaining fees for %s: %.2f%n", s.getName(), remaining);
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  7. REPORTS
    // ════════════════════════════════════════════════════════════════════
    private void menuReports() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Reports ---");
            System.out.println("1. Student Report");
            System.out.println("2. Course Report");
            System.out.println("3. Academic Report");
            System.out.println("4. Payment Report");
            System.out.println("5. Back");

            int choice = input.readInt("Choose an option: ");
            switch (choice) {
                case 1: doStudentReport();  break;
                case 2: doCourseReport();   break;
                case 3: doAcademicReport(); break;
                case 4: doPaymentReport();  break;
                case 5: back = true;        break;
                default: System.out.println("-> Invalid option.");
            }
        }
    }

    private void doStudentReport() {
        System.out.println("\n-- Student Report --");
        String studentId = input.readNonEmptyString("Student ID: ");
        try {
            Student s = studentService.searchStudent(studentId);
            if (s == null) { System.out.println("-> Student not found."); return; }
            s.printDetails();
            enrollmentService.displayStudentCourses(s);
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    private void doCourseReport() {
        System.out.println("\n-- Course Report --");
        String courseId = input.readNonEmptyString("Course ID: ");
        Course c = courseService.searchCourse(courseId);
        if (c == null) { System.out.println("-> Course not found."); return; }
        c.printDetails();
        enrollmentService.displayCourseStudents(c);
    }

    private void doAcademicReport() {
        System.out.println("\n-- Academic Report --");
        String studentId = input.readNonEmptyString("Student ID: ");
        try {
            Student s = studentService.searchStudent(studentId);
            if (s == null) { System.out.println("-> Student not found."); return; }
            reportService.displayStudentReport(s);
        } catch (StudentNotFoundException e) {
            System.out.println("-> " + e.getMessage());
        }
    }

    private void doPaymentReport() {
        System.out.println("\n-- Payment Report --");
        paymentService.displayPayments();
    }

    // ════════════════════════════════════════════════════════════════════
    //  HELPERS
    // ════════════════════════════════════════════════════════════════════
    private void printBanner() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Smart University Management System (SUMS)  ║");
        System.out.println("║              Welcome!                        ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
