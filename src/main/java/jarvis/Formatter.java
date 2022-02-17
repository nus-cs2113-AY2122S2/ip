package jarvis;

import jarvis.commands.Deadline;
import jarvis.commands.Event;
import jarvis.commands.Task;
import jarvis.commands.UserList;
import jarvis.display.DisplayMessages;
import jarvis.exceptions.JarvisException;

import java.util.Arrays;
import java.util.Scanner;

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

    protected static boolean isValidIndex(int index) {
        return index != -1;
    }

    protected static boolean isValidCommand(int numOfArgs) {
        return numOfArgs > 1;
    }
    protected static void markCommand(String[] userCommand) throws JarvisException {
        int taskIndex = getTaskIndex(userCommand);
        if (isValidIndex(taskIndex)) {
            UserList.markTask(taskIndex, true);
        } else {
            throw new JarvisException();
        }
    }

    protected static void unmarkCommand(String[] userCommand) throws JarvisException {
        int taskIndex = getTaskIndex(userCommand);
        if (isValidIndex(taskIndex)) {
            UserList.unmarkTask(taskIndex);
        } else {
            throw new JarvisException();
        }
    }

    protected static void todoCommand(String[] userCommand) throws JarvisException {
        int numOfArgs = userCommand.length;
        if (isValidCommand(numOfArgs)) {
            String taskDescription = parseUserInput(userCommand, 1, numOfArgs);
            Task newTask = new Task(taskDescription);
            UserList.insertTask(newTask);
        } else {
           throw new JarvisException();
        }
    }

    protected static void deadlineCommand(String[] userCommand) throws JarvisException {
        int indexOfBy = indexOf(userCommand, "/by");
        int numOfArgs = userCommand.length;
        boolean isValidIndex = indexOfBy > 1;
        boolean hasSufficientArgs = numOfArgs >= 4;

        if (isValidIndex && hasSufficientArgs) {
            String deadlineDescription = parseUserInput(userCommand, 1, indexOfBy);
            String deadlineDate = parseUserInput(userCommand, indexOfBy + 1, numOfArgs);
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            UserList.insertTask(newDeadline);
        } else {
            throw new JarvisException();
        }
    }

    protected static void eventCommand(String[] userCommand) throws JarvisException {
        int indexOfAt = indexOf(userCommand, "/at");
        int numOfArgs = userCommand.length;
        boolean isValidIndex = indexOfAt > 1;
        boolean hasSufficientArgs = numOfArgs >= 4;

        if (isValidIndex && hasSufficientArgs) {
            String eventDescription = parseUserInput(userCommand, 1, indexOfAt);
            String eventDate = parseUserInput(userCommand, indexOfAt + 1, numOfArgs);
            Event newEvent = new Event(eventDescription, eventDate);
            UserList.insertTask(newEvent);
        } else {
            throw new JarvisException();
        }
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
            DisplayMessages.savingData();
            UserList.saveData();
            DisplayMessages.closingMessage();
            System.exit(0);
            break;

        case "list":
            UserList.printList();
            break;

        case "mark":
            try {
                markCommand(userCommand);
            } catch (JarvisException e) {
                DisplayMessages.outOfBounds();
            }
            break;

        case "unmark":
            try {
                unmarkCommand(userCommand);
            } catch (JarvisException e) {
                DisplayMessages.outOfBounds();
            }
            break;

        case "todo":
            try {
                todoCommand(userCommand);
            } catch(JarvisException e) {
                DisplayMessages.invalidInput();
            }
            break;

        case "deadline":
            try {
                deadlineCommand(userCommand);
            } catch(JarvisException e) {
                DisplayMessages.invalidInput();
            }
            break;

        case "event":
            try {
                eventCommand(userCommand);
            } catch (JarvisException e) {
                DisplayMessages.invalidInput();
            }
            break;

        default:
            DisplayMessages.invalidInput();
        }
    }
}
