/**
 * Check whether the user's command make sense and throws error messages if it doesn't
 */

public class Parser {

    /**
     * Check exceptions for command keyword "find"
     * @param arrayOfStr array of strings that the input instruction has split into
     * @throws DukeException
     */
    public static void checkExceptionsFind(String[] arrayOfStr) throws DukeException {
        if (arrayOfStr.length == 1){
            //if user input "find"
            throw new DukeException(UI.ERROR_NO_KEYWORD);
        }
        if (arrayOfStr.length == 2 && arrayOfStr[1].equals("")){
            //if user input "find "
            throw new DukeException(UI.ERROR_NO_KEYWORD);
        }
    }

    /**
     * Check exceptions for command keyword "event"
     * @param arrayOfStr array of strings that the input instruction has split into
     * @param arrayOfEvent 2 strings that the input instruction for "event" excluding command keyword has split into, regex being "/at"
     * @throws DukeException
     */
    public static void checkExceptionsEvent(String[] arrayOfStr, String[] arrayOfEvent) throws DukeException {
        if (arrayOfStr.length == 1 || arrayOfStr[1].equals("/at")){
            //if user input "event" or "event /at"
            throw new DukeException(UI.ERROR_NO_EVENT);
        }
        if (arrayOfStr.length == 2 && arrayOfStr[1].equals("")){
            //if user input "event "
            throw new DukeException(UI.ERROR_NO_EVENT);
        }
        if (arrayOfEvent.length == 1){
            //if user did not input "/at"
            throw new DukeException(UI.ERROR_NO_EVENT_DATE);
        }
    }

    /**
     * Check exceptions for command keyword "deadline"
     * @param arrayOfStr array of strings that the input instruction has split into
     * @param arrayOfDeadline 2 strings that the input instruction for "deadline" excluding command keyword has split into, regex being "/by"
     * @throws DukeException
     */
    public static void checkExceptionsDeadline(String[] arrayOfStr, String[] arrayOfDeadline) throws DukeException {
        if (arrayOfStr.length == 1 || arrayOfStr[1].equals("/by")){
            //if user input "deadline" or "deadline /by"
            throw new DukeException(UI.ERROR_NO_TASK);
        }
        if (arrayOfStr.length == 2 && arrayOfStr[1].equals("")){
            //if user input "deadline "
            throw new DukeException(UI.ERROR_NO_TASK);
        }
        if (arrayOfDeadline.length == 1){
            //if user did not input "/by"
            throw new DukeException(UI.ERROR_NO_DUE_DATE);
        }
    }

    /**
     * Check exceptions for command keyword "delete"
     * @param arrayOfStr array of strings that the input instruction has split into
     * @throws DukeException
     */
    public static void checkExceptionsDelete(String[] arrayOfStr) throws DukeException {
        if (arrayOfStr.length == 1) {
            //if user input "delete"
            throw new DukeException(UI.ERROR_NO_TASK_NUMBER);
        }
        if (arrayOfStr.length == 2 && arrayOfStr[1].equals("")){
            //if user input "delete "
            throw new DukeException(UI.ERROR_NO_TASK_NUMBER);
        }
    }

    /**
     * Check exceptions for command keyword "mark"
     * @param arrayOfStr array of strings that the input instruction has split into
     * @throws DukeException
     */
    public static void checkExceptionsMark(String[] arrayOfStr) throws DukeException {
        if (arrayOfStr.length == 1){
            //if user input "mark"
            throw new DukeException(UI.ERROR_NO_TASK_NUMBER);
        }
        if (arrayOfStr.length == 2 && arrayOfStr[1].equals("")){
            //if user input "mark "
            throw new DukeException(UI.ERROR_NO_TASK_NUMBER);
        }
    }

    /**
     * Check exceptions for command keyword "todo"
     * @param arrayOfStr array of strings that the input instruction has split into
     * @throws DukeException
     */
    public static void checkExceptionsTodo(String[] arrayOfStr) throws DukeException {
        if (arrayOfStr.length == 1) {
            //if user input "todo"
            throw new DukeException(UI.ERROR_NO_TASK);
        }
        if (arrayOfStr.length == 2 && arrayOfStr[1].equals("")){
            //if user input "todo "
            throw new DukeException(UI.ERROR_NO_TASK);
        }
    }

}
