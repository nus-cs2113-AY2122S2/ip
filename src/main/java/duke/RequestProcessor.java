package duke;

import duke.exception.AdditionalException;

public class RequestProcessor {

    public static boolean filterNewRequest(String request) throws AdditionalException {
        boolean isBye = false;
        String[] words = words = request.split(" ");
        String command = words[0].toLowerCase();
        if (command.equalsIgnoreCase("bye")) {
            isBye = true;
        } else {
            filter(request, words, command, true);
        }
        return isBye;
    }

    private static void filter(String request, String[] words, String command, boolean isNewRequest)
                throws AdditionalException {
        switch(command) {
        case "list":
            TaskManager.printList();
            break;
        case "mark":
            TaskManager.markOrDeleteItem(words, "mark", isNewRequest);
            break;
        case "unmark":
            TaskManager.markOrDeleteItem(words, "unmark", isNewRequest);
            break;
        case "delete":
            TaskManager.markOrDeleteItem(words, "delete", isNewRequest);
            break;
        case "todo":
            TaskManager.addTask(request, "todo", isNewRequest);
            printConfirmationIfNewRequest(isNewRequest);
            break;
        case "deadline":
            TaskManager.addTask(request, "deadline", "/by", isNewRequest);
            printConfirmationIfNewRequest(isNewRequest);
            break;
        case "event":
            TaskManager.addTask(request, "event", "/at", isNewRequest);
            printConfirmationIfNewRequest(isNewRequest);
            break;
        default:
            throw new AdditionalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void printConfirmationIfNewRequest(boolean isNewRequest) {
        if (isNewRequest) {
            TaskManager.printConfirmationForAddingTasks();
        }
    }

    public static void filterRequestsFromFile(String nextLine, int taskNumber) throws AdditionalException {
        String[] words = words = nextLine.split("\\|");
        String command = words[0].toLowerCase();
        String toMark = words[1].toLowerCase();
        String[] actualRequest = new String[words.length - 1];
        actualRequest[0] = command;
        String request = command;
        request = removeToMark(words, actualRequest, request);
        filter(request, actualRequest, command, false);
        updateTaskIfMark(toMark, taskNumber);
    }

    private static void updateTaskIfMark(String toMark, int taskNumber) throws AdditionalException {
        String request;
        if (toMark.equalsIgnoreCase("true")) {
            String[] actualRequest = new String[2];
            actualRequest[0] = "mark";
            actualRequest[1] = Integer.toString(taskNumber);
            request = "mark " + Integer.toString(taskNumber);
            filter(request, actualRequest, "mark", false);
        }
    }

    private static String removeToMark(String[] words, String[] actualRequest, String request) {
        for (int i = 2; i < words.length; i++) {
            actualRequest[i - 1] = words[i];
            request += " ";
            request += words[i];
        }
        return request;
    }

}
