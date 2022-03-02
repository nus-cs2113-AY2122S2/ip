package duke.exception;

/**
 * Represents exceptions that occur within the Duke program.
 * A DukeException object corresponds to a particular
 * exception that has occurred within the code and contains DukeExceptionCause which specifies the type of exception a
 * DukeException object corresponds to.
 */
public class DukeException extends Exception {

    private DukeExceptionCause exceptionCause;

    public DukeException(DukeExceptionCause exceptionCause) {
        setExceptionCause(exceptionCause);
    }

    public DukeExceptionCause getExceptionCause() {
        return exceptionCause;
    }

    private void setExceptionCause(DukeExceptionCause exceptionCause) {
        this.exceptionCause = exceptionCause;
    }
}
