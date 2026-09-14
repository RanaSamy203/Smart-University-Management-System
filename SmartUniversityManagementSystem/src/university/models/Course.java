package university.models;

public class Course {

    private String courseId;
    private String courseName;
    private int creditHours;
    private int maximumStudents;
    private Professor professor;
    private Department department;

    public Course(String courseId, String courseName,
                  int creditHours, int maximumStudents,
                  Professor professor, Department department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.maximumStudents = maximumStudents;
        this.professor = professor;
        this.department = department;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public int getMaximumStudents() {
        return maximumStudents;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Department getDepartment() {
        return department;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setMaximumStudents(int maximumStudents) {
        this.maximumStudents = maximumStudents;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void printDetails() {
        System.out.println("============ Course Details ============");
        System.out.println("Course ID       : " + courseId);
        System.out.println("Course Name     : " + courseName);
        System.out.println("Credit Hours    : " + creditHours);
        System.out.println("Maximum Students: " + maximumStudents);
        System.out.println("Professor       : "
                + (professor != null ? professor.getName() : "Not Assigned"));
        System.out.println("Department      : "
                + (department != null ? department.getName() : "Not Assigned"));
        System.out.println("========================================");
    }
}
