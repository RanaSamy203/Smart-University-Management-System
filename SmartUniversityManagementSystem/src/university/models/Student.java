package university.models;

public class Student extends Person{
    public String major;
    private double gpa;
    private int academicLevel;

    public Student(int id, String name, String email, String phone, String major, double gpa, int academicLevel)
    {
        super(id,name,email, phone);
        this.major=major;
        this.gpa=gpa;
        this.academicLevel=academicLevel;
    }

    public String getMajor(){
        return major;
    }
    public double getGpa(){
        return gpa;
    }
    public int getAcademicLevel(){
        return academicLevel;
    }
    public void setMajor(String major){
        this.major=major;
    }
    public void setGpa(double gpa){
        this.gpa=gpa;
    }
    public void setAcademicLevel(int academicLevel){
        this.academicLevel=academicLevel;
    }
    @Override public String getRole(){
        System.out.println("Role is Student");
        return null;
    }
    @Override public void printDetails() {
        super.printDetails();
        System.out.println("Major: " + major);
        System.out.println("GPA: " + gpa);
        System.out.println("Academic Level: " + academicLevel);
    }
}
