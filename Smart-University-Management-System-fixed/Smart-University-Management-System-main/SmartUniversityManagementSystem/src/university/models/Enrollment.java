package university.models;

// This class represents the registration of a Student in a Course.
public class Enrollment {

    // Stores the Student object who is registered.
    private Student student;

    // Stores the Course object the student registered in.
    private Course course;

    // Stores the date when the student registered.
    private String enrollmentDate;

    // Stores the current registration status.
    // Possible values: REGISTERED or DROPPED.
    private String status;

    // Constructor used to create a new enrollment.
    // The initial status is always REGISTERED.
    public Enrollment(Student student, Course course, String enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = "REGISTERED";
    }

    // Returns the Student object.
    public Student getStudent() {
        return student;
    }

    // Returns the Course object.
    public Course getCourse() {
        return course;
    }

    // Returns the enrollment date.
    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    // Returns the current enrollment status.
    public String getStatus() {
        return status;
    }

    // Changes the enrollment status.
    // For example: REGISTERED -> DROPPED.
    public void setStatus(String status) {
        this.status = status;
    }

    // Displays all information about this enrollment.
    public void printDetails() {
        System.out.println("Student: " + student);
        System.out.println("Course: " + course);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Status: " + status);
    }
}
