
        package university.services;

import university.models.Assessment;
import university.models.Course;
import university.models.Grade;
import university.models.Student;

import java.util.ArrayList;


// Service responsible for calculating student results,
// GPA, course percentages, and displaying academic reports.
public class ReportService {

    // Stores all grades available in the system.
    // Each Grade contains a Student, Course, Assessment, and Score.
    private ArrayList<Grade> grades;


    // Constructor used to create a ReportService object.
    public ReportService() {

        // Creates an empty ArrayList to store grades.
        grades = new ArrayList<>();
    }


    // Adds a new Grade to the grades list.
    //
    // This method is useful for storing grades
    // so that the report service can calculate results.
    public void addGrade(Grade grade) {

        // Adds the grade object to the list.
        grades.add(grade);
    }


    // Calculates the percentage of a student in a specific course.
    //
    // student → the student whose percentage will be calculated.
    // course  → the course whose percentage will be calculated.
    //
    // The calculation uses the weight of each assessment.
    public double calculateCoursePercentage(Student student, Course course) {

        // Stores the total calculated percentage.
        double totalPercentage = 0;

        // Goes through all grades in the system.
        for (Grade grade : grades) {

            // Checks whether this grade belongs to the requested student
            // and the requested course.
            if (grade.getStudent() == student &&
                    grade.getCourse() == course) {

                // Gets the percentage achieved in this assessment.
                double percentage = grade.getPercentage();

                // Gets the assessment weight.
                double weight = grade.getAssessment().getWeight();

                // Adds the weighted percentage to the total.
                totalPercentage += percentage * (weight / 100);
            }
        }

        // Returns the final course percentage.
        return totalPercentage;
    }


    // Calculates the GPA of a student.
    //
    // student → the student whose GPA will be calculated.
    public double calculateGPA(Student student) {

        // Stores the total percentage of all courses.
        double totalPercentage = 0;

        // Counts the number of courses that have grades.
        int courseCount = 0;

        // Stores the courses that were already counted.
        ArrayList<Course> countedCourses = new ArrayList<>();

        // Goes through all grades.
        for (Grade grade : grades) {

            // Checks whether this grade belongs to the student.
            if (grade.getStudent() == student) {

                // Gets the course related to this grade.
                Course course = grade.getCourse();

                // Prevents counting the same course more than once.
                if (!countedCourses.contains(course)) {

                    // Calculates the student's percentage in this course.
                    double coursePercentage =
                            calculateCoursePercentage(student, course);

                    // Adds the course percentage to the total.
                    totalPercentage += coursePercentage;

                    // Counts this course.
                    courseCount++;

                    // Stores the course so it is not counted again.
                    countedCourses.add(course);
                }
            }
        }

        // If the student has no courses with grades,
        // return 0 instead of dividing by zero.
        if (courseCount == 0) {
            return 0;
        }

        // Converts the average percentage to a 4.0 GPA scale.
        return (totalPercentage / courseCount) / 25;
    }


    // Checks whether a student passed a specific course.
    //
    // student → the student being checked.
    // course  → the course being checked.
    //
    // Returns true if the course percentage is 50% or higher.
    public boolean isPassed(Student student, Course course) {

        // Calculates the student's percentage in the course.
        double percentage = calculateCoursePercentage(student, course);

        // Returns true if the student achieved at least 50%.
        return percentage >= 50;
    }


    // Displays the results of a specific student.
    //
    // student → the student whose results will be displayed.
    public void displayStudentResults(Student student) {

        System.out.println("===== Student Results =====");
        System.out.println("Student: " + student.getName());

        // Stores courses that have already been displayed.
        ArrayList<Course> displayedCourses = new ArrayList<>();

        // Goes through all grades.
        for (Grade grade : grades) {

            // Checks whether the grade belongs to the student.
            if (grade.getStudent() == student) {

                // Gets the course of the grade.
                Course course = grade.getCourse();

                // Displays each course only once.
                if (!displayedCourses.contains(course)) {

                    // Calculates the course percentage.
                    double percentage =
                            calculateCoursePercentage(student, course);

                    // Checks whether the student passed.
                    boolean passed = isPassed(student, course);

                    System.out.println("Course: " + course.getCourseName());
                    System.out.println("Percentage: " + percentage + "%");
                    System.out.println("Result: " +
                            (passed ? "PASSED" : "FAILED"));
                    System.out.println("---------------------------");

                    // Marks the course as displayed.
                    displayedCourses.add(course);
                }
            }
        }
    }


    // Displays a complete academic report for a student.
    //
    // student → the student whose report will be displayed.
    public void displayStudentReport(Student student) {

        System.out.println("===== Student Academic Report =====");

        // Displays the student's basic information.
        System.out.println("Student ID: " + student.getId());
        System.out.println("Student Name: " + student.getName());
        System.out.println("Major: " + student.getMajor());

        System.out.println();

        // Displays the student's course results.
        displayStudentResults(student);

        System.out.println();

        // Calculates and displays the student's GPA.
        double gpa = calculateGPA(student);

        System.out.println("GPA: " + gpa);
        System.out.println("==================================");
    }
}
