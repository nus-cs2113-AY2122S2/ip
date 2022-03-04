package exceptions;

public class TodoDukeException extends DukeException {

    private static final String TODO_EXCEPTION_WORDS = " ☹ OOPS!!! The description of a todo cannot be empty.";

    @Override
    public String toString() {
        return TODO_EXCEPTION_WORDS;
    }

}
