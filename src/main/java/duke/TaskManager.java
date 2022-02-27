package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager implements Serializable {
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String BYE = "bye";
    private static final String END_OF_SECTION = "___________________________________________________";

    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static boolean isContinueInput = true;

    Scanner in = new Scanner(System.in);
    private static File duke;

    public TaskManager(File file) {
        String line;
        this.duke = file;
        if (this.duke.length() != 0) {
            try {
//                FileInputStream readData = new FileInputStream(this.duke);
//                ObjectInputStream readStream = new ObjectInputStream(readData);
//
//                allTasks = (ArrayList<Task>) readStream.readObject();
//                readStream.close();
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
                    // System.out.println(input[0].charAt(4));
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
                e.printStackTrace();
            }
        }

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

    private static void taskDecoder(String command, String commandArg) throws DukeException {
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
        default:
            throw new DukeException();
        }
    }

    private static void printAllTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("You have no task currently");
        } else {
            for (int i = 0; i < allTasks.size(); ++i) {
                System.out.println(String.format("%d.%s", i + 1, allTasks.get(i)));
            }
        }
        printEndLine();
    }

    private static void addToDo(String commandArg) {
        if (commandArg.isEmpty()) {
            System.out.println("OOPS!! The description of a todo cannot be empty");
            printEndLine();
        } else {
            allTasks.add(new ToDo(commandArg));
            printTask();
            printEndLine();
        }
    }

    private static void addEvent(String commandArg) {
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

    private static void addDeadline(String commandArg) {
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

    private static void markTask(String commandArg) {
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

    private static void unmarkTask(String commandArg) {
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

    private static void deleteTask(String commandArg) {
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

    private static Task getTask(String commandArg) {
        return allTasks.get(Integer.parseInt(commandArg) - 1);
    }

    private static void printTaskRemaining() {
        System.out.println(String.format("Now you have %d tasks in the list.", allTasks.size()));
    }

    private static void printTask() {
        System.out.println(String.format("Got it. I've added this task:%n  %s",
                allTasks.get(allTasks.size() - 1)));
        printTaskRemaining();
    }

    private static void printEndLine() {
        System.out.println(END_OF_SECTION);
    }

    private static void printInvalidCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means");
        printEndLine();
    }

    private static void saveData() {
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
            e.printStackTrace();
        }
    }
}
