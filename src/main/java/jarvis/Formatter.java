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
    /**
     *  This function selects a range of elements in the given inputLine argument, and returns the element joined in
     *  a single String. This is used for handling user inputs
     *
     * @param inputLine String array of given input by user
     * @param startIndex The starting index of which element(s) to include, inclusive of
     * @param endIndex The terminating index of which element to include, not inclusive of
     * @return Specified Elements of inputLine[], combined into a String
     */
    public static String parseUserInput(String[] inputLine, int startIndex, int endIndex) {
        String[] updatedArray = Arrays.copyOfRange(inputLine, startIndex, endIndex);
        return String.join(" ", updatedArray);

    }

    /**
     * This function searches a keyword in the user given command and returns the index of the keyword.
     * Used to find '/at' and '/by' for Events and Deadlines.
     *
     * @param userInput String array of user input
     * @param toFind Keyword to find, either '/at' or '/by'
     * @return an integer representing the index of the keyword. Returns -1 if not present.
     */

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
     * Returns index of task specified by user.
     * Returns -1 if the index is invalid or out of range.
     * Function is used for 'mark' and 'unmark' commands
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

    /**
     * Function to implement SLAP methodology, checks if index is valid and returns True if it is. False otherwise.
     *
     * @param index index to be checked
     * @return A boolean to indicate if index is valid
     */

    protected static boolean isValidIndex(int index) {
        return index != -1;
    }

    /**
     * Function to implement SLAP methodology, checks if numOfArgs is valid and returns True if it is. False otherwise.
     *
     * @param numOfArgs number of arguments to be checked
     * @return A boolean to indicate if the number of arguments is valid
     */
    protected static boolean isValidCommand(int numOfArgs) {
        return numOfArgs > 1;
    }

    /**
     * Function is the main driver for the 'mark' command. Retrieves index specified by user and marks it through
     * the UserList.markTask(...) method.
     *
     * @param userCommand String array of the line of user input
     * @throws JarvisOutOfBounds an exception the index specified is not valid given the user's current list
     */
    protected static void markCommand(String[] userCommand) throws JarvisOutOfBounds {
        int taskIndex = getTaskIndex(userCommand);
        if (isValidIndex(taskIndex)) {
            UserList.markTask(taskIndex, true);
        } else {
            throw new JarvisOutOfBounds();
        }
    }

    /**
     * Function is the main driver for the 'unmark' command. Retrieves index specified by user and marks it through
     * the UserList.unmarkTask(...) method.
     *
     * @param userCommand String array of the line of user input
     * @throws JarvisOutOfBounds an exception the index specified is not valid given the user's current list
     */
    protected static void unmarkCommand(String[] userCommand) throws JarvisOutOfBounds {
        int taskIndex = getTaskIndex(userCommand);
        if (isValidIndex(taskIndex)) {
            UserList.unmarkTask(taskIndex);
        } else {
            throw new JarvisOutOfBounds();
        }
    }

    /**
     * Main driver function for adding a Task to the user's list. Throws an exception if the user given input is not
     * valid.
     *
     * @param userCommand String array of the line of user input
     * @throws JarvisInvalidInput exception to indicate that user's given input is not valid
     */
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

    /**
     * Main driver function for adding a Deadline to the user's list. Throws an exception if the user given input is not
     * valid.
     *
     * @param userCommand String array of the line of user input
     * @throws JarvisInvalidInput exception to indicate that user's given input is not valid
     */
    protected static void deadlineCommand(String[] userCommand) throws JarvisInvalidInput {
        int indexOfBy = indexOf(userCommand, "/by");
        int numOfArgs = userCommand.length;
        boolean isValidIndex = indexOfBy > 1;
        boolean hasSufficientArgs = numOfArgs >= 4;

        if (isValidIndex && hasSufficientArgs) {
            String deadlineDescription = parseUserInput(userCommand, 1, indexOfBy);
            String deadlineDate = userCommand[indexOfBy + 1];
            String deadlineTime = userCommand[indexOfBy + 2];
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate, deadlineTime);
            if (newDeadline.getDeadlineDate() != "") {
                UserList.insertTask(newDeadline);
            }
        } else {
            throw new JarvisInvalidInput();
        }
    }

    /**
     * Main driver function for adding an Event to the user's list. Throws an exception if the user given input is not
     * valid
     *
     * @param userCommand String array of the line of user input
     * @throws JarvisInvalidInput exception to indicate that user's given input is not valid
     */
    protected static void eventCommand(String[] userCommand) throws JarvisInvalidInput {
        int indexOfAt = indexOf(userCommand, "/at");
        int numOfArgs = userCommand.length;
        boolean isValidIndex = indexOfAt > 1;
        boolean hasSufficientArgs = numOfArgs >= 5;

        if (isValidIndex && hasSufficientArgs) {
            String eventDescription = parseUserInput(userCommand, 1, indexOfAt);
            String eventDay = userCommand[indexOfAt + 1];
            String eventTime = userCommand[indexOfAt + 2];
            Event newEvent = new Event(eventDescription, eventDay, eventTime);
            if (newEvent.getEventDate() != "") {
                UserList.insertTask(newEvent);
            }
        } else {
            throw new JarvisInvalidInput();
        }
    }

    /**
     * Main driver function for removing a Task from user's list. Throws an exception if the user specified index is not
     * valid
     *
     * @param userCommand String array of the line of user input
     * @throws JarvisOutOfBounds exception to indicate that user's given input is not valid
     */
    protected static void deleteCommand(String[] userCommand) throws JarvisOutOfBounds {
        try {
            Integer taskIndex = Integer.parseInt(userCommand[1]);
            UserList.removeTask(taskIndex - 1, true);
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
    /**
     * This function handles user input and is constantly looped by main driver function in the Jarvis
     * file. Calls other command main driver function after parsing user input as String array. Includes error-handling
     * for invalid input through catching of exceptions
     *
     * @param in Scanner argument to read next line of user input
     */
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
