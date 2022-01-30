package exceptions;

public class TodoDukeException extends DukeException{

    static final private String TODO_EXCEPTION_WORDS = " ☹ OOPS!!! The description of a todo cannot be empty.";
    @Override
    public String toString() {
        return TODO_EXCEPTION_WORDS;
    }

}
