package exceptions;

public class IllegalSavingAction extends DukeExceptions {
    protected static final String SAVING_FILE_ERROR_MSG =
            "Sorry, there's something wrong when saving task, please try again later QnQ";

    @Override
    public String toString() {
        return SAVING_FILE_ERROR_MSG;
    }
}
