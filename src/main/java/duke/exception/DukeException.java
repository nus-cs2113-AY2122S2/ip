package main.java.duke.exception;

public class DukeException extends Exception {
    
    private final String error;

    public DukeException(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.error;
    }
}