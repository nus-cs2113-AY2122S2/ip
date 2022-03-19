package Duke;

import java.io.IOException;
import java.util.Scanner;

import static Duke.TaskList.findTask;
import static Duke.TaskList.printList;
import static Duke.TaskList.isMark;
import static Duke.TaskList.isUnmark;
import static Duke.TaskList.markAsDone;
import static Duke.TaskList.markAsUndone;
import static Duke.TaskList.addTodo;
import static Duke.TaskList.addDeadline;
import static Duke.TaskList.addEvent;
import static Duke.TaskList.deleteTask;
import static Duke.UserInput.inputCount;
import static Duke.UserInput.line;

public class Parser {
    /**
     * Returns whether the input task is todo
     * @return true if is todo
     */
    public static boolean isTodo() {
        return getFirstWord().equalsIgnoreCase("Todo");
    }

    /**
     * Returns the command type
     * @return the first word/ command type
     */
    public static String getFirstWord() throws StringIndexOutOfBoundsException {
        return (line.substring(0, line.indexOf(" ")));
    }

    /**
     * Returns whether the input task is deadline
     * @return true if is deadline
     */
    public static boolean isDeadline() {
        return getFirstWord().equalsIgnoreCase("Deadline");
    }

    /**
     * Returns whether the input task is event
     * @return true if is event
     */
    public static boolean isEvent() {
        return getFirstWord().equalsIgnoreCase("Event");
    }

    /**
     * Returns whether the input command is delete
     * @return true if is delete
     */
    public static boolean isDelete() {
        return getFirstWord().equalsIgnoreCase("Delete");
    }

    /**
     * Returns whether the input command is bye
     * @return true if is bye
     */
    public static boolean isBye() {
        return (line.trim()).equalsIgnoreCase("bye");
    }

    /**
     * Returns whether the input value is within the range of quantity of task
     * @return true if is valid
     */
    public static boolean checkValidity() throws NumberFormatException {
        return 0 < Integer.parseInt(line.substring(line.indexOf(" ") + 1))
                && Integer.parseInt(line.substring(line.indexOf(" ") + 1)) <= inputCount;
    }

    /**
     * Returns whether the input command is find
     * @return true if is find
     */
    public static boolean isFind() {
        try {
            return getFirstWord().equalsIgnoreCase("Find");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid command!");
        }
        return false;
    }

    /**
     * Returns whether the input command is list
     * @return true if is list
     */
    public static boolean isList() {
        return (line.trim()).equalsIgnoreCase("list");
    }

    /**
     * Checks whether the format of user input is correct
     * @param input input of users
     * @throws InvalidInputException if command of user is invalid
     */
    public static void checkCommandValidity(Scanner input) throws InvalidInputException {
        line = input.nextLine().trim();
        if (line.equalsIgnoreCase("todo") || line.equalsIgnoreCase("find")
                || line.equalsIgnoreCase("deadline") || line.equalsIgnoreCase("event")
                || line.equalsIgnoreCase("delete") || line.equalsIgnoreCase("unmark")
                || line.equalsIgnoreCase("mark")) {
            line += ' ';
        } else if (!isList() && !isBye() && !line.contains(" ")) {
            throw new InvalidInputException();
        } else if (!isList() && !isBye() && line.contains(" ")) {
            if (!validFirstWord())
                throw new InvalidInputException();
            else if (getFirstWord().equalsIgnoreCase("list"))
                System.out.println("there should not be any arguments after 'list' command!");
            else if (getFirstWord().equalsIgnoreCase("bye"))
                System.out.println("there should not be any arguments after 'bye' command!");
        }
    }

    /**
     * Returns true if the first word is a valid command, false otherwise.
     * @return true if the first word is a valid command
     */
    public static boolean validFirstWord() {
        return getFirstWord().equalsIgnoreCase("delete")
                || getFirstWord().equalsIgnoreCase("unmark")
                || getFirstWord().equalsIgnoreCase("list")
                || getFirstWord().equalsIgnoreCase("bye")
                || getFirstWord().equalsIgnoreCase("mark")
                || getFirstWord().equalsIgnoreCase("deadline")
                || getFirstWord().equalsIgnoreCase("event")
                || getFirstWord().equalsIgnoreCase("todo")
                || getFirstWord().equalsIgnoreCase("find");
    }

    /**
     * Handles the command
     * @throws IOException
     */
    public static void handleCommand() throws IOException {
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                checkCommandValidity(input);
            } catch (InvalidInputException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (isList()) {
                printList();
                continue;
            } else if (isBye()) {
                break;
            }  else if (isFind()) {
                try {
                    findTask();
                    continue;
                } catch (InvalidInputException e) {
                    System.out.println("Please input what you want to search!");
                }
            } else if (line.contains(" ")) {
                if (isMark()) {
                    markCommand();
                } else if (isUnmark()) {
                    unmarkCommand();
                } else if (isTodo()) {
                    todoCommand();
                } else if (isDeadline()) {
                    deadlineCommand();
                } else if (isEvent()) {
                    eventCommand();
                } else if (isDelete()) {
                    deleteCommand();
                }
            }
            Storage.writeToFile();
        }
    }

    public static void markCommand() {
        try {
            if (checkValidity()) {
                markAsDone();
            } else {
                System.out.println("Invalid Index!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the index of task you want to mark!");
        }
    }

    public static void unmarkCommand() {
        try {
            if (checkValidity()) {
                markAsUndone();
            } else {
                System.out.println("Invalid Index!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the index of task you want to unmark!");
        }
    }

    public static void todoCommand() {
        try {
            addTodo();
        } catch (InvalidInputException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void deadlineCommand() {
        try {
            addDeadline();
        } catch (InvalidInputException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please set the deadline using 'deadline /by time' format!!!");
        }
    }

    public static void eventCommand() {
        try {
            addEvent();
        } catch (InvalidInputException e) {
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please set the event time using 'event /at time'  format!!!");
        }
    }

    public static void deleteCommand() {
        try {
            try {
                if (!checkValidity())
                    System.out.println("Invalid Index!");
                else
                    deleteTask();
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! What exactly do you want to delete?");
            }
        } catch (InvalidInputException e) {
            System.out.println("☹ OOPS!!! What exactly do you want to delete?");
        }
    }
}
