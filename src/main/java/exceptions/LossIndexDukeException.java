package exceptions;

public class LossIndexDukeException extends DukeException {
    static final private String LOSS_INDEX_EXCEPTION_WORDS = " ☹ OOPS!!! No index is given";

    @Override
    public String toString() {
        return LOSS_INDEX_EXCEPTION_WORDS;
    }
}
