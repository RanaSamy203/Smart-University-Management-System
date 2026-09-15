package university;

import university.services.StudentService;
import university.services.CourseService;
import university.services.EnrollmentService;
import university.services.PaymentService;
import university.services.ReportService;
import university.utils.ConsoleUI;

/**
 * Main Entry Point — Smart University Management System (SUMS)
 *
 * Responsibilities (Part 6):
 *   - Create all service instances.
 *   - Pass them to ConsoleUI.
 *   - Start the application loop.
 *
 * Main contains NO business logic — it only wires the system together.
 */
public class Main {

    public static void main(String[] args) {

        // ── Create all services ──────────────────────────────────────────
        StudentService    studentService    = new StudentService();
        CourseService     courseService     = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();
        PaymentService    paymentService    = new PaymentService();
        ReportService     reportService     = new ReportService();

        // ── Wire and launch the UI ───────────────────────────────────────
        ConsoleUI ui = new ConsoleUI(
                studentService,
                courseService,
                enrollmentService,
                paymentService,
                reportService
        );

        ui.start();
    }
}
