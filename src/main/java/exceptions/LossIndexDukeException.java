package exceptions;

public class LossIndexDukeException extends DukeException {
    static final private String LossIndex_EXCEPTION_WORDS = " ☹ OOPS!!! No index is given";

    @Override
    public String toString() {
        return LossIndex_EXCEPTION_WORDS;
    }
}
