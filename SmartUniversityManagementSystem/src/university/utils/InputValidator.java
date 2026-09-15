package university.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Centralized input reading + validation for the whole console app.
 *
 * WHY THIS CLASS EXISTS (bug it fixes):
 * The original ConsoleUI used scanner.next() everywhere, including for
 * names. next() only reads a single whitespace-delimited token, so typing
 * a two-word name like "Ahmed Ali" would store "Ahmed" as the name and
 * then silently feed "Ali" into the very next prompt (e.g. as the ID).
 * This class always uses nextLine() for text and safely consumes the
 * leftover newline after numeric input, so the two never interleave.
 *
 * It also wraps every read in a loop that catches bad input (letters
 * typed where a number is expected) instead of letting the whole
 * program crash with an uncaught InputMismatchException.
 */
public class InputValidator {

    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a full line of text (can contain spaces).
     */
    public String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    /**
     * Reads a line of text that is NOT allowed to be empty.
     * Keeps asking until the user provides something.
     */
    public String readNonEmptyString(String message) {
        while (true) {
            String value = readString(message);
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("-> Error: input cannot be empty. Please try again.");
        }
    }

    /**
     * Reads an integer. Keeps asking until the user types a valid integer.
     * Also consumes the trailing newline so a later readString() call
     * does not immediately return an empty string.
     */
    public int readInt(String message) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("-> Error: please enter a valid whole number.");
            }
        }
    }

    /**
     * Reads a double (used for fees / payments later on).
     */
    public double readDouble(String message) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("-> Error: please enter a valid number (e.g. 1500.50).");
            }
        }
    }

    /**
     * Reads a yes/no answer as a boolean.
     */
    public boolean readBoolean(String message) {
        while (true) {
            String line = readString(message + " (y/n): ").toLowerCase();
            if (line.equals("y") || line.equals("yes")) {
                return true;
            }
            if (line.equals("n") || line.equals("no")) {
                return false;
            }
            System.out.println("-> Error: please answer with y or n.");
        }
    }
}
