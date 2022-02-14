package boba.exception;

import boba.command.Command;

/**
 * Exception class for Boba that handles
 * exceptions specific to Boba
 */
public class BobaException extends Exception{

    /** The Command that causes the exception*/
    private Command operation;

    /**
     * Constructor that handles a Command argument
     * @param operation The Command
     */
    public BobaException(Command operation) {
        this.operation = operation;
    }

    /**
     * @return Command for this exception
     */
    public Command getOperation() {
        return operation;
    }
}
