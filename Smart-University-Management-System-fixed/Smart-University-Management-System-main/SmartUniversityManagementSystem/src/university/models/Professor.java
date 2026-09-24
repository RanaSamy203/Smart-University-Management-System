package university.models;

public class Professor extends Person{
    private String specialization;
    private Department department;

    public Professor(int id, String name, String email, String phone,
                     String specialization, Department department)
    {

        super(id, name, email, phone);

        this.specialization = specialization;
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Department getDepartment() {
        return department;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String getRole() {
        return "Professor";
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Specialization: " + specialization);
        System.out.println("Department: "
                + (department != null ? department.getName() : "Not Assigned"));
    }
}