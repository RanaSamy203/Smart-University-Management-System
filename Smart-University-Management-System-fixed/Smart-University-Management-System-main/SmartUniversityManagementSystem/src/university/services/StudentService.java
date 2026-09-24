package university.services;

import university.exceptions.StudentNotFoundException;
import university.models.Professor;
import university.models.Student;

import java.util.ArrayList;

/**
 * Manages students and professors (in-memory storage).
 */
public class StudentService {

    private final ArrayList<Student> students;
    private final ArrayList<Professor> professors;

    public StudentService() {
        students = new ArrayList<>();
        professors = new ArrayList<>();
    }

    // ================= STUDENTS =================

    /** Adds a student. Returns false if the ID already exists. */
    public boolean addStudent(Student student) {
        if (student == null || hasStudent(student.getId())) {
            return false;
        }
        students.add(student);
        return true;
    }

    public boolean hasStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int id) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new StudentNotFoundException("Student not found with ID: " + id);
    }

    public Student searchStudent(String name, boolean byName)
            throws StudentNotFoundException {
        if (byName && name != null) {
            for (Student student : students) {
                if (student.getName().equalsIgnoreCase(name)) {
                    return student;
                }
            }
        }
        throw new StudentNotFoundException("Student not found with name: " + name);
    }

    /** Deletes a student. Returns true only if a student was actually removed. */
    public boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public void displayStudents() {
        for (Student student : students) {
            student.printDetails();
            System.out.println("-------------------------");
        }
    }

    // ================= PROFESSORS =================

    /** Adds a professor. Returns false if the ID already exists. */
    public boolean addProfessor(Professor professor) {
        if (professor == null || searchProfessor(professor.getId()) != null) {
            return false;
        }
        professors.add(professor);
        return true;
    }

    public Professor searchProfessor(int id) {
        for (Professor professor : professors) {
            if (professor.getId() == id) {
                return professor;
            }
        }
        return null;
    }

    /** Deletes a professor. Returns true only if a professor was actually removed. */
    public boolean deleteProfessor(int id) {
        for (int i = 0; i < professors.size(); i++) {
            if (professors.get(i).getId() == id) {
                professors.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayProfessors() {
        if (professors.isEmpty()) {
            System.out.println("No professors in the system yet.");
            return;
        }
        for (Professor professor : professors) {
            professor.printDetails();
            System.out.println("-------------------------");
        }
    }
}
