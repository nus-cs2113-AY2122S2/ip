import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {
    private final Scanner uiScan;
    private final ArrayList<Task> tasks;

    /**
     * Constructor.
     * @param mainScan a Scanner object that reads commands from the user.
     */
    public UserInterface(Scanner mainScan) {
        this.uiScan = mainScan;
        this.tasks = new ArrayList<>();
    }

    /**
     * Starts the UserInterface.
     * Greets the user and begins the command input loop.
     */
    public void start() {
        printGreeting();
        commandLoop();
    }

    /**
     * Reads commands from stdin and executes them using commandExec(commandInput)
     * until the user inputs "bye", upon which the function returns.
     */
    public void commandLoop() {
        String commandInput;
        do {
            commandInput = uiScan.nextLine();
            commandExec(commandInput);
        } while (!commandInput.equals("bye"));
    }

    /**
     * Checks the String inputted by the user and executes the appropriate command
     * using a switch statement.
     * @param nextLine The command to be executed.
     */
    private void commandExec(String nextLine) {
        if (nextLine == null) {
            System.out.println("Error: Command is null.");
            return;
        }
        try {
            ArrayList<String> pieces = new ArrayList<>(Arrays.asList(nextLine.split(" ")));
            switch (pieces.get(0)) {
            case "bye":
                printGoodbye();
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                doTask(pieces.get(1));
                break;
            case "unmark":
                undoTask(pieces.get(1));
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(pieces);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + pieces.get(0));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Stores a task in the list of tasks.
     * @param inputTasks ArrayList<String> containing input from stdin. The first String in task should represent the type of
     *                   Task that must be added to the list of Tasks. Supported tasks: todo, deadline, event
     */
    private void addTask(ArrayList<String> inputTasks) {
        try {
            printDivider();
            this.tasks.add(taskBuilder(inputTasks));
            System.out.println("Got it. I've added this task:\n  " + this.tasks.get(this.tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            printDivider();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Constructs a subclass of Task from an ArrayList<String>.
     * @param taskStringArray the ArrayList<String> containing the user's input, split up by whitespace.
     *                        The first element of this list should contain the type of task to be constructed.
     * @return a subclass of Task determined by the first element of taskStringArray.
     */
    private Task taskBuilder(ArrayList<String> taskStringArray) {
        String mode = taskStringArray.get(0);
        ArrayList<StringBuilder> taskParts = taskSplitter(taskStringArray);
        String prepositions = String.valueOf(taskParts.get(taskParts.size() - 1));
        switch (mode) {
        case "todo":
            return new ToDo(String.valueOf(taskParts.get(0)));
        case "deadline":
            return new Deadline(String.valueOf(taskParts.get(0)), String.valueOf(taskParts.get(1)), prepositions);
        case "event":
            return new Event(String.valueOf(taskParts.get(0)), String.valueOf(taskParts.get(1)), prepositions);
        }
        return null;
    }

    /**
     * Splits an ArrayList<String> into an ArrayList<StringBuilder>, where each element
     * can be used to construct a subclass of Task. Use this in conjunction with taskBuilder to construct a
     * subclass of Task.
     * @param input ArrayList of Strings that represent the user's input split by whitespace.
     * @return an ArrayList<StringBuilder> containing the input split by the '/' character, not including
     *         the word containing the '/'. Each element represents a chunk of the task. The last element contains
     *         all prepositions used in a single string.
     *         e.g.: deadline return book /by Sunday returns the following ArrayList:
     *         {"return book", "Sunday", "/by"}, which prints the task as follows:
     *         read book (by: Sunday);
     *         e.g.: deadline return book /by Sunday /at library returns the following Arraylist:
     *         {"return book", "Sunday", "Library", "/by/at"}
     */
    private ArrayList<StringBuilder> taskSplitter(ArrayList<String> input) {
        /* taskPartsIterator increments when a String containing '/' is encountered.
         * inputIterator increments after each String in input is read.
         * The loop ends when inputIterator exceeds the size of the ArrayList, indicating that all
         * Strings in input have been read.
         * inputIterator++ does two things:
         * 1. It starts iterating from 1 instead of 0, skipping the first element which contains the Task subclass
         * name.
         * 2. It increments inputIterator after encountering a String starting with '/' so the nested loop can
         * continue from the next String, preventing the outer loop from looping infinitely.
         */
        ArrayList<StringBuilder> taskParts = new ArrayList<>();
        StringBuilder prepositions = new StringBuilder();
        for (int taskPartsIterator = 0, inputIterator = 0; inputIterator < input.size(); ++taskPartsIterator) {
            String finalWord = input.get(inputIterator);
            if (finalWord.startsWith("/")) {
                prepositions.append(finalWord);
            }
            inputIterator++;
            taskParts.add(new StringBuilder());
            for (; inputIterator < input.size() && !input.get(inputIterator).startsWith("/"); ++inputIterator) {
                taskParts.get(taskPartsIterator).append(input.get(inputIterator)).append(" ");
            }
        }
        taskParts.add(prepositions);
        return taskParts;
    }

    /**
     * Prints all tasks stored in memory by addTask(Task)
     */
    private void listTasks() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= this.tasks.size(); i++) {
            System.out.println(i + "." + this.tasks.get(i - 1));
        }
        printDivider();
    }

    /**
     * Marks the task selected by the user as done. Tasks are selected by their visual index on the list
     * (starting from 1, not 0) and not by name.
     * @param task The index of the task to be marked done.
     */
    private void doTask(String task) {
        if (task == null) {
            System.out.println(("Error: Task to be done is null."));
            return;
        }
        try {
            this.tasks.get(Integer.parseInt(task) - 1).doTask();
            printDivider();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.tasks.get(Integer.parseInt(task) - 1));
            printDivider();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Marks the task selected by the user as undone. Tasks are selected by their visual index on the list
     * (starting from 1, not 0) and not by name.
     * @param task The index of the task to be marked done.
     */
    private void undoTask(String task) {
        if (task == null) {
            System.out.println("Error: Task to be undone is null.");
            return;
        }
        try {
            this.tasks.get(Integer.parseInt(task) - 1).undoTask();
            printDivider();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.tasks.get(Integer.parseInt(task) - 1));
            printDivider();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prints a goodbye message and returns.
     */
    private void printGoodbye() {
        printDivider();
        System.out.println("Goodbye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints 37 underscores to stdout to serve as a divider.
     */
    private void printDivider() {
        System.out.println("_____________________________________");
    }

    /**
     * Prints a greeting with divider lines.
     */
    private void printGreeting() {
        printDivider();
        System.out.println("Hello! I'm Michel.");
        System.out.println("What can I do for you?");
        printDivider();
    }
}
