public class DukeException extends Exception {
    private String errorMessage;
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
