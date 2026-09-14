package src.university.models;

// Project is a type of Assessment.
// It inherits the common properties and methods from Assessment.
public class Project extends Assessment {

    // Stores the type of the project.
    // Examples: "Individual", "Group", "Graduation Project".
    private String projectType;


    // Constructor used to create a Project object.
    //
    // assessmentId → unique ID of the assessment.
    // title        → name of the project.
    // maxScore     → maximum possible score.
    // weight       → percentage of the final course grade.
    // projectType  → type of the project.
    public Project(String assessmentId, String title, double maxScore,
                   double weight, String projectType) {

        // Calls the parent class constructor
        // to initialize the common Assessment data.
        super(assessmentId, title, maxScore, weight);

        // Stores the specific type of this project.
        this.projectType = projectType;
    }


    // Returns the type of the project.
    public String getProjectType() {
        return projectType;
    }


    // Changes the type of the project.
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }


    // Overrides the abstract method from Assessment.
    //
    // rawScore → the score obtained by the student.
    //
    // The project does not apply a special calculation,
    // so the raw score is returned directly.
    @Override
    public double calculateScore(double rawScore) {
        return rawScore;
    }


    // Displays the project information.
    // It also displays the common Assessment information.
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Project Type: " + projectType);
    }
}
