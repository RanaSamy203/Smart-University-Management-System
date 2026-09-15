<<<<<<< HEAD
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
=======
```java
package university.services;

import university.models.Student;

import java.util.ArrayList;

/**
 * Service class responsible for managing students
 * in the Smart University Management System.
 */
public class StudentService {

    private ArrayList<Student> students;

    /**
     * Creates a StudentService with an empty list of students.
     */
    public StudentService() {
        students = new ArrayList<>();
    }

    /**
     * Adds a student to the system.
     *
     * @param student the student to add
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Finds a student by their ID.
     *
     * @param id the student ID
     * @return the matching student, or null if not found
     */
    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }

        return null;
    }

    /**
     * Removes a student from the system by their ID.
     *
     * @param id the student ID
     * @return true if the student was removed, otherwise false
     */
    public boolean removeStudent(String id) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        return true;
    }

    /**
     * Returns all students currently registered in the system.
     *
     * @return list of all students
     */
    public ArrayList<Student> getAllStudents() {
        return students;
    }

    /**
     * Searches for students by name.
     *
     * @param name the name or part of the name to search for
     * @return list of students matching the given name
     */
    public ArrayList<Student> searchStudentsByName(String name) {
        ArrayList<Student> results = new ArrayList<>();

        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(student);
            }
        }

        return results;
    }
}
```
>>>>>>> 12327b82d8548d6ca04d166fac264febbc2a7b5d
