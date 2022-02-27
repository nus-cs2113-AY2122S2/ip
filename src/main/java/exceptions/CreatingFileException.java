package exceptions;

public class CreatingFileException extends DukeExceptions{
    protected static final String CREATING_FILE_ERROR_MSG =
            "Sorry, there's something wrong when creating file, please try again later QnQ";

    @Override
    public String toString() {
        return CREATING_FILE_ERROR_MSG;
    }
}
