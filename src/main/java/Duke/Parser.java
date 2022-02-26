package Duke;

import java.io.IOException;
import java.util.Scanner;
import static Duke.UserInput.*;
import static Duke.TaskList.*;

public class Parser {
    public static boolean isTodo() {
        return getFirstWord().equalsIgnoreCase("Todo");
    }

    public static String getFirstWord() throws StringIndexOutOfBoundsException {
        return (line.substring(0, line.indexOf(" ")));
    }

    public static boolean isDeadline() {
        return getFirstWord().equalsIgnoreCase("Deadline");
    }

    public static boolean isEvent() {
        return getFirstWord().equalsIgnoreCase("Event");
    }

    public static boolean isDelete() {
        return getFirstWord().equalsIgnoreCase("Delete");
    }

    public static boolean isBye() {
        return (line.trim()).equalsIgnoreCase("bye");
    }

    public static boolean checkValidity() throws NumberFormatException {
        return Integer.parseInt(line.substring(line.indexOf(" ") + 1)) <= inputCount;
    }

    public static boolean isFind() {
        return getFirstWord().equalsIgnoreCase("Find");
    }

    public static boolean isList() {
        return (line.trim()).equalsIgnoreCase("list");
    }

    public static void checkCommandValidity(Scanner input) throws InvalidInputException {
        line = input.nextLine().trim();
        if (line.equalsIgnoreCase("todo") || line.equalsIgnoreCase("find") || line.equalsIgnoreCase("deadline") || line.equalsIgnoreCase("event") || line.equalsIgnoreCase("delete") || line.equalsIgnoreCase("unmark") || line.equalsIgnoreCase("mark")) {
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

    public static boolean validFirstWord() {
        return getFirstWord().equalsIgnoreCase("delete") || getFirstWord().equalsIgnoreCase("unmark") ||
                getFirstWord().equalsIgnoreCase("list") || getFirstWord().equalsIgnoreCase("bye") ||
                getFirstWord().equalsIgnoreCase("mark") || getFirstWord().equalsIgnoreCase("deadline") ||
                getFirstWord().equalsIgnoreCase("event") || getFirstWord().equalsIgnoreCase("todo") ||
                getFirstWord().equalsIgnoreCase("find");
    }

    public static void handleCommand() throws IOException, InvalidInputException {
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
                    try {
                        if (checkValidity()) {
                            markAsDone();
                        } else {
                            System.out.println("Invalid Index!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter the index of task you want to mark!");
                    }
                } else if (isUnmark()) {
                    try {
                        if (checkValidity()) {
                            markAsUndone();
                        } else {
                            System.out.println("Invalid Index!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter the index of task you want to unmark!");
                    }
                } else if (isTodo()) {
                    try {
                        addTodo();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (isDeadline()) {
                    try {
                        addDeadline();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } catch (NumberFormatException e) {
                        System.out.println("☹ OOPS!!! Please set the deadline using 'deadline /by time' format!!!");
                    }
                } else if (isEvent()) {
                    try {
                        addEvent();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    } catch (NumberFormatException e) {
                        System.out.println("☹ OOPS!!! Please set the event time using 'event /at time'  format!!!");
                    }
                } else if (isDelete()) {
                    try {
                        try {
                            if (!checkValidity())
                                System.out.println("☹ OOPS!!! You do not have this task.");
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
            Storage.writeToFile();
        }
    }
}
