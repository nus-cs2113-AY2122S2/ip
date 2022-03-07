package exceptions;

public class TaskNameLossException extends DukeExceptions {
    protected static final String INDEX_LOSS_ERROR_MSG =
            "Did you specify the name of the task?∑(っ °Д °;)っ";

    @Override
    public String toString() {
        return INDEX_LOSS_ERROR_MSG;
    }
}
