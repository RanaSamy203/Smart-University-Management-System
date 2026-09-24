package university.exceptions;

/**
 * Custom exception used when an invalid grade is entered.
 */
public class InvalidGradeException extends Exception {

    /**
     * Creates an InvalidGradeException with the specified message.
     *
     * @param message description of the grade validation error
     */
    public InvalidGradeException(String message) {
        super(message);
    }
}
