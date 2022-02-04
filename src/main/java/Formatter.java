import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Formatter {

    public static String parseUserInput(String[] inputLine, int startIndex, int endIndex) {
        String[] updatedArray = Arrays.copyOfRange(inputLine, startIndex, endIndex);
        return String.join(" ", updatedArray);

    }

    public static int indexOf(String[] userInput, String toFind) {
        // if array is Null
        if (userInput == null) {
            return -1;
        }
        for (int i = 0; i < userInput.length; i++) {
            if (userInput[i].equals(toFind)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns index of task specified by user
     * Returns -1 if the index is invalid or out of range
     *
     * @param userCommand String array of userCommand
     */
    public static int getTaskIndex(String[] userCommand) {
        int taskIndex = Integer.parseInt(userCommand[1]) - 1;
        if (taskIndex >= UserList.getListSize()) {
            return -1;
        }
        return taskIndex;
    }

    public static boolean checkValidIndex(int index) {
        return index != -1;
    }

    public static void inputHandler(Scanner in) {
        String inputLine = in.nextLine();
        String[] userCommand = inputLine.split(" ");
        int taskIndex = -1;
        int numOfArgs = userCommand.length;
        boolean isValidCommand = false;
        boolean isValidIndex = false;
        String taskDescription = null;

        switch (userCommand[0]) {
        case "bye": //exit command
            DisplayMessages.closingMessage();
            System.exit(0);
            break;

        case "list":
            UserList.printList();
            break;

        case "mark":
            taskIndex = getTaskIndex(userCommand);
            if (checkValidIndex(taskIndex)) {
                UserList.markTask(taskIndex);
            } else {
                DisplayMessages.outOfBounds();
            }
            break;

        case "unmark":
            taskIndex = getTaskIndex(userCommand);
            if (checkValidIndex(taskIndex)) {
                UserList.unmarkTask(taskIndex);
            } else {
                DisplayMessages.outOfBounds();
            }
            break;

        case "todo":
            if (numOfArgs > 1) {
                isValidCommand = true;
            }
            if (isValidCommand) {
                taskDescription = parseUserInput(userCommand, 1, numOfArgs);
                Task newTask = new Task(taskDescription);
                UserList.insertTask(newTask);
            } else {
                DisplayMessages.invalidInput();
            }
            break;

        case "deadline":
            int indexOfBy = indexOf(userCommand, "/by");
            isValidIndex = indexOfBy > 1;
            if (numOfArgs >= 4 && isValidIndex) {
                isValidCommand = true;
            }
            if (isValidCommand) {
                String deadlineDescription = parseUserInput(userCommand, 1, indexOfBy);
                String deadlineDate = parseUserInput(userCommand, indexOfBy + 1, numOfArgs);
                Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
                UserList.insertTask(newDeadline);
            } else {
                DisplayMessages.invalidInput();
            }
            break;

        case "event":
            int indexOfAt = indexOf(userCommand, "/at");
            isValidIndex = indexOfAt > 1;
            if (numOfArgs >= 4 && isValidIndex) {
                isValidCommand = true;
            }
            if (isValidCommand) {
                String eventDescription = parseUserInput(userCommand, 1, indexOfAt);
                String eventDate = parseUserInput(userCommand, indexOfAt + 1, numOfArgs);
                Event newEvent = new Event(eventDescription, eventDate);
                UserList.insertTask(newEvent);
            } else {
                DisplayMessages.invalidInput();
            }
            break;

        default:
            DisplayMessages.invalidInput();
        }
    }
}
