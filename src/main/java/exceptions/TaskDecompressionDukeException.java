package exceptions;

public class TaskDecompressionDukeException extends DukeException{
    static final private String TASK_DECOMPRESSION_EXCEPTION_WORDS = " ☹ OOPS!!! Compress Task Fails";

    @Override
    public String toString() {
        return TASK_DECOMPRESSION_EXCEPTION_WORDS;
    }
}
