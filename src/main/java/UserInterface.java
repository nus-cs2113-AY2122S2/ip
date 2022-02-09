import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    private final Scanner UISCAN;
    private final ArrayList<Task> tasks;

    public UserInterface(Scanner mainScan) {
        this.UISCAN = mainScan;
        this.tasks = new ArrayList<>();
    }

    public void start() {
        printGreeting();
        loopCommandInput();
    }

    /**
     * Reads commands from stdin and executes them using commandExec(commandInput)
     * until the user inputs COMMAND_EXIT, upon which the function returns.
     */
    public void loopCommandInput() {
        String commandInput;
        do {
            commandInput = UISCAN.nextLine();
            try {
                executeCommand(commandInput);
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!commandInput.equals(COMMAND_EXIT));
    }

    /**
     * Checks the String inputted by the user and executes the appropriate command
     * using a switch statement.
     *
     * @param nextLine The command to be executed.
     */
    private void executeCommand(String nextLine) throws DukeException {
        ArrayList<String> pieces = new ArrayList<>(Arrays.asList(nextLine.split(" ")));
        String commandType = pieces.get(0);
        switch (commandType) {
        case COMMAND_EXIT:
            printGoodbye();
            break;
        case COMMAND_LIST:
            listTasks();
            break;
        case COMMAND_MARK:
            String taskToMark = pieces.get(1);
            doTask(taskToMark);
            break;
        case COMMAND_UNMARK:
            String taskToUnmark = pieces.get(1);
            undoTask(taskToUnmark);
            break;
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            addTask(pieces);
            break;
        default:
            throw new DukeException("Command not found.");
        }
    }

    /**
     * Stores a task in the list of tasks.
     *
     * @param inputTasks ArrayList<String> containing input from stdin. The first String in task should represent the type of
     *                   Task that must be added to the list of Tasks. Supported tasks: todo, deadline, event
     */
    private void addTask(ArrayList<String> inputTasks) {
        try {
            printDivider();
            this.tasks.add(buildTask(inputTasks));
            System.out.println("Got it. I've added this task:\n  " + this.tasks.get(this.tasks.size() - 1));
            System.out.println("Now you have " + tasks.size()
                    + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            printDivider();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Constructs a subclass of Task from an ArrayList<String>.
     *
     * @param taskStringArray the ArrayList<String> containing the user's input, split up by whitespace.
     *                        The first element of this list should contain the subclass of task to be constructed.
     * @return a subclass of Task determined by the first element of taskStringArray.
     */
    private Task buildTask(ArrayList<String> taskStringArray) throws DukeException {
        String taskType = taskStringArray.get(0);
        ArrayList<StringBuilder> taskParts = splitTask(taskStringArray);
        validateTask(taskType, taskParts);
        String prepositions = String.valueOf(taskParts.get(taskParts.size() - 1));
        switch (taskType) {
        case COMMAND_TODO:
            return new ToDo(String.valueOf(taskParts.get(0)));
        case COMMAND_DEADLINE:
            return new Deadline(String.valueOf(taskParts.get(0)),
                    String.valueOf(taskParts.get(1)),
                    prepositions);
        case COMMAND_EVENT:
            return new Event(String.valueOf(taskParts.get(0)),
                    String.valueOf(taskParts.get(1)),
                    prepositions);
        }
        return null;
    }

    /**
     * Validates the input of the task to check for user error.
     *
     * @param taskType  the type of task (todo, deadline etc).
     * @param taskParts the list of components that form the task.
     * @throws DukeException error message if task is invalid
     */
    private void validateTask(String taskType, ArrayList<StringBuilder> taskParts) throws DukeException {
        if (taskParts.isEmpty()) {
            throw new DukeException("The description of a " + taskType + " cannot be empty.");
        }
        String prepositions = String.valueOf(taskParts.get(taskParts.size() - 1));
        switch (taskType) {
        case COMMAND_TODO:
            if (!prepositions.isEmpty()) {
                throw new DukeException("Todo tasks cannot include the preposition " + prepositions +" in the description.");
            }
            break;
        case COMMAND_DEADLINE:
            if (taskParts.get(0).toString().equals("")
                    || taskParts.get(1).toString().equals("")) {
                throw new DukeException("The description or deadline is incomplete.");
            }
            break;
        case COMMAND_EVENT:
            if (taskParts.get(0).toString().equals("") || taskParts.get(1).toString().equals("")) {
                throw new DukeException("The description or time of event is incomplete.");
            }
        }
    }


    /**
     * Splits an ArrayList<String> into an ArrayList<StringBuilder>, where each element
     * can be used to construct a subclass of Task. Use this in conjunction with buildTask to construct a
     * subclass of Task.
     *
     * @param input ArrayList of Strings that represent the user's input split by whitespace.
     * @return an ArrayList<StringBuilder> containing the input split by the '/' character.
     * Each element represents a String used to construct the task.
     * The last element contains all prepositions used in a single string.
     * e.g.: deadline return book /by Sunday /at library returns the following Arraylist:
     * {"return book", "Sunday", "Library", "/by/at"}
     * returns an empty list if input contains only a command.
     */
    private ArrayList<StringBuilder> splitTask(ArrayList<String> input) {
        /* taskPartsIterator increments when a String containing '/' is encountered.
         * inputIterator increments after each String in input is read.
         * The loop ends when inputIterator exceeds the size of the ArrayList, indicating that all
         * Strings in input have been read.
         */
        ArrayList<StringBuilder> taskParts = new ArrayList<>();
        if (input.size() <= 1) {
            return taskParts;
        }
        StringBuilder prepositions = new StringBuilder();
        for (int taskPartsIterator = 0, inputIterator = 0; inputIterator < input.size(); ++taskPartsIterator) {
            String finalWord = input.get(inputIterator);
            if (finalWord.startsWith("/")) {
                prepositions.append(finalWord);
            }
            inputIterator++;
            taskParts.add(new StringBuilder());
            for (; inputIterator < input.size()
                    && !input.get(inputIterator).startsWith("/"); ++inputIterator) {
                taskParts.get(taskPartsIterator).append(input.get(inputIterator)).append(" ");
            }
        }
        taskParts.add(prepositions);
        return taskParts;
    }

    /**
     * Prints all tasks stored in memory by addTask(Task) to stdout
     */
    private void listTasks() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        printTasks();
        printDivider();
    }

    private void printTasks() {
        for (int i = 1; i <= this.tasks.size(); i++) {
            System.out.println(i + "." + this.tasks.get(i - 1));
        }
    }

    /**
     * Marks the task selected by the user as done. Tasks are selected by their visual index on the list
     * (starting from 1, not 0) and not by name.
     *
     * @param task The index of the task to be marked done.
     */
    private void doTask(String task) {
        if (task == null) {
            System.out.println(("Task to be done is null."));
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
     *
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
