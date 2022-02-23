package bim;

public class BimException extends Exception {
    private String message;

    public BimException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
