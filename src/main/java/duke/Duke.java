import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import duke.task.*;
import duke.exception.*;
import duke.task.Event;

import java.util.ArrayList;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static int taskCounter = 0;
    private static String HORIZONTAL_LINE = "____________________________________________________________";
    private static String DATA_DIRECTORY = System.getProperty("user.dir") + "/data";
    private static String DATA_FILE = DATA_DIRECTORY + "/duke.txt";
    private static Path dataDirectoryPath = Paths.get(DATA_DIRECTORY);
    
    private static void printFormat(String... args) {
        System.out.println(HORIZONTAL_LINE);
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printFormat("Hello! I'm Duke",
                "What can I do for you?",
                "type 'commands' for the list of commands");
    }

    private static void handleInput(String input) 
        throws SingleWordCommandException, IndexOutOfBoundsException, NoSlashException, AlreadyMarkedException,
        AlreadyUnmarkedException, InvalidNumberException {
        if (input.contains("list")) {
            if (input.equals("list")) {
                handleList();
            } else {
                throw new SingleWordCommandException();
            }

        } else if (input.contains("unmark")) {
            handleUnmark(input);

        } else if (input.contains("mark")) {
            handleMark(input);

        } else if (input.contains("todo")) {
            handleToDo(input);

        } else if (input.contains("deadline")) {
            if (input.contains("/")) {
                handleDeadline(input);
            } else {
                throw new NoSlashException();
            }

        } else if (input.contains("event")) {
            if (input.contains("/")) {
                handleEvent(input);
            } else {
                throw new NoSlashException();
            }

        } else if (input.contains("commands")) {
            if (input.equals("commands")) {
                handleCommand();
            } else {
                throw new SingleWordCommandException();
            }

        } else if (input.contains("delete")) {
            handleDelete(input);

        } else {
            printFormat("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    "Please type in 'commands' if you are not sure of the commands");

        }
    }

    private static void handleList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + list.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void handleUnmark(String input) throws AlreadyUnmarkedException, InvalidNumberException {
        int markInt = Integer.parseInt(input.substring(7)) - 1;
        if (markInt + 1 > taskCounter) {
            throw new InvalidNumberException();
        } else if (list.get(markInt).isDone()) {
            list.get(markInt).setDone(false);
            printFormat("OK, I've marked this task as not done yet:",
                list.get(markInt).toString());
        } else {
            throw new AlreadyUnmarkedException();
        }
    }

    private static void handleMark(String input) throws AlreadyMarkedException, InvalidNumberException,
            NumberFormatException {
        int markInt = Integer.parseInt(input.substring(5)) - 1;
        if (markInt + 1> taskCounter) {
            throw new InvalidNumberException();
        } else if (list.get(markInt).isDone()) {
            throw new AlreadyMarkedException();
        } else {
            list.get(markInt).setDone(true);
            printFormat("Nice! I've marked this task as done:",
                    list.get(markInt).toString());
        }
    }

    private static void handleDelete(String input) throws InvalidNumberException {
        int deleteInt = Integer.parseInt(input.substring(7)) - 1;
        if (deleteInt + 1 > taskCounter) {
            throw new InvalidNumberException();
        } else {
            Task deletedTask = list.get(deleteInt);
            list.remove(deleteInt);
            taskCounter--;
            printFormat("Noted. I've removed this task:", deletedTask.toString(), 
                    "Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
        }
    }

    private static void handleToDo(String input) {
        String[] subStrings = input.split(" ", 2);
        Task newTask = new ToDo(subStrings[1]);
        list.add(newTask);
        taskCounter++;
        //writeToFile("T", "0", subStrings[1]);
        printFormat("Got it. I've added this task:", 
                "  " + newTask.toString(),
                "Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
    }

    private static void handleDeadline(String input) {
        String[] subStrings = input.split(" ", 2);
        subStrings = subStrings[1].split("/", 2);
        String inputString = subStrings[0];
        subStrings = subStrings[1].split(" ", 2);
        String inputPreposition = subStrings[0];
        String inputDate = subStrings[1];
        Task newTask = new Deadline(inputString, inputDate);
        list.add(newTask);
        taskCounter++;
        printFormat("Got it. I've added this task:", 
                "  " + newTask.toString(),
                "Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
    }

    private static void handleEvent(String input) {
        String[] subStrings = input.split(" ", 2);
        subStrings = subStrings[1].split("/", 2);
        String inputString = subStrings[0];
        subStrings = subStrings[1].split(" ", 2);
        String inputPreposition = subStrings[0];
        String inputDate = subStrings[1];
        Task newTask = new Event(inputString, inputDate);
        list.add(newTask);
        taskCounter++;
        printFormat("Got it. I've added this task:", 
                "  " + newTask.toString(),
                "Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
    }

    private static void handleCommand() {
        printFormat("List of valid commands:",
                "'list' - lists out all tasks and its details",
                "'mark (X)' - marks item X on the list as done",
                "   (X is a number) e.g. 'mark 3' marks item 3 on the list",
                "'unmark (X)' - unmarks item X on the list as done",
                "   (X is a number) e.g. 'unmark 3' unmarks item 3 on the list",
                "'todo (task)' - ToDos are tasks without specific deadlines",
                "   (task is a string) e.g. 'todo buy shampoo' adds the task 'buy shampoo' to the list",
                "'deadline (task) /by (date/time)' - Deadlines are tasks that need to be done before a specific date/time",
                "   e.g. 'deadline math homework /by tues 2pm' adds a task with a deadline to the list",
                "'event (task) /at (date/time)' - Events are tasks that start at a specific time and ends at a specific time",
                "   e.g. 'event project meeting /at sunday 8-10pm' adds a task with a time range");
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE, false);
        int taskNum = 0;
        for (Task task : list) {
            taskNum++;
            fw.write(convertTask(taskNum, task));
        }
        fw.close();
    }

    private static String convertTask(int taskNum, Task task) {
        // num|type|T/F|description|(date)
        String line = String.valueOf(taskNum) + "|" + task.getType() + "|";
        if (task.isDone) {
            line += "1|";
        } else {
            line += "0|";
        }
        line += task.getDescription().substring(0, task.getDescription().length() - 1);
        if (task.toString().contains("(")) {
            line += "|" + task.getDate();
        }
        line += System.lineSeparator();
        return line;
    }

    private static void readFileContents() throws IOException {
        boolean directoryExists = new File(DATA_DIRECTORY).exists();
        boolean fileExists = new File(DATA_FILE).exists();
        File dataFile = new File(DATA_FILE);
        if (!directoryExists) {
            Files.createDirectory(dataDirectoryPath);
        }
        if (!fileExists) {
            dataFile.createNewFile();
        }
        Scanner s = new Scanner(dataFile); 
        while (s.hasNext()) {
            parseData(s.nextLine());
        }
    }

    private static void parseData(String data) {
        String[] dataArr = data.split("\\|");
        Task newTask;
        if (dataArr[1].equals("T")) {
            newTask = new ToDo(dataArr[3]);
            if (Integer.parseInt(dataArr[2]) == 1) {
                newTask.setDone(true);
            }
        } else if (dataArr[1].equals("D")) {
            newTask = new Deadline(dataArr[3], dataArr[4]);
            if (Integer.parseInt(dataArr[2]) == 1) {
                newTask.setDone(true);
            }
        } else {
            newTask = new Event(dataArr[3], dataArr[4]);
            if (Integer.parseInt(dataArr[2]) == 1) {
                newTask.setDone(true);
            }
        }
        list.add(newTask);
    }

    private static void bye() throws IOException {
        writeToFile();
        printFormat("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        intro();
        try {
            readFileContents();
            taskCounter = list.size();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                try {
                    handleInput(input);
                } catch (SingleWordCommandException e) {
                    printFormat("Oh no! There should not be any words after '" + input.split(" ")[1] + "'");
                } catch (IndexOutOfBoundsException e) {
                    printFormat("Oh no! You have to include the details of the task '" + input + "'");
                } catch (NoSlashException e) {
                    printFormat("Oh no! You need to include '/' in your task '" + input + "'");
                } catch (AlreadyMarkedException e) {
                    printFormat("Oh no! The item is already marked!");
                } catch (AlreadyUnmarkedException e) {
                    printFormat("Oh no! The item is already unmarked!");
                } catch (InvalidNumberException e) {
                    printFormat("Oh no! The number you have chosen is not valid!");
                } catch (NumberFormatException e) {
                    printFormat("Oh no! You need to be using a number instead!");
                }
            }
        }
        try {
            bye();
        } catch (IOException e) {
            printFormat("Oh no! Something went wrong!");
        }
    }
}