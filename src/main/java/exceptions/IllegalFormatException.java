package exceptions;

public class IllegalFormatException extends DukeExceptions {
    protected static final String FORMAT_ERROR_MSG =
            "The format is incorrect! Can you please check your input again? ●ω●";

    @Override
    public String toString() {
        return FORMAT_ERROR_MSG;
    }
}
