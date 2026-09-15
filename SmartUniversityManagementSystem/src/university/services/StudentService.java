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
