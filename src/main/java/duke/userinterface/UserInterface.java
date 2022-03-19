package duke.userinterface;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Shows messages Duke has for the user in various situations.
 * Contains methods to extract specific information in user input for command processing.
 */
public class UserInterface {

    public void printLine() {
        System.out.println("------------------------------");
    }

    public void printGreeting() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("Type in 'help' to get started. I'll slowly guide you along the way :)");
        System.out.println("What can I do for you?");
    }

    public void printByeMessage() {
        System.out.println("Any tasks currently in the list have been saved.");
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printNoCommandMessage() {
        System.out.println("Well, I'll wait for a command..");
    }

    public void printHelpMessage() {
        System.out.println("There are currently nine keyword commands: ");
        System.out.println("1. list, 2. todo, 3. deadline, 4.event, 5. mark, 6. unmark, 7. delete, 8. find, 9. bye");
    }

    public void printEmptyDescriptionMessage() {
        System.out.println("Oops! Please type in a description!");
    }

    public void printEmptyTimingDetailsMessage() {
        System.out.println("Oops! Please indicate the time only in this format: YYYY-MM-DD HH:MM");
        System.out.println("Also, the time cannot be before right now.");
    }

    public void printIOExceptionMessageLoad() {
        System.out.println("Oops! Something went wrong with loading the data!");
    }

    public void printIOExceptionMessageWrite() {
        System.out.println("Oops! Something went wrong with writing the data!");
    }

    public void printIOExceptionMessageRead() {
        System.out.println("Oops! Something went wrong with reading the data!");
    }

    public void printOutOfBoundsExceptionMessage(ArrayList<Task> tasks) {
        if (tasks.size() > 0){
            System.out.println("Oops! Please give a positive integer value up to " + tasks.size()
                    + " only. Try again!");
        } else {
            System.out.println("The list is currently empty. Try again!");
        }
    }

    public void printNumberFormatExceptionMessage() {
        System.out.println("Oops! Please give an integer value. Try again!");
    }

    public void printTasks(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        } else {
            System.out.println("Wow, such empty.");
        }
    }

    public void printTask(ArrayList<Task> tasks, int number) {
        System.out.println("Noted. I've removed this task:\n" + tasks.get(number).toString());
    }

    public void printCreateSaveDataDirectoryMessage() {
        System.out.println("NOTE: Data directory not found. " +
                "Duke will create the folder in the current directory now.");
    }

    public void printCreateSaveDataFileMessage() {
        System.out.println("NOTE: Data file not found. Duke will create the file now.");
    }

    public void printSaveDataFileInitializedMessage() {
        System.out.println("NOTE: The saved data has been initialized!");
    }

    /**
     * prints out all tasks that contain the matching keyword(s)
     * @param tasks processed list of tasks that contain the matching keyword(s)
     */
    public void printMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        } else {
            System.out.println("There are no tasks in the current list that matches the keywords given!");
        }
    }

    public void printMessageForAdding(ArrayList<Task> tasks, Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.size() + "." + task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void printMarkOrUnmarkMessage(ArrayList<Task> tasks, String command, int number) {
        if (command.equals("mark")) {
            tasks.get(number).setIsMarked();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            tasks.get(number).unsetIsMarked();
            System.out.println("Nice! I've marked this task as not done:");
        }
        System.out.println(number + 1 + "." + tasks.get(number).toString());
    }

    public String getCommand(String userInput) {
        String[] words = userInput.split(" ");
        String command = words[0];
        return command;
    }

    /**
     * Extracts out the description portion of the user input from the accepted input formats.
     * @param userInput
     * @return
     * @throws EmptyDescriptionException
     */
    public String getDescription(String userInput) throws EmptyDescriptionException {
        try {
            int indexOfDescription = userInput.indexOf(" ");
            int indexOfSlash = userInput.indexOf("/");
            String description = "";
            if (indexOfSlash != -1) {
                description = userInput.substring(indexOfDescription, indexOfSlash);
            } else {
                description = userInput.substring(indexOfDescription);
            }
            if (description.equals(" ")) {
                throw new EmptyDescriptionException();
            }
            return description;
        } catch (StringIndexOutOfBoundsException | EmptyDescriptionException e) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Extracts out timing information from user input from the accepted input formats.
     * @param userInput
     * @return
     * @throws EmptyTimingDetailsException
     */
    public String getTimingDetails(String userInput) throws EmptyTimingDetailsException {
        int indexOfSlash = userInput.indexOf("/");
        int indexOfTimingDetails = indexOfSlash + 4;
        String timingDetails;
        LocalDateTime dateTime;
        if (indexOfSlash != -1 && indexOfTimingDetails <= userInput.length()) {
            timingDetails = userInput.substring(indexOfTimingDetails);
        } else {
            throw new EmptyTimingDetailsException();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateTime =  LocalDateTime.parse(timingDetails, formatter);
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new EmptyTimingDetailsException();
            }
            return timingDetails;
        } catch (DateTimeParseException e) {
            throw new EmptyTimingDetailsException();
        }
    }

    public int getTaskNumber(String userInput) {
        String[] words = userInput.split(" ");
        String taskNumber = words[1];
        int number = Integer.parseInt(taskNumber) - 1;
        return number;
    }
}
