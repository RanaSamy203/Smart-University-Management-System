package src.university.models;

// Abstract class that represents a general assessment in the university system.
// It is the parent class for Exam, Assignment, and Project.
public abstract class Assessment {

    // Unique identifier for the assessment.
    private String assessmentId;

    // Name or title of the assessment.
    private String title;

    // Maximum score that the student can get in this assessment.
    private double maxScore;

    // Percentage/weight of this assessment in the course final grade.
    private double weight;


    // Constructor used to initialize all assessment information.
    //
    // assessmentId → unique ID of the assessment.
    // title        → assessment name.
    // maxScore     → maximum possible score.
    // weight       → assessment contribution to the final course grade.
    public Assessment(String assessmentId, String title, double maxScore, double weight) {
        this.assessmentId = assessmentId;
        this.title = title;
        this.maxScore = maxScore;
        this.weight = weight;
    }


    // Returns the unique ID of the assessment.
    public String getAssessmentId() {
        return assessmentId;
    }


    // Returns the title/name of the assessment.
    public String getTitle() {
        return title;
    }


    // Returns the maximum score of the assessment.
    public double getMaxScore() {
        return maxScore;
    }


    // Returns the weight of the assessment.
    public double getWeight() {
        return weight;
    }


    // Changes the title of the assessment.
    public void setTitle(String title) {
        this.title = title;
    }


    // Changes the maximum score of the assessment.
    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }


    // Changes the weight of the assessment.
    public void setWeight(double weight) {
        this.weight = weight;
    }


    // Abstract method used to calculate the score of an assessment.
    //
    // rawScore → the actual score obtained by the student.
    //
    // Each child class must provide its own implementation.
    // This demonstrates abstraction and method overriding.
    public abstract double calculateScore(double rawScore);


    // Displays the common information of the assessment.
    // Child classes can override this method to display their own additional information.
    public void printDetails() {
        System.out.println("Assessment ID: " + assessmentId);
        System.out.println("Title: " + title);
        System.out.println("Max Score: " + maxScore);
        System.out.println("Weight: " + weight);
    }
}
