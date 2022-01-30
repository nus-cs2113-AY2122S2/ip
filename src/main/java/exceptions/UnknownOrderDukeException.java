package exceptions;


public class UnknownOrderDukeException extends DukeException{

    static final private String TODO_EXCEPTION_WORDS = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    @Override
    public String toString() {
        return TODO_EXCEPTION_WORDS;
    }

}
