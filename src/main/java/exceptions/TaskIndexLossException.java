package exceptions;

public class TaskIndexLossException extends DukeExceptions {
    protected static final String INDEX_LOSS_ERROR_MSG =
            "Did you specify the index?∑(っ °Д °;)っ";

    @Override
    public String toString() {
        return INDEX_LOSS_ERROR_MSG;
    }
}
