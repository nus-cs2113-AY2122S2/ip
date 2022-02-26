package duke;

import duke.exceptions.*;

import java.time.format.DateTimeParseException;

public class Parser {
    public static String boundary = "____________________________________________________________" + System.lineSeparator();

    // Report error to user if he/she did not put task description
    public void checkDescription(String request) throws TaskEmptyException {
        if (request.toLowerCase().endsWith("deadline") ||
                    request.toLowerCase().endsWith("event") ||
                    request.toLowerCase().endsWith("todo")) {
            throw new TaskEmptyException();
        }
    }

    // Report error if the user did not give an index of task to delete
    public void checkDeleteIndex(String request) throws DeleteIndexException {
        if (request.toLowerCase().endsWith("delete")) {
            throw new DeleteIndexException();
        }
    }

    // Report error when user input is not in correct format
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

    public void tryDeleteTask(TaskList tasks, String request) {
        try {
            checkDeleteIndex(request);
            tasks.deleteTask(Integer.parseInt(request.substring(7)) - 1);
        } catch (DeleteIndexException e) {
            System.out.print(boundary + "Hmm...hi dear, which task do you want to delete?"
                                     + System.lineSeparator() + boundary);
        }
    }

}
