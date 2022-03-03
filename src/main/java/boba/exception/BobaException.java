package boba.exception;

/**
 * Exception class for Boba that handles
 * exceptions specific to Boba
 */
public class BobaException extends Exception{

    public BobaException() {}

    public BobaException(String errorMessage) {
        super(errorMessage);
    }

}
