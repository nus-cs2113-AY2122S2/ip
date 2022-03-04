package boba.exception;

/**
 * Exception class for Boba that handles
 * exceptions specific to Boba
 */
public class BobaException extends Exception{

    /**
     * Default Constructor
     */
    public BobaException() {}

    /**
     * Constructor with errorMessage
     * @param errorMessage
     */
    public BobaException(String errorMessage) {
        super(errorMessage);
    }

}
