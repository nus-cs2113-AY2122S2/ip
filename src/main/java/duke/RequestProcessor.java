package duke;

import duke.exception.AdditionalException;

public class RequestProcessor {

    public static boolean filterRequest(String request) throws AdditionalException {
        boolean isBye = false;
        String[] words = request.split(" ");
        String command = words[0].toLowerCase();
        switch(command) {
        case "bye":
            isBye = true;
            break;
        case "list":
            TaskManager.printList();
            break;
        case "mark":
            TaskManager.markOrDeleteItem(words, "mark");
            break;
        case "unmark":
            TaskManager.markOrDeleteItem(words, "unmark");
            break;
        case "delete":
            TaskManager.markOrDeleteItem(words, "delete");
            break;
        case "todo":
            TaskManager.addTask(request, "todo");
            TaskManager.printConfirmationForAddingTasks();
            break;
        case "deadline":
            TaskManager.addTask(request, "deadline", "/by");
            TaskManager.printConfirmationForAddingTasks();
            break;
        case "event":
            TaskManager.addTask(request, "event", "/at");
            TaskManager.printConfirmationForAddingTasks();
            break;
        default:
            throw new AdditionalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return isBye;
    }
}
