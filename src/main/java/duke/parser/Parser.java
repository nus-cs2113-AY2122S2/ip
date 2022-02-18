package duke.parser;

import duke.tasklist.AddTask;
import duke.tasklist.ListTask;
import duke.tasklist.MarkOrDeleteTask;
import duke.tasklist.TaskList;
import duke.exception.AdditionalException;

public class Parser {

    public static void filter(String request, String[] words, String command, boolean isNewRequest)
                throws AdditionalException {
        switch(command) {
        case "list":
            ListTask.printList();
            break;
        case "mark":
            MarkOrDeleteTask.markOrDeleteItem(words, "mark", isNewRequest);
            break;
        case "unmark":
            MarkOrDeleteTask.markOrDeleteItem(words, "unmark", isNewRequest);
            break;
        case "delete":
            MarkOrDeleteTask.markOrDeleteItem(words, "delete", isNewRequest);
            break;
        case "todo":
            AddTask.addTask(request, "todo", isNewRequest);
            printConfirmationIfNewRequest(isNewRequest);
            break;
        case "deadline":
            AddTask.addTask(request, "deadline", "/by", isNewRequest);
            printConfirmationIfNewRequest(isNewRequest);
            break;
        case "event":
            AddTask.addTask(request, "event", "/at", isNewRequest);
            printConfirmationIfNewRequest(isNewRequest);
            break;
        default:
            throw new AdditionalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void printConfirmationIfNewRequest(boolean isNewRequest) {
        if (isNewRequest) {
            TaskList.printConfirmationForAddingTasks();
        }
    }

}
