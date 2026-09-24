package university.models;
// Assignment is a type of Assessment.
// It inherits the common properties and methods from Assessment.
public class Assignment extends Assessment {

    // Stores the date on which the assignment was submitted.
    private String submissionDate;


    // Constructor used to create an Assignment object.
    //
    // assessmentId  → unique ID of the assessment.
    // title         → name of the assignment.
    // maxScore      → maximum possible score.
    // weight        → percentage of the final course grade.
    // submissionDate → date when the assignment was submitted.
    public Assignment(String assessmentId, String title, double maxScore,
                      double weight, String submissionDate) {

        // Calls the parent class constructor
        // to initialize the common Assessment data.
        super(assessmentId, title, maxScore, weight);

        // Stores the assignment submission date.
        this.submissionDate = submissionDate;
    }


    // Returns the submission date of the assignment.
    public String getSubmissionDate() {
        return submissionDate;
    }


    // Changes the submission date of the assignment.
    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }


    // Overrides the abstract method from Assessment.
    //
    // rawScore → the score obtained by the student.
    //
    // The assignment does not apply a special calculation,
    // so the raw score is returned directly.
    @Override
    public double calculateScore(double rawScore) {
        return rawScore;
    }


    // Displays the assignment information.
    // It also displays the common Assessment information.
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Submission Date: " + submissionDate);
    }
}
