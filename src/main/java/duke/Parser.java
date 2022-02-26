package duke;

import duke.exceptions.*;

import java.time.format.DateTimeParseException;

public class Parser {
    public static String boundary = "____________________________________________________________" + System.lineSeparator();

    /* Report error to user if he/she did not put task description.
     * @param request The request by user.
     * @throws TaskEmptyException If user request only contains task type but no task description.
     */
    public void checkDescription(String request) throws TaskEmptyException {
        if (request.toLowerCase().endsWith("deadline") ||
                    request.toLowerCase().endsWith("event") ||
                    request.toLowerCase().endsWith("todo")) {
            throw new TaskEmptyException();
        }
    }

    /* Report error if the user did not give an index of task to delete.
     * @param request The request by user.
     * @throws DeleteIndexException If user request only contains "delete" but no index.
     */
    public void checkDeleteIndex(String request) throws DeleteIndexException {
        if (request.toLowerCase().endsWith("delete")) {
            throw new DeleteIndexException();
        }
    }

    /* Report error if the user did not give a keyword to search.
     * @param request The request by user.
     * @throws FindKeywordException If user request only contains "find" but no keyword.
     */
    public void checkFindKeyword (String request) throws FindKeywordException {
        if (request.toLowerCase().endsWith("find")) {
            throw new FindKeywordException();
        }
    }

    // Try adding a task and print error message when user's add request is not in correct format
    public void tryAddTask(TaskList tasks, String request) {
        try {
            checkDescription(request);
            tasks.addTask(request.trim());
        } catch (GeneralException e) {
            System.out.print(boundary + "Hmm...I'm sorry but I cannot understand this :("
                                     + System.lineSeparator() + boundary);
        } catch (TaskEmptyException e) {
            System.out.print(boundary + "Hmm...hi dear, remember to put in your task description~"
                                     + System.lineSeparator() + boundary);
        } catch (DeadlineFormatException e) {
            System.out.print(boundary + "Hmm...hi dear, when do u want to finish this by?"
                                     + System.lineSeparator() + boundary);
        } catch (DateTimeParseException e) {
            System.out.print(boundary + "Hmm...hi dear, please input your Deadline in \"description /by yyyy-mm-dd\" format"
                                     + System.lineSeparator() + boundary);
        } catch (EventFormatException e) {
            System.out.print(boundary + "Hmm...hi dear, when is this event happening?"
                                     + System.lineSeparator() + boundary);
        }
    }

    // Try deleting a task and print error message when user's delete request is not in correct format
    public void tryDeleteTask(TaskList tasks, String request) {
        try {
            checkDeleteIndex(request);
            tasks.deleteTask(Integer.parseInt(request.substring(7)) - 1);
        } catch (DeleteIndexException e) {
            System.out.print(boundary + "Hmm...hi dear, which task do you want to delete?"
                                     + System.lineSeparator() + boundary);
        }
    }

    // Trying finding a task and print error message when user's find request is not in correct format
    public void tryFindTask(TaskList tasks, String request) {
        try {
            checkFindKeyword(request);
            tasks.findTask(request.substring(5));
        } catch (FindKeywordException e) {
            System.out.print(boundary + "Hmm...hi dear, please give me a keyword to search."
                                     + System.lineSeparator() + boundary);
        }
    }
}
