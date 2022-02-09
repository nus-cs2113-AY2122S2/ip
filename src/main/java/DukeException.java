public class DukeException extends Exception {

    private DukeExceptionCause exceptionCause;

    public DukeException(DukeExceptionCause exceptionCause) {
        setExceptionCause(exceptionCause);
    }

    public DukeExceptionCause getExceptionCause() {
        return exceptionCause;
    }

    public void setExceptionCause(DukeExceptionCause exceptionCause) {
        this.exceptionCause = exceptionCause;
    }
}
