package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    public static final String COMMAND_DELETE = "delete";
    private Scanner uiScan;
    private ArrayList<Task> tasks;

    public UserInterface(Scanner mainScan) throws IOException, DukeException {
        this.uiScan = mainScan;
        this.tasks = loadSaveFile();
    }

    /**
     * Loads tasks saved in a save file, currently hardcoded to data/duke.txt.
     * @return A list of tasks found in the save file.
     * @throws FileNotFoundException
     * @throws DukeException
     */
    private ArrayList<Task> loadSaveFile() throws IOException, DukeException {
        File saveFile = new File("data/duke.txt");
        saveFile.createNewFile();
        Scanner fileScan = new Scanner(saveFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (fileScan.hasNextLine()) {
            tasks.add(loadTask(fileScan.nextLine()));
        }
        return tasks;
    }

    /**
     * Reads a single line from the save file and returns a Task representing it.
     * @param nextLine The line to be read from the save file.
     * @return A task representing a line from the save file.
     * @throws DukeException
     */
    private Task loadTask(String nextLine) throws DukeException {
        String[] pieces = nextLine.split("\\|");
        String taskType = pieces[0];
        ArrayList<String> task = new ArrayList<>();
        task.add(loadTaskType(taskType));
        Boolean isDone = loadCompletionStatus(pieces[1]);
        Task loadedTask;
        switch (taskType) {
        case "T":
            loadedTask = loadToDoDetails(task, pieces);
            break;
        case "D":
            loadedTask = loadDeadlineDetails(task, pieces);
            break;
        case "E":
            loadedTask = loadEventDetails(task, pieces);
            break;
        default:
            loadedTask = null;
        }
        if (isDone) {
            assert loadedTask != null;
            loadedTask.doTask();
        }
        return loadedTask;
    }

    /**
     * Fills the ToDo task component list with its description.
     * @param task An arraylist that will be built into a task.
     * @param pieces An array containing the '|' delimited chunks from a line of the save file.
     * @return a Task
     * @throws DukeException
     */
    private Task loadToDoDetails(ArrayList<String> task, String[] pieces) throws DukeException {
        task.add(pieces[2]);
        return buildTask(task);
    }

    /**
     * Fills the Deadline task component list with its description.
     * @param task An arraylist that will be built into a task.
     * @param pieces An array containing the '|' delimited chunks from a line of the save file.
     * @return a Task.
     * @throws DukeException
     */
    private Task loadDeadlineDetails(ArrayList<String> task, String[] pieces) throws DukeException {
        // description of task
        task.add(pieces[2]);
        // prepositions of task
        task.add(pieces[4]);
        // deadline of task
        task.add(pieces[3]);
        return buildTask(task);
    }

    /**
     * Fills the Event task component list with its description.
     * @param task An arraylist that will be built into a task.
     * @param pieces An array containing the '|' delimited chunks from a line of the save file.
     * @return a Task.
     * @throws DukeException
     */
    private Task loadEventDetails(ArrayList<String> task, String[] pieces) throws DukeException {
        // description of task
        task.add(pieces[2]);
        // prepositions of task
        task.add(pieces[4]);
        // timing of event
        task.add(pieces[3]);
        return buildTask(task);
    }

    /**
     * converts the shorthand Task char from a line in the save file to a class name.
     * @param taskType
     * @return
     */
    private String loadTaskType(String taskType) {
        switch (taskType) {
        case "T":
            return COMMAND_TODO;
        case "D":
            return COMMAND_DEADLINE;
        case "E":
            return COMMAND_EVENT;
        }
        return null;
    }

    /**
     * Returns true if the task is marked complete in the save file and false if otherwise.
     * @param isDone A char from the save file: 1 if the task is done, 0 if not.
     * @return true if the task is done, false if not.
     */
    private Boolean loadCompletionStatus(String isDone) {
        if (isDone.equals("1")) {
            return true;
        }
        return false;
    }

    public void start() {
        printGreeting();
        loopCommandInput();
        try {
            updateSaveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads commands from stdin and executes them using commandExec(commandInput)
     * until the user inputs COMMAND_EXIT, upon which the function returns.
     */
    public void loopCommandInput() {
        String commandInput;
        do {
            commandInput = uiScan.nextLine();
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
        case COMMAND_DELETE:
            String taskToDelete = pieces.get(1);
            deleteTask(taskToDelete);
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
     * Deletes a task based on its visual index in the list.
      * @param task
     */
    private void deleteTask(String task) {
        if (task == null) {
            System.out.println(("Task to be done is null."));
            return;
        }
        try {
            printDivider();
            System.out.println("Noted. I've removed this task:");
            System.out.println(this.tasks.get(Integer.parseInt(task) - 1));
            this.tasks.remove(Integer.parseInt(task) - 1);
            System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
            printDivider();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Replaces the save file with the current list of tasks.
     * @throws IOException
     */
    private void updateSaveFile() throws IOException {
        FileWriter fileToWriteTo = new FileWriter(new File("data/duke.txt"));
        String saveFile = "";
        for (Task task: this.tasks) {
            saveFile += (taskToString(task) + System.lineSeparator());
        }
        fileToWriteTo.write(saveFile);
        fileToWriteTo.close();
    }

    /**
     * Turns a task into a String that can be written to the save file.
     * @param task The task to be converted.
     * @return A String in save file format.
     */
    private String taskToString(Task task) {
        if (task instanceof ToDo) {
            return taskToString((ToDo) task);
        }
        if (task instanceof Deadline) {
            return taskToString((Deadline) task);
        }
        if (task instanceof Event) {
            return taskToString((Event) task);
        }
        return null;
    }

    /**
     * Turns a ToDo into save file String format.
     * @param task the ToDo to be converted.
     * @return a String in save file format.
     */
    private String taskToString(ToDo task) {
        String ret = "T|";
        if (task.getStatusIcon().equals("X")) {
            ret += "1|";
        } else {
            ret += "0|";
        }
        ret += task.getDescription();
        return ret;
    }

    /**
     *
     * Turns a Deadline into save file String format.
     * @param task the Deadline to be converted.
     * @return a String in save file format.
     */
    private String taskToString(Deadline task) {
        String ret = "D|";
        if (task.getStatusIcon().equals("X")) {
            ret += "1|";
        } else {
            ret += "0|";
        }
        ret += (task.getDescription() + "|");
        ret += (task.getTiming() + "|");
        ret += (task.getPrepositions());
        return ret;
    }

    /**
     *
     * Turns a Event into save file String format.
     * @param task the Event to be converted.
     * @return a String in save file format.
     */
    private String taskToString(Event task) {
        String ret = "D|";
        if (task.getStatusIcon().equals("X")) {
            ret += "1|";
        } else {
            ret += "0|";
        }
        ret += (task.getDescription() + "|");
        ret += (task.getTiming() + "|");
        ret += (task.getPrepositions());
        return ret;
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
