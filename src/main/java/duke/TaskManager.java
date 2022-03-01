package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

/**
 * Task Manager that manage all the task given by the user command
 * and retrieves task stored in the given text file.
 */
public class TaskManager implements Serializable {
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String BYE = "bye";
    private static final String END_OF_SECTION = "___________________________________________________";

    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static boolean isContinueInput = true;

    private static File duke;

    /**
     * A TaskManager constructor that retrieves all the task stored in the given file
     * and interpret user command.
     * @param file the given file.
     */
    public TaskManager(File file) {
        if (this.duke.length() != 0) {
            readFileContent(file);
        }
        readUserInputUntilExit();
    }

    /**
     * A method call to interpret the user command
     * @param command the representation of the command type
     * @param commandArg the command description
     * @throws DukeException if the user input the command line incorrectly.
     */
    private void taskDecoder(String command, String commandArg) throws DukeException {
        switch(command) {
        case LIST:
            printAllTasks();
            break;
        case TODO:
            addToDo(commandArg);
            break;
        case EVENT:
            addEvent(commandArg);
            break;
        case DEADLINE:
            addDeadline(commandArg);
            break;
        case MARK:
            markTask(commandArg);
            break;
        case UNMARK:
            unmarkTask(commandArg);
            break;
        case BYE:
            isContinueInput = false;
            saveData();
            break;
        case DELETE:
            deleteTask(commandArg);
            break;
        case FIND:
            findTask(commandArg);
            break;
        default:
            throw new DukeException();
        }
    }

    private void printAllTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("You have no task currently");
        } else {
            for (int i = 0; i < allTasks.size(); ++i) {
                System.out.println(String.format("%d.%s", i + 1, allTasks.get(i)));
            }
        }
        printEndLine();
    }

    private void addToDo(String commandArg) {
        if (commandArg.isEmpty()) {
            System.out.println("OOPS!! The description of a todo cannot be empty");
            printEndLine();
        } else {
            allTasks.add(new ToDo(commandArg));
            printTask();
            printEndLine();
        }
    }

    private void addEvent(String commandArg) {
        String[] eventDescription = commandArg.split("/at");
        try {
            allTasks.add(new Event(eventDescription[0], eventDescription[1]));
            printTask();
            printEndLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input format. Please follow the following format:");
            System.out.println("deadline [description] /at [date]");
            printEndLine();
        }
    }

    private void addDeadline(String commandArg) {
        String[] deadlineDescription = commandArg.split("/by");
        try {
            allTasks.add(new Deadline(deadlineDescription[0], deadlineDescription[1]));
            printTask();
            printEndLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input format. Please follow the following format:");
            System.out.println("deadline [description] /by [date]");
            printEndLine();
        }
    }

    private void markTask(String commandArg) {
        try {
            allTasks.get(Integer.parseInt(commandArg) - 1).markAsDone();
            System.out.println(String.format("Nice! I've marked this task as done:%n  %s",
                    getTask(commandArg)));
            printEndLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input the correct index to mark it as done");
            printEndLine();
        }
    }

    private void unmarkTask(String commandArg) {
        try {
            allTasks.get(Integer.parseInt(commandArg) - 1).markAsNotDone();
            System.out.println(String.format("Ok, I've marked this task as not done yet:%n  %s",
                    getTask(commandArg)));
            printEndLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input the correct index to mark it as done");
            printEndLine();
        }
    }

    private void deleteTask(String commandArg) {
        try {
            System.out.println(String.format("Noted. I've removed this task:%n  %s", getTask(commandArg)));
            allTasks.remove(Integer.parseInt(commandArg) - 1);
            printTaskRemaining();
            printEndLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input the correct index");
            printEndLine();
        }
    }

    private static ArrayList<Task> findTask(String commandArg) {
        ArrayList<Task> filteredList = (ArrayList<Task>) allTasks.stream()
                .filter((t) -> t.getDescription().contains(commandArg))
                .collect(toList());

        for (int i = 0; i < filteredList.size(); ++i) {
            System.out.println(String.format("%d.%s", i + 1, filteredList.get(i).toString()));
        }
        System.out.println(END_OF_SECTION);
        return filteredList;
    }

    /**
     * Locate and return the Task object given by the indices according to its stored position.
     * @param commandArg the index of the Task object in the stored ArrayList.
     * @return the task object
     */
    private Task getTask(String commandArg) {
        return allTasks.get(Integer.parseInt(commandArg) - 1);
    }

    private void printTaskRemaining() {
        System.out.println(String.format("Now you have %d tasks in the list.", allTasks.size()));
    }

    private void printTask() {
        System.out.println(String.format("Got it. I've added this task:%n  %s",
                allTasks.get(allTasks.size() - 1)));
        printTaskRemaining();
    }

    private void printEndLine() {
        System.out.println(END_OF_SECTION);
    }

    private void printInvalidCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means");
        printEndLine();
    }

    private void readFileContent(File file) {
        try {
            file = duke;
            String line;
            Scanner readFile = new Scanner(duke);
            for (int i = 0; readFile.hasNextLine(); ++i) {
                line = readFile.nextLine();
                String[] input = line.split(" ", 2);
                if (input[0].charAt(1) == 'T') {
                    String todoDescription = input[1].replaceAll("\\W", " ").strip();
                    allTasks.add(new ToDo(todoDescription));
                } else if (input[0].charAt(1) == 'D') {
                    String deadline = input[1].replaceAll("\\W", " ").strip();
                    String[] deadlineDescription = deadline.split("by", 2);
                    allTasks.add(new Deadline(deadlineDescription[0].strip(), deadlineDescription[1].strip()));
                } else if (input[0].charAt(1) == 'E') {
                    String event = input[1].replaceAll("\\W", " ").strip();
                    String[] eventDescription = event.split("at", 2);
                    allTasks.add(new Event(eventDescription[0].strip(), eventDescription[1].strip()));
                }
                if (input[0].length() > 4) {
                    allTasks.get(i).markAsDone();
                }
            }
            printTaskRemaining();
            printEndLine();
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("File corrupted");
        }
    }

    private void readUserInputUntilExit() {
        Scanner in = new Scanner(System.in);
        String line;

        while (isContinueInput && in.hasNextLine()) {
            line = in.nextLine();
            String[] input = line.split(" ", 2);
            String command = input[0].toLowerCase();
            String commandArg;
            if (input.length > 1) {
                commandArg = input[1];
            } else {
                commandArg = "";
            }
            try {
                taskDecoder(command, commandArg);
            } catch (DukeException e) {
                printInvalidCommand();
            }
        }
    }

    private void saveData() {
        try {
            FileWriter writeFile = new FileWriter(duke);
            System.out.println("\nSaving data");
            for (Task tasks : allTasks) {
                writeFile.write(tasks.toString() + "\n");
                System.out.println(tasks.toString());
            }
            System.out.println("Saving data end\n");
            writeFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Unable to save file");
        } catch (IOException e) {
            System.out.println("File corrupted. Unable to save file");
        }
    }
}
