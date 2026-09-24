package university.models;


public class Employee extends Person{
    private String jobTitle;
    private Department department;

    public Employee(int id, String name, String email, String phone,
                    String jobTitle, Department department)
    {
        super(id,name,email, phone);
        this.jobTitle=jobTitle;
        this.department=department;
    }
    public String getJobTitle(){
        return jobTitle;
    }
    public Department getDepartment(){
        return department;
    }
    public void setJobTitle(String jobTitle){
        this.jobTitle=jobTitle;
    }
    public void setDepartment(Department department){
        this.department=department;
    }
    @Override public String getRole()  {
        return "Employee";
    }
    @Override public void printDetails() {
        super.printDetails();
        System.out.println("JobTitle: " + jobTitle);
        System.out.println("Department: "
                + (department != null ? department.getName() : "Not Assigned"));
    }
}
