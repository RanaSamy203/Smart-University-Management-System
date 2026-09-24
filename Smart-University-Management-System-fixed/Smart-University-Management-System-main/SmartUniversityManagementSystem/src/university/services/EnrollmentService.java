package university.services;
import java.time.LocalDate;
import java.util.ArrayList;
import university.models.Student;
import university.models.Course;
import university.models.Enrollment;

// This class manages all student registration operations.
public class EnrollmentService {

    // Stores all enrollment records.
    // Dropped enrollments are kept in the list.
    private ArrayList<Enrollment> enrollments;

    // Constructor creates an empty list of enrollments.
    public EnrollmentService() {
        enrollments = new ArrayList<>();
    }

    // Registers a student in a course.
    // Checks that the student and course exist,
    // prevents duplicate registration,
    // and checks if the course is full.
    public boolean registerStudent(Student student, Course course) {

        // Check if the Student object exists.
        if (student == null) {
            System.out.println("Student does not exist.");
            return false;
        }

        // Check if the Course object exists.
        if (course == null) {
            System.out.println("Course does not exist.");
            return false;
        }

        // Prevent the same student from registering
        // in the same course more than once.
        if (isStudentRegistered(student, course)) {
            System.out.println("Student is already registered in this course.");
            return false;
        }

        // Check if the course reached its maximum capacity.
        if (isCourseFull(course)) {
            System.out.println("Course is full.");
            return false;
        }

        // Create a new enrollment with REGISTERED status.
        Enrollment enrollment =
                new Enrollment(student, course, LocalDate.now().toString());

        // Add the new enrollment to the list.
        enrollments.add(enrollment);

        return true;
    }

    // Drops a student from a course.
    // The enrollment is NOT removed from the list.
    // Its status is changed to DROPPED.
    public boolean dropCourse(Student student, Course course) {

        // Find the enrollment for this student and course.
        Enrollment enrollment = findEnrollment(student, course);

        // If no enrollment was found, nothing can be dropped.
        if (enrollment == null) {
            System.out.println("Enrollment not found.");
            return false;
        }

        // Check if the student already dropped the course.
        if (enrollment.getStatus().equals("DROPPED")) {
            System.out.println("Student has already dropped this course.");
            return false;
        }

        // Change the status from REGISTERED to DROPPED.
        enrollment.setStatus("DROPPED");

        return true;
    }

    // Returns all courses currently registered by a student.
    // DROPPED courses are not included.
    public ArrayList<Course> getStudentCourses(Student student) {

        ArrayList<Course> courses = new ArrayList<>();

        // Check every enrollment in the list.
        for (Enrollment enrollment : enrollments) {

            // Add the course only if:
            // 1. It belongs to the given student.
            // 2. Its status is REGISTERED.
            if (enrollment.getStudent() == student &&
                    enrollment.getStatus().equals("REGISTERED")) {

                courses.add(enrollment.getCourse());
            }
        }

        return courses;
    }

    // Returns all students currently registered in a course.
    // DROPPED students are not included.
    public ArrayList<Student> getCourseStudents(Course course) {

        ArrayList<Student> students = new ArrayList<>();

        // Check every enrollment in the list.
        for (Enrollment enrollment : enrollments) {

            // Add the student only if:
            // 1. The enrollment belongs to the given course.
            // 2. Its status is REGISTERED.
            if (enrollment.getCourse() == course &&
                    enrollment.getStatus().equals("REGISTERED")) {

                students.add(enrollment.getStudent());
            }
        }

        return students;
    }

    // Searches for an enrollment for a specific student and course.
    // Returns the enrollment if found, otherwise returns null.
    public Enrollment findEnrollment(Student student, Course course) {

        // Search through all enrollment records.
        for (Enrollment enrollment : enrollments) {

            // Check if the enrollment belongs to the given
            // student and course.
            if (enrollment.getStudent() == student &&
                    enrollment.getCourse() == course) {

                return enrollment;
            }
        }

        // No matching enrollment was found.
        return null;
    }

    // Checks whether a student is currently registered in a course.
    // Returns true only when the status is REGISTERED.
    public boolean isStudentRegistered(Student student, Course course) {

        // Find the student's enrollment.
        Enrollment enrollment = findEnrollment(student, course);

        // If there is no enrollment, the student is not registered.
        if (enrollment == null) {
            return false;
        }

        // Return true only if the status is REGISTERED.
        return enrollment.getStatus().equals("REGISTERED");
    }

    // Checks whether a course has reached its maximum capacity.
    // Only REGISTERED students are counted.
    // DROPPED students do not count.
    public boolean isCourseFull(Course course) {

        int registeredStudents = 0;

        // Count all currently registered students in the course.
        for (Enrollment enrollment : enrollments) {

            if (enrollment.getCourse() == course &&
                    enrollment.getStatus().equals("REGISTERED")) {

                registeredStudents++;
            }
        }

        // Compare the number of registered students
        // with the course maximum capacity.
        return registeredStudents >= course.getMaximumStudents();
    }

    // Returns the complete list of enrollment records.
    // Includes both REGISTERED and DROPPED enrollments.
    public ArrayList<Enrollment> getAllEnrollments() {

        return enrollments;
    }

    // Displays all courses currently registered by a student.
    public void displayStudentCourses(Student student) {

        // Get only the student's currently registered courses.
        ArrayList<Course> courses = getStudentCourses(student);

        System.out.println("Courses registered by student:");

        // Display a message if the student has no registered courses.
        if (courses.isEmpty()) {
            System.out.println("No registered courses.");
            return;
        }

        // Display each registered course.
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // Displays all students currently registered in a course.
    public void displayCourseStudents(Course course) {

        // Get only the students currently registered in the course.
        ArrayList<Student> students = getCourseStudents(course);

        System.out.println("Students registered in course:");

        // Display a message if there are no registered students.
        if (students.isEmpty()) {
            System.out.println("No registered students.");
            return;
        }

        // Display each registered student.
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
