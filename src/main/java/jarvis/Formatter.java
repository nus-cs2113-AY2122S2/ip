package jarvis;

import jarvis.commands.Deadline;
import jarvis.commands.Event;
import jarvis.commands.Task;
import jarvis.commands.UserList;
import jarvis.display.DisplayMessages;
import jarvis.exceptions.JarvisInvalidInput;
import jarvis.exceptions.JarvisOutOfBounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Formatter {

    public static String parseUserInput(String[] inputLine, int startIndex, int endIndex) {
        String[] updatedArray = Arrays.copyOfRange(inputLine, startIndex, endIndex);
        return String.join(" ", updatedArray);

    }

    public static int indexOf(String[] userInput, String toFind) {
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
    protected static void markCommand(String[] userCommand) throws JarvisOutOfBounds {
        int taskIndex = getTaskIndex(userCommand);
        if (isValidIndex(taskIndex)) {
            UserList.markTask(taskIndex, true);
        } else {
            throw new JarvisOutOfBounds();
        }
    }

    protected static void unmarkCommand(String[] userCommand) throws JarvisOutOfBounds {
        int taskIndex = getTaskIndex(userCommand);
        if (isValidIndex(taskIndex)) {
            UserList.unmarkTask(taskIndex);
        } else {
            throw new JarvisOutOfBounds();
        }
    }

    protected static void todoCommand(String[] userCommand) throws JarvisInvalidInput {
        int numOfArgs = userCommand.length;
        if (isValidCommand(numOfArgs)) {
            String taskDescription = parseUserInput(userCommand, 1, numOfArgs);
            Task newTask = new Task(taskDescription);
            UserList.insertTask(newTask);
        } else {
           throw new JarvisInvalidInput();
        }
    }

    protected static void deadlineCommand(String[] userCommand) throws JarvisInvalidInput {
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
            throw new JarvisInvalidInput();
        }
    }

    protected static void eventCommand(String[] userCommand) throws JarvisInvalidInput {
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
            throw new JarvisInvalidInput();
        }
    }

    protected static void deleteCommand(String[] userCommand) throws JarvisOutOfBounds {
        try {
            Integer taskIndex = Integer.parseInt(userCommand[1]);
            UserList.removeTask(taskIndex - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException er){
            throw new JarvisOutOfBounds();
        }
    }

    private static void printList(ArrayList<Task> list) {
        DisplayMessages.horizontalLine();
        System.out.println("Here are the matching tasks in your list:\n");
        Integer index = 1;
        for (Task t : list) {
            System.out.println(index.toString() + ". " + t.getFullTask());
            index++;
        }
        DisplayMessages.horizontalLine();
    }

    protected static void findCommand(String[] userCommand) throws JarvisInvalidInput {
        if (userCommand.length < 2) {
            throw new JarvisInvalidInput();
        } else {
            String keyword = parseUserInput(userCommand, 1, userCommand.length);
            ArrayList<Task> resultList = UserList.getSearchResult(keyword);
            if (resultList.isEmpty()) {
                DisplayMessages.emptySearchResult();
            } else {
                printList(resultList);
            }
        }
    }

    public static void inputHandler(Scanner in) {
        String inputLine = in.nextLine();
        String[] userCommand = inputLine.split(" ");
        try {
            switch (userCommand[0]) {
            case "bye":
                DisplayMessages.savingData();
                UserList.saveData();
                DisplayMessages.closingMessage();
                System.exit(0);
                break;

            case "list":
                UserList.printList();
                break;

            case "mark":
                markCommand(userCommand);
                break;

            case "unmark":
                unmarkCommand(userCommand);
                break;

            case "todo":
                todoCommand(userCommand);
                break;

            case "deadline":
                deadlineCommand(userCommand);
                break;

            case "event":
                eventCommand(userCommand);
                break;

            case "delete":
                deleteCommand(userCommand);
                break;
            case "find":
                findCommand(userCommand);
                break;
            default:
                DisplayMessages.invalidInput();
            }
        } catch (JarvisInvalidInput e) {
            DisplayMessages.invalidInput();
        } catch (JarvisOutOfBounds e) {
            DisplayMessages.outOfBounds();
        }
    }
}
