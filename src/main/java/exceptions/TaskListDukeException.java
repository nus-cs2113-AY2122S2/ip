package exceptions;

public class TaskListDukeException extends DukeException {
    static final private String TASKLIST_EXCEPTION_WORDS = " ☹ OOPS!!! Fail to Access Task List, Check the Index!";

    @Override
    public String toString() {
        return TASKLIST_EXCEPTION_WORDS;
    }
}
