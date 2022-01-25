import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner uiScan;
    private ArrayList<Task> tasks;

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
        greeting();
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
            String[] pieces = nextLine.split(" ");
            switch (pieces[0]) {
                case "bye":
                    printGoodbye();
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    doTask(pieces[1]);
                    break;
                case "unmark":
                    undoTask(pieces[1]);
                    break;
                default:
                    addTask(nextLine);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Stores a task.
     * @param task The description of the task.
     */
    private void addTask(String task) {
        if (task == null) {
            System.out.println("Error: Task to be stored is null.");
            return;
        }
        try {
            printDivider();
            this.tasks.add(new Task(task));
            System.out.println("added: " + task);
            printDivider();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
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
    private void greeting() {
        printDivider();
        System.out.println("Hello! I'm Michel.");
        System.out.println("What can I do for you?");
        printDivider();
    }
}
