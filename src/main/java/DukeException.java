public class DukeException extends Exception {
    private String description;

    public DukeException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}