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
