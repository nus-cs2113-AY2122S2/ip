package duke.parser;

import duke.TaskList;
import duke.exception.AdditionalException;

public class Parser {

    public static void filter(String request, String[] words, String command, boolean isNewRequest)
                throws AdditionalException {
        switch(command) {
        case "list":
            TaskList.printList();
            break;
        case "mark":
            TaskList.markOrDeleteItem(words, "mark", isNewRequest);
            break;
        case "unmark":
            TaskList.markOrDeleteItem(words, "unmark", isNewRequest);
            break;
        case "delete":
            TaskList.markOrDeleteItem(words, "delete", isNewRequest);
            break;
        case "todo":
            TaskList.addTask(request, "todo", isNewRequest);
            printConfirmationIfNewRequest(isNewRequest);
            break;
        case "deadline":
            TaskList.addTask(request, "deadline", "/by", isNewRequest);
            printConfirmationIfNewRequest(isNewRequest);
            break;
        case "event":
            TaskList.addTask(request, "event", "/at", isNewRequest);
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
