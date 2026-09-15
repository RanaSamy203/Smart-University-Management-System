package university.exceptions;

/**
 * Thrown when a Student with a given ID does not exist in the system.
 * Used by Search / Update / Delete Student operations so that ConsoleUI
 * can catch a single, meaningful exception instead of guessing from a
 * null return value.
 */
public class StudentNotFoundException extends Exception {

    public StudentNotFoundException(String message) {
        super(message);
    }
}
