package exceptions;


public class UnknownOrderDukeException extends DukeException {

    private static final String TODO_EXCEPTION_WORDS = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    @Override
    public String toString() {
        return TODO_EXCEPTION_WORDS;
    }

}
