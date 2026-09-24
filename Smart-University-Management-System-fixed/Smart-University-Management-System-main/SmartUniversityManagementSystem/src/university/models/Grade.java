package university.models;
// Grade represents the result/score of a student
// in a specific assessment for a specific course.
public class Grade {

    // The student who received this grade.
    private Student student;

    // The course in which the student received this grade.
    private Course course;

    // The assessment for which the grade was given.
    // It can be an Exam, Assignment, or Project.
    private Assessment assessment;

    // The actual score obtained by the student.
    private double score;


    // Constructor used to create a Grade object.
    //
    // student    → the student who received the grade.
    // course     → the course related to the grade.
    // assessment → the assessment related to the grade.
    // score      → the score obtained by the student.
    public Grade(Student student, Course course,
                 Assessment assessment, double score) {

        // Stores the student associated with this grade.
        this.student = student;

        // Stores the course associated with this grade.
        this.course = course;

        // Stores the assessment associated with this grade.
        this.assessment = assessment;

        // Stores the student's score.
        this.score = score;
    }


    // Returns the student associated with this grade.
    public Student getStudent() {
        return student;
    }


    // Returns the course associated with this grade.
    public Course getCourse() {
        return course;
    }


    // Returns the assessment associated with this grade.
    public Assessment getAssessment() {
        return assessment;
    }


    // Returns the score obtained by the student.
    public double getScore() {
        return score;
    }


    // Changes the student's score.
    public void setScore(double score) {
        this.score = score;
    }


    // Calculates the student's percentage in this assessment.
    //
    // Formula:
    // percentage = (score / maximum score) × 100
    //
    // Example:
    // score = 40
    // maxScore = 50
    // percentage = 80%
    public double getPercentage() {

        // Gets the maximum score from the assessment.
        double maxScore = assessment.getMaxScore();

        // Avoids division by zero for a badly configured assessment.
        if (maxScore <= 0) {
            return 0;
        }

        // Calculates and returns the percentage.
        return (score / maxScore) * 100;
    }


    // Checks whether the student passed this assessment.
    //
    // The student is considered passed
    // when the percentage is 50% or higher.
    public boolean isPassed() {
        return getPercentage() >= 50;
    }


    // Displays all information related to this grade.
    public void printDetails() {

        System.out.println("Student: " + student.getName());
        System.out.println("Course: " + course.getCourseName());
        System.out.println("Assessment: " + assessment.getTitle());
        System.out.println("Score: " + score);
        System.out.println("Percentage: " + getPercentage() + "%");
        System.out.println("Passed: " + isPassed());
    }
}
