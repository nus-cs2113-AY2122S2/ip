package exceptions;

public class IllegalTimeFormatException extends DukeExceptions {
    protected static final String FORMAT_ERROR_MSG =
            "The time format is incorrect! Can you please check your input again? (￣～￣；) " +
                    "You can check the manual to find out acceptable time format!";

    @Override
    public String toString() {
        return FORMAT_ERROR_MSG;
    }
}
