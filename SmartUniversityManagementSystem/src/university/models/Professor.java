<<<<<<< HEAD
package src.university.models;

public class Professor extends Person{
    private String specialization;
   // private Department department;

    public Professor(int id, String name, String email, String phone,
                     String specialization, //Department department)
    {

        super(id, name, email, phone);

        this.specialization = specialization;
        //this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

//    public Department getDepartment() {
//        return department;
//    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

//    public void setDepartment(Department department) {
//        this.department = department;
//    }

    @Override
    public String getRole() {
        System.out.println("Role is : Professor");
        return null;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Specialization: " + specialization);
        //System.out.println("Department: " + department);
    }
}
=======
package models;
public class Professor {
    private String professorID;
    private String name;
    private String email;

    public Professor(String professorID, String name, String email){
        this.professorID = professorID;
        this.name = name;
        this.email = email;
    }

    public String getProfessorID(){
        return professorID;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;

    }

    public void setProfessorID(String professorID){
        this.professorID = professorID;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public void printDetails(){
        System.out.println("Professor ID: " + professorID);
        System.out.println("Professor Name: " + name);
        System.out.println("Professor Email: " + email);
    }
    @Override
    public String toString(){
        return "Professor ID: " + professorID + ", Professor Name: " + name + ", Professor Email: " + email;
    }


    
}
>>>>>>> c06184da589ed585573f0b551471f2ece2779d72
