package exceptions;

public class MakeTaskDukeException extends DukeException {
    private static final String MAKE_TASK_EXCEPTION_WORDS = " ☹ OOPS!!! Fail to make the task:(";

    @Override
    public String toString() {
        return MAKE_TASK_EXCEPTION_WORDS;
    }
}
