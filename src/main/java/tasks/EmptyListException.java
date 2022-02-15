package tasks;

public class EmptyListException extends Exception {
    public static final String EMPTY_LIST_MSG = "List is empty.";

    public EmptyListException(String errorMsg) {
        super(errorMsg);
    }
}
