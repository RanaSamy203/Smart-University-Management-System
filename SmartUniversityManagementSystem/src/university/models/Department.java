package src.university.models;
public class Department {
    private String departmentID;
    private String name;
    private String description;

    public Department(String departmentID, String name, String description){
        this.departmentID = departmentID;
        this.name = name;
        this.description = description;
    }

    public String getDepartmentID(){
        return departmentID;
    } 

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    
    public void printDetails(){
        System.out.println("Department ID: " + departmentID);
        System.out.println("Department Name: " + name);
        System.out.println("Department Description: " + description);
    }
    
}
