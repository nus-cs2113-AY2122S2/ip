import duke.exception.MissingDateException;
import duke.exception.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    protected static ArrayList<Task> tasks = new ArrayList<>();

    private static void parseInput(Scanner in) {
        String line;
        System.out.println("Storing Up to 100 Tasks");
        do {
            System.out.println("Awaiting your command");
            line = in.nextLine();
            String[] words = line.split(" ", 2);
            String command = words[0];

            switch (command) {
            case "list":
                printTasks();
                break;
            case "mark":
                markTask(words);
                break;
            case "unmark":
                unmarkTask(words);
                break;
            case "todo":
                addToDo(words);
                break;
            case "deadline":
                addDeadline(words);
                break;
            case "event":
                addEvent(words);
                break;
            case "delete":
                deleteTask(words);
                break;
            case "help":
                printHelp();
                break;
            default:
                if (!line.equalsIgnoreCase("bye")) {
                    System.out.println("Sorry, I did not understand that command. Input help to find out more");
                }
                break;
            }
        } while ((!line.equalsIgnoreCase("bye")) && Task.getNumOfTasks() < 100);
    }

    private static void deleteTask(String[] deleteTaskParameters) {
        try {
            int taskNumber = Integer.parseInt(deleteTaskParameters[1]);
            int taskIndex = taskNumber - 1;
            if (taskNumber > Task.getNumOfTasks()) {
                System.out.println("You don't have that many tasks ><!");
                return;
            }
            System.out.println("I have removed:\n" + tasks.get(taskIndex));
            tasks.remove(taskIndex);
            Task.deleteTask();
            System.out.println("Now you have " + Task.getNumOfTasks() + " task(s) on the list.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number after delete");
        }
    }

    private static void addEvent(String[] words) {
        try {
            String description = extractDescription(words);
            String at = extractDate(words);
            tasks.add(new Event(description, at));
            System.out.println("Event added!");
            printTasks();
        } catch (MissingDescriptionException e) {
            System.out.println("Description cannot be empty. Correct format: event <description> /at <date>");
        } catch (MissingDateException e) {
            System.out.println("Date cannot be empty. Correct format: event <description> /at <date>");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect Format. Correct format: event <description> /at <date>");
        }
    }

    private static void addDeadline(String[] words) {
        try {
            String description = extractDescription(words);
            String by = extractDate(words);
            tasks.add(new Deadline(description, by));
            System.out.println("Deadline added!");
            printTasks();
        } catch (MissingDescriptionException e) {
            System.out.println("Description cannot be empty. Correct format: deadline <description> /by <date>");
        } catch (MissingDateException e) {
            System.out.println("Date cannot be empty. Correct format: deadline <description> /by <date>");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect Format. Correct format: deadline <description> /by <date>");
        }
    }

    private static void addToDo(String[] words) {
        try {
            String description = extractDescription(words);
            tasks.add(new ToDo(description));
            System.out.println("Todo added!");
            printTasks();
        } catch (MissingDescriptionException e) {
            System.out.println("Description cannot be empty. Correct format: todo <description>");
        }
    }

    private static void unmarkTask(String[] unmarkTaskParameters) {
        try {
            int taskNumber = Integer.parseInt(unmarkTaskParameters[1]);
            int taskIndex = taskNumber - 1;
            if (taskNumber > Task.getNumOfTasks()) {
                System.out.println("You don't have that many tasks ><!");
                return;
            }
            tasks.get(taskIndex).setUndone();
            System.out.println("I have marked the task as not done!");
            System.out.println(tasks.get(taskIndex).getStatusIcon() + tasks.get(taskIndex).getTaskDescription() + "\n");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a number after unmark");
        }
    }

    private static void markTask(String[] markTaskParameters) {
        try {
            int taskNumber = Integer.parseInt(markTaskParameters[1]);
            int taskIndex = taskNumber - 1;
            if (taskNumber > Task.getNumOfTasks()) {
                System.out.println("You don't have that many tasks ><!");
                return;
            }
            tasks.get(taskIndex).setDone();
            System.out.println("I have marked the task as done!");
            System.out.println(tasks.get(taskIndex).getStatusIcon() + tasks.get(taskIndex).getTaskDescription() + "\n");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a number after mark");
        }
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumOfTasks(); i += 1) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("You have " + Task.getNumOfTasks() + " task(s) on the list.\n");
    }

    private static void printWelcomeMessage() {
        System.out.println("____________________________________________________________\n"
                + "Hello, nice to meet you. I'm Yae! (*^▽^*)\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
    }
    
    private static String extractDescription(String[] words) throws MissingDescriptionException {
        if (words.length < 2) {
            throw new MissingDescriptionException();
        }
        String[] parameters = words[1].split(" /", 2);
        if (parameters[0].isBlank()) {
            throw new MissingDescriptionException();
        }
        return parameters[0];
    }

    private static String extractDate(String[] words) throws MissingDateException {
        String[] parameters = words[1].split(" /", 2);
        parameters = parameters[1].split(" ", 2);
        if (parameters.length < 2 || parameters[1].isBlank()) {
            throw new MissingDateException();
        }
        return parameters[1];
    }

    private static void printHelp() {
        System.out.println("Here are the list of commands:");
        System.out.println("list: Lists current tasks.");
        System.out.println("mark: Marks task as done. (e.g. mark <task number>)\n"
                + "unmark: Marks task as not done. (e.g. unmark <task number>)");
        System.out.println("todo: adds a todo. (e.g. todo <description>)");
        System.out.println("deadline: adds a deadline. (e.g. deadline <description> /by <date>)");
        System.out.println("event: adds an event. (e.g. event <description> /at <date>)");
    }

    private static void readSaveData() throws FileNotFoundException {
        String taskType;
        String taskDescription;
        boolean isDone;
        String date = "";
        File loadData = new File("data/duke.txt");
        Scanner loadDataScanner = new Scanner(loadData);
        while (loadDataScanner.hasNext()) {
            String line = loadDataScanner.nextLine();
            String[] words = line.split(" \\| ");
            taskType = words[0];
            isDone = words[1].equals("[X]");
            taskDescription = words[2];
            if (words.length > 3) {
                date = words[3];
            }
            loadData(taskType, isDone, taskDescription, date);
        }
        System.out.println("Loaded Save File");
    }

    private static void loadData(String command, boolean isDone, String description, String date) {
        int numOfTasks = Task.getNumOfTasks();
        switch(command) {
        case "todo":
            tasks.add(new ToDo(description));
            if (isDone) {
                tasks.get(numOfTasks).setDone();
            }
            break;
        case "deadline":
            tasks.add(new Deadline(description, date));
            if (isDone) {
                tasks.get(numOfTasks).setDone();
            }
            break;
        case "event":
            tasks.add(new Event(description, date));
            if (isDone) {
                tasks.get(numOfTasks).setDone();
            }
            break;
        default:
            System.out.println("Cannot load line");
        }
    }

    private static void saveData() throws IOException {
        createSaveDirectory();
        createSaveFile();
        FileWriter writer = new FileWriter("data/duke.txt", false);
        for (int i = 0; i < Task.getNumOfTasks(); i += 1) {
            writer.write(tasks.get(i).getTaskType() + " | " + tasks.get(i).getStatusIcon() + "| "
                    + tasks.get(i).getTaskDescription());
            if (tasks.get(i) instanceof Deadline || tasks.get(i) instanceof Event) {
                writer.write(" | " + tasks.get(i).getTime());
            }
            writer.write("\n");
        }
        writer.close();
    }

    private static void createSaveDirectory() {
        File saveDirectory = new File("data");
        if (!saveDirectory.exists()) {
            if (!saveDirectory.mkdir()) {
                System.out.println("Failed to create new directory.");
            }
        }
    }

    private static void createSaveFile() throws IOException {
        File saveFile = new File("data/duke.txt");
        if (saveFile.createNewFile()){
            System.out.println("Save file created.");
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        //Task[] tasks = new Task[100];
        try {
            readSaveData();
        } catch (FileNotFoundException e) {
            System.out.println(("Save File not found."));
        }
        parseInput(in);
        try {
            saveData();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Goodbye, see you next time!");
    }
}
