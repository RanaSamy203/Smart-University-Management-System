package university.services;

import university.models.Student;
import university.models.Professor;
import university.exceptions.StudentNotFoundException;

import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students;
    private ArrayList<Professor> professors;

    public StudentService() {
        students = new ArrayList<>();
        professors = new ArrayList<>();
    }

    // ================= STUDENTS =================

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Search Student by ID
    public Student searchStudent(int id) throws StudentNotFoundException {

        for (Student student : students) {

            if (student.getId()==id) {
                return student;
            }
        }

        throw new StudentNotFoundException("Student not found with ID: " + id);
    }

    // Search Student by Name
    public Student searchStudent(String name, boolean byName)
            throws StudentNotFoundException {

        if (byName) {

            for (Student student : students) {

                if (student.getName().equalsIgnoreCase(name)) {
                    return student;
                }
            }
        }

        throw new StudentNotFoundException("Student not found with name: " + name);
    }

    // Delete Student
    public void deleteStudent(int  id) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId()==id) {
                students.remove(i);
                return;
            }
        }
    }

    // Get All Students
    public ArrayList<Student> getAllStudents() {
        return students;
    }

    // Display Students
    public void displayStudents() {

        for (Student student : students) {
            student.printDetails();
            System.out.println("-------------------------");
        }
    }


    // ================= PROFESSORS =================

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public Professor searchProfessor(int id) {

        for (Professor professor : professors) {

            if (professor.getId()==id) {
                return professor;
            }
        }

        return null;
    }

    public void deleteProfessor(int id) {

        for (int i = 0; i < professors.size(); i++) {

            if (professors.get(i).getId()==id) {
                professors.remove(i);
                return;
            }
        }
    }

    public void displayProfessors() {

        for (Professor professor : professors) {
            professor.printDetails();
            System.out.println("-------------------------");
        }
    }
}