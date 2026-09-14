package src.university.models;

import src.university.models.Department;
import src.university.models.Professor;

public class Course {
    private String courseID;
    private String courseName;
    private int creditHours;
    private int maxmumStudnets;
    private Professor professor;// -> عملت لها استدعاء من دالة البروفسيور
    private Department department;

    // ___________________________________________//
    public Course(String courseID, String courseName,
            int creditHours,
            int maxmumStudnets,
            Professor professorName,
            Department department) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.maxmumStudnets = maxmumStudnets;
        this.professor = professor;
        this.department = department;

    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public int getMaxmumStudnets() {
        return maxmumStudnets;
    }

    public Professor getProfessorName() {
        return professor;
    }

    public Department getDepartment() {
        return department;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setMaximumStudents(int setMaximumStudents) {
        this.maxmumStudnets = maxmumStudnets;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void printDetails(){
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + courseName);
        System.out.println("Credit Hours: " + creditHours);
        System.out.println("Maximum Students: " + maxmumStudnets);

        if(professor!=null)
        {
            System.out.println("Professor: " + professor.getName());
        }else
        {
            System.out.println("No Professor Assigned");
        }

        if(department!=null)
        {
            System.out.println("Department: " + department.getName());
        }else
        {
            System.out.println("No Department Assigned");
        }
    }

}
