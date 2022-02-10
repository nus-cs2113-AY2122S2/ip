package exceptions;

public class WrongTimeFormatDukeException extends DukeException{
    static final private String WRONG_TIME_FORMAT_EXCEPTION_WORDS = " ☹ OOPS!!! The time is in wrong format :-(";

    @Override
    public String toString() {
        return WRONG_TIME_FORMAT_EXCEPTION_WORDS;
    }
}
