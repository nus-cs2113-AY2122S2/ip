package bim;

public class BimException extends Exception {
    private String description;

    public BimException(String description) {
        this.description = description;
    }

    public BimException() { this.description = ""; }

    public String getDescription() {
        return description;
    }
}
