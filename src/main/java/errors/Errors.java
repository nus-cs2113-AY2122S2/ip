package errors;

/**
 * This class is used to keep track of all the errors
 *
 */
public class Errors {

    public enum ErrorTypes{
    INPUT_ERROR,INVALID_TASK_NAME_ERROR,INVALID_TASK_DETAILS_ERROR, INVALID_TASK_DELETE_ERROR, INVALID_TASK_MARK_ERROR, FILE_NOT_FOUND,IO_ERROR
    }

    public static final String  INPUT_ERROR = " Please specify an appropriate command for me to execute";
    public static final String  INVALID_TASK_NAME_ERROR = " Hi I need a valid task name";
    public static final String  INVALID_TASK_DETAILS_ERROR = " Hi your task details is incomplete";
    public static final String  INVALID_TASK_MARK_ERROR = " Hi I need a valid task number";
    public static final String  INVALID_TASK_DELETE_ERROR = " Hi I need a valid task number to delete";
    public static final String  FILE_NOT_FOUND = " FILE NOT FOUND";
    public static final String  IO_ERROR= " IO ERROR DETECTED";
}
