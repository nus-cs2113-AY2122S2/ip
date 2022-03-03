package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

/**
 * Task Manager that manage all the task given by the user command
 * and retrieves task stored in the given text file.
 */
public class TaskManager {
    private static ArrayList<Task> allTasks = new ArrayList<>();

    private static File duke;
    private Parser decodeFile;
    private Parser decodeUserInput;

    /**
     * A TaskManager constructor that retrieves all the task stored in the given file
     * and interpret user command.
     * @param file the given file.
     */
    public TaskManager(File file) {
        duke = file;
        decodeFile = new Parser(file);
        if (duke.length() != 0) {
            decodeFile.readFileContent();
        }
        decodeUserInput = new Parser(new Scanner(System.in));
        decodeUserInput.readUntilEndOfInput();
    }

    public static void printAllTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("You have no task currently");
        } else {
            for (int i = 0; i < allTasks.size(); ++i) {
                System.out.println(String.format("%d.%s", i + 1, allTasks.get(i)));
            }
        }
        Ui.setEndOfSection();
    }

    public static void addToDo(String commandArg) {
        if (commandArg.isEmpty()) {
            System.out.println("OOPS!! The description of a todo cannot be empty");
            Ui.setEndOfSection();
        } else {
            allTasks.add(new ToDo(commandArg));
            printTask();
            Ui.setEndOfSection();
        }
    }

    public static void addToDo(String toDoDescription, String placeHolder) {
        allTasks.add(new ToDo(toDoDescription));
    }
    public static void addEvent(String commandArg) {
        String[] eventDescription = commandArg.split("/at");
        try {
            allTasks.add(new Event(eventDescription[0], eventDescription[1]));
            printTask();
            Ui.setEndOfSection();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input format. Please follow the following format:");
            System.out.println("deadline [description] /at [date]");
            Ui.setEndOfSection();
        }
    }

    public static void addEvent(String eventDescription, String eventTime) {
        allTasks.add(new Event(eventDescription, eventTime));
    }

    public static void addDeadline(String commandArg) {
        String[] deadlineDescription = commandArg.split("/by");
        try {
            allTasks.add(new Deadline(deadlineDescription[0], deadlineDescription[1]));
            printTask();
            Ui.setEndOfSection();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input format. Please follow the following format:");
            System.out.println("deadline [description] /by [date]");
            Ui.setEndOfSection();
        }
    }

    public static void addDeadline(String deadlineDescription, String deadlineTime) {
        allTasks.add(new Deadline(deadlineDescription, deadlineTime));
    }

    public static void markTask(String commandArg) {
        try {
            allTasks.get(Integer.parseInt(commandArg) - 1).markAsDone();
            System.out.println(String.format("Nice! I've marked this task as done:%n  %s",
                    getTask(commandArg)));
            Ui.setEndOfSection();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input the correct index to mark it as done");
            Ui.setEndOfSection();
        }
    }

    public static void unmarkTask(String commandArg) {
        try {
            allTasks.get(Integer.parseInt(commandArg) - 1).markAsNotDone();
            System.out.println(String.format("Ok, I've marked this task as not done yet:%n  %s",
                    getTask(commandArg)));
            Ui.setEndOfSection();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input the correct index to mark it as done");
            Ui.setEndOfSection();
        }
    }

    public static void deleteTask(String commandArg) {
        try {
            System.out.println(String.format("Noted. I've removed this task:%n  %s", getTask(commandArg)));
            allTasks.remove(Integer.parseInt(commandArg) - 1);
            printTaskRemaining();
            Ui.setEndOfSection();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input the correct index");
            Ui.setEndOfSection();
        }
    }

    public static ArrayList<Task> findTask(String commandArg) {
        ArrayList<Task> filteredList = (ArrayList<Task>) allTasks.stream()
                .filter((t) -> t.getDescription().contains(commandArg))
                .collect(toList());

        for (int i = 0; i < filteredList.size(); ++i) {
            System.out.println(String.format("%d.%s", i + 1, filteredList.get(i).toString()));
        }
        Ui.setEndOfSection();
        return filteredList;
    }

    /**
     * Locate and return the Task object given by the indices according to its stored position.
     * @param commandArg the index of the Task object in the stored ArrayList.
     * @return the task object
     */
    public static Task getTask(String commandArg) {
        return allTasks.get(Integer.parseInt(commandArg) - 1);
    }

    public static void printTaskRemaining() {
        System.out.println(String.format("Now you have %d tasks in the list.", allTasks.size()));
    }

    public static void printTask() {
        System.out.println(String.format("Got it. I've added this task:%n  %s",
                allTasks.get(allTasks.size() - 1)));
        printTaskRemaining();
    }

    public static void saveData() {
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
