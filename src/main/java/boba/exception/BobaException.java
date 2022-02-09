package boba.exception;

import boba.command.Command;

/**
 * Exception class for Boba that handles
 * exceptions specific to Boba
 */
public class BobaException extends Exception{

    /** The boba.command.Command that causes the exception*/
    private Command operation;

    /**
     * Constructor that handles a boba.command.Command argument
     * @param operation The boba.command.Command
     */
    public BobaException(Command operation) {
        this.operation = operation;
    }

    /**
     * @return The boba.command.Command for this exception
     */
    public Command getOperation() {
        return operation;
    }
}
