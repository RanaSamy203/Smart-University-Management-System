# Smart University Management System (SUMS)

## 📌 Project Description

Smart University Management System (SUMS) is a **Java Console Application** built to simulate the core operations of a small university. The system manages students, professors, employees, departments, courses, enrollments, assessments, grades, and payments — all through a menu-driven console interface.

The project was built as a team assignment to demonstrate practical understanding and application of **Object-Oriented Programming (OOP)** concepts covered in the course.

---

## 👥 Team Members

| Part | Name | Responsibility | LinkedIn |
|---|------|-----------------|----------|
| 4 | Khaled Taha | Grades & Assessments (Assessment, Exam, Assignment, Project, Grade, ReportService) | [linkedin.com/in/khaled-taha-0a2a41389](https://www.linkedin.com/in/khaled-taha-0a2a41389) |
| 6 | Menna Mostafa | Main System & Integration (Main, ConsoleUI, InputValidator) | [linkedin.com/in/menna-m0stafa](https://www.linkedin.com/in/menna-m0stafa) |
| 5 | Hager Sayed | Payments & Interfaces (Payment, PaymentService, Payable, Printable, Searchable) | [linkedin.com/in/hager-sayed-a81244338](https://www.linkedin.com/in/hager-sayed-a81244338) |
| 3 | Nada Salah | Student Registration (Enrollment, EnrollmentService) | [linkedin.com/in/nada-salah-177318377](https://www.linkedin.com/in/nada-salah-177318377) |
| 1 | Rana Samy | People Management (Person, Student, Professor, Employee) | [linkedin.com/in/rana-samy20](https://www.linkedin.com/in/rana-samy20) |
| 2 | Taha Ayman | Courses & Departments (Department, Course, CourseService) | [linkedin.com/in/taha-ayman-s](https://www.linkedin.com/in/taha-ayman-s) |

---

## ✨ Features

- **Student Management** — Add, search, update, delete, and display students.
- **Professor Management** — Add, search, update, delete, and display professors. (`Employee` exists as a model but is not wired into the menus yet.)
- **Course Management** — Add, search (by ID or name), update, delete, and display courses.
- **Departments** — Each professor/course is linked to a department (entered inline; no standalone department menu yet).
- **Student Registration** — Register/drop courses with validation (no duplicate registration, no over-capacity registration).
- **Grades & Assessments** — Add exams, assignments, and projects; record grades; calculate percentages and pass/fail status.
- **GPA Calculation** — Automatically compute a student's overall GPA across all registered courses.
- **Payments** — Track tuition fees, record payments, and calculate remaining balances.
- **Reports** — Generate full academic reports per student, including grades, GPA, and payment status.
- **Input Validation** — Prevents invalid numeric input, empty strings, and other user input errors.
- **Exception Handling** — Custom exceptions for invalid grades, invalid payments, and missing students.

---

## 🧠 OOP Concepts Used

| Concept | Where it's applied |
|---|---|
| **Encapsulation** | All model classes use private fields with public getters/setters. |
| **Inheritance** | `Person → Student / Professor / Employee`, `Assessment → Exam / Assignment / Project`. |
| **Abstraction** | `Person` and `Assessment` are abstract classes with abstract methods (`getRole()`, `calculateScore()`). |
| **Polymorphism** | `printDetails()` and `calculateScore()` behave differently depending on the object's actual subclass. |
| **Interfaces** | `Payable`, `Printable`, `Searchable` define shared behavior across unrelated classes. |
| **Method Overloading** | Search methods such as `searchCourse(String courseId)` vs `searchCourse(String courseName, boolean byName)`. |
| **Method Overriding** | Subclasses override `printDetails()`, `getRole()`, and `calculateScore()`. |
| **Collections (ArrayList)** | Used to store students, courses, enrollments, grades, and payments dynamically. |
| **Exception Handling** | Custom exceptions: `InvalidGradeException`, `InvalidPaymentException`, `StudentNotFoundException`. |

---

## 📁 Folder Structure

```
SmartUniversityManagementSystem
│
├── src
│   └── university
│       │
│       ├── Main.java
│       │
│       ├── models
│       │   ├── Person.java
│       │   ├── Student.java
│       │   ├── Professor.java
│       │   ├── Employee.java
│       │   │
│       │   ├── Department.java
│       │   ├── Course.java
│       │   ├── Enrollment.java
│       │   │
│       │   ├── Assessment.java
│       │   ├── Exam.java
│       │   ├── Assignment.java
│       │   ├── Project.java
│       │   ├── Grade.java
│       │   │
│       │   └── Payment.java
│       │
│       ├── interfaces
│       │   ├── Payable.java
│       │   ├── Printable.java
│       │   └── Searchable.java
│       │
│       ├── services
│       │   ├── StudentService.java
│       │   ├── CourseService.java
│       │   ├── EnrollmentService.java
│       │   ├── PaymentService.java
│       │   └── ReportService.java
│       │
│       ├── exceptions
│       │   ├── InvalidGradeException.java
│       │   ├── InvalidPaymentException.java
│       │   └── StudentNotFoundException.java
│       │
│       └── utils
│           ├── ConsoleUI.java
│           └── InputValidator.java
│
└── README.md
```

---

## ▶️ How to Run

### Prerequisites
- Java JDK 8 or higher installed.
- Any IDE (IntelliJ IDEA, Eclipse, VS Code) or command line.

### Steps (Command Line)

1. Clone or download the project folder.
2. Navigate to the `src` directory:
   ```bash
   cd SmartUniversityManagementSystem/src
   ```
3. Compile all Java files:
   ```bash
   javac -encoding UTF-8 -d out university/models/*.java university/interfaces/*.java university/services/*.java university/exceptions/*.java university/utils/*.java university/Main.java
   ```
4. Run the application (on Windows run `chcp 65001` first so the menu box characters display correctly):
   ```bash
   java -cp out university.Main
   ```
5. Use the on-screen menu to navigate through the system.

### Steps (IDE)
1. Open the project as a Java project.
2. Ensure the package structure (`university.models`, `university.services`, etc.) matches the folder structure.
3. Run `Main.java`.

---

## 🎬 Demo Scenario

The following scenario is used to demonstrate the system end-to-end in front of the instructor:

1. **Add Professor** — Create "Dr. Ahmed"; the "Computer Science" department is created in the same step.
2. **Course Management** — (departments are entered together with professors and courses; there is no separate department menu).
3. **Add Course** — Create "Java Programming" and link it to Dr. Ahmed.
4. **Add Student** — Create "Khaled".
5. **Register Student** — Enroll Khaled in "Java Programming".
6. **Add Assessments** — Create a Midterm Exam, an Assignment, a Project, and a Final Exam for the course.
7. **Add Grades** — Record Khaled's scores for each assessment.
8. **Calculate Results** — System computes total, percentage, and pass/fail status.
9. **Make Payment** — Record a payment for Khaled:
   - Total Fees: 20,000
   - Paid: 15,000
   - Remaining: 5,000
10. **Display Report** — Show Khaled's full academic report (grades, GPA, and payment status).

---

## 📝 Notes

- All model classes belong to the `university.models` package.
- Interfaces belong to `university.interfaces`.
- Business logic (add/search/update/delete operations) lives in the `university.services` package — models themselves stay free of business logic.
- Custom exceptions live in `university.exceptions` and are thrown for invalid grades, invalid payments, and missing students.
- `Main.java` only wires services together and starts `ConsoleUI` — it contains no business logic itself.
