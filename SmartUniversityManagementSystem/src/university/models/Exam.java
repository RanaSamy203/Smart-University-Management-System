// Exam is a type of Assessment.
// It inherits the common properties and methods from Assessment.
public class Exam extends Assessment {

    // Stores the type of the exam.
    // Examples: "Midterm" or "Final".
    private String examType;


    // Constructor used to create an Exam object.
    //
    // assessmentId → unique ID of the assessment.
    // title        → name of the exam.
    // maxScore     → maximum possible score.
    // weight       → percentage of the final course grade.
    // examType     → type of exam, such as Midterm or Final.
    public Exam(String assessmentId, String title, double maxScore,
                double weight, String examType) {

        // Calls the parent class constructor
        // to initialize the common Assessment data.
        super(assessmentId, title, maxScore, weight);

        // Stores the specific type of this exam.
        this.examType = examType;
    }


    // Returns the type of the exam.
    public String getExamType() {
        return examType;
    }


    // Changes the type of the exam.
    public void setExamType(String examType) {
        this.examType = examType;
    }


    // Overrides the abstract method from Assessment.
    //
    // rawScore → the score obtained by the student.
    //
    // The method converts the raw score into the assessment score.
    @Override
    public double calculateScore(double rawScore) {
        return rawScore;
    }


    // Displays the exam information.
    // It also displays the common Assessment information.
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Exam Type: " + examType);
    }
}
