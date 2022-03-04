package main.java.duke.exception;

/**
 * Class for the DukeException. It is the exception that handles almost all errors raised by
 * invalid inputs from the user.
 */

public class DukeException extends Exception {
    
    private final String error;

    /**
     * Constructor for DukeException.
     * 
     * @param error String error message
     */
    public DukeException(String error) {
        this.error = error;
    }

    /**
     * Method to get the error message.
     * 
     * @return The error message.
     */
    public String getMessage() {
        return this.error;
    }
}