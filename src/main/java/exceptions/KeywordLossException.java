package exceptions;

public class KeywordLossException extends DukeExceptions {
    protected static final String KEYWORD_LOSS_ERROR_MSG =
            "Did you specify the keyword?∑(っ °Д °;)っ";

    @Override
    public String toString() {
        return KEYWORD_LOSS_ERROR_MSG;
    }
}
