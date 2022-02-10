package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Map;

public class Handler {
    // Output message format for various actions
    public static final String TASK_ADDED_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nadded: %s"
            + "\n____________________________________________________________";
    public static final String TASK_MARKED_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nNice! I've marked this task as done:"
            + "\n %s"
            + "\n____________________________________________________________";
    public static final String TASK_UNMARKED_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nOK, I've marked this task as not done yet:"
            + "\n %s"
            + "\n____________________________________________________________";
    public static final String LIST_PRE_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nHere are the tasks in your list";
    public static final String LIST_MESSAGE_FORMAT =
            "%d. %s";
    public static final String LIST_POST_MESSAGE_FORMAT =
            "____________________________________________________________";

    protected ArrayList<Task> taskList;

    /**
     * Initialises a Handler instance with an empty lists of task
     * Functions as a singleton class (without the appropriate code), instantiate only one
     */
    public Handler() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Execute a command based on user input. Passes off control to relevant functions
     *
     * @param parser the Parser object contained parsed user command data
     */
    public void execute(Parser parser) {
        String userCommand;
        userCommand = parser.getUserCommand();
        Map<String, String> argumentList;
        argumentList = parser.getArgumentList();

        switch (userCommand) {
        case "list":
            executeList(argumentList);
            break;
        case "mark":
            executeMark(argumentList);
            break;
        case "unmark":
            executeUnmark(argumentList);
            break;
        case "todo":
            executeTodo(argumentList);
            break;
        case "deadline":
            executeDeadline(argumentList);
            break;
        case "event":
            executeEvent(argumentList);
            break;
        }
    }

    /**
     * Lists out all tasks and their corresponding information.
     * Private function, internal use within Handler only.
     *
     * @param argumentList arguments passed in by the user, as inherited from public execute() function
     */
    private void executeList(Map<String, String> argumentList) {
        System.out.println(LIST_PRE_MESSAGE_FORMAT);
        for (int i = 0; i<taskList.size(); i++) {
            String taskInfo = taskList.get(i).toString();
            String formattedTaskInfo = String.format(LIST_MESSAGE_FORMAT, i+1, taskInfo);
            System.out.println(formattedTaskInfo);
        }
        System.out.println(LIST_POST_MESSAGE_FORMAT);
    }

    /**
     * Marks a task as done, whose index is in argumentList.
     * Private function, internal use within Handler only.
     *
     * @param argumentList arguments passed in by the user, as inherited from public execute() function
     */
    private void executeMark(Map<String, String> argumentList) throws IndexOutOfBoundsException {
        int index = Integer.parseInt(argumentList.get(""));
        index = index - 1;
        Task taskToMark = taskList.get(index);
        taskToMark.setStatusIcon(true);
        System.out.println(String.format(TASK_MARKED_MESSAGE_FORMAT, taskToMark.toString()));

    }

    /**
     * Marks a task as not done, whose index is in argumentList.
     * Private function, internal use within Handler only.
     *
     * @param argumentList arguments passed in by the user, as inherited from public execute() function
     */
    private void executeUnmark(Map<String, String> argumentList) throws IndexOutOfBoundsException {
        int index = Integer.parseInt(argumentList.get(""));
        index = index - 1;
        Task taskToMark = taskList.get(index);
        taskToMark.setStatusIcon(false);
        System.out.println(String.format(TASK_UNMARKED_MESSAGE_FORMAT, taskToMark.toString()));
    }

    /**
     * Creates a Todo and adds it to the list. The description is provided in argumentList.
     * Private function, internal use within Handler only.
     *
     * @param argumentList arguments passed in by the user, as inherited from public execute() function
     */
    private void executeTodo(Map<String, String> argumentList) {
        String description = argumentList.get("");
        Todo todoTask = new Todo(argumentList.get(""));
        taskList.add(todoTask);
        System.out.println(String.format(TASK_ADDED_MESSAGE_FORMAT, todoTask.toString()));
    }

    /**
     * Creates a Deadline and adds it to the list. The description and byDate is provided in argumentList.
     * Private function, internal use within Handler only.
     *
     * @param argumentList arguments passed in by the user, as inherited from public execute() function
     */
    private void executeDeadline(Map<String, String> argumentList) {
        String description = argumentList.get("");
        String byDate = argumentList.get("by");
        Deadline deadlineTask = new Deadline(description, byDate);
        taskList.add(deadlineTask);
        System.out.println(String.format(TASK_ADDED_MESSAGE_FORMAT, deadlineTask.toString()));
    }

    /**
     * Creates an Event and adds it to the list. The description and atDate is provided in argumentList.
     * Private function, internal use within Handler only.
     *
     * @param argumentList arguments passed in by the user, as inherited from public execute() function
     */
    private void executeEvent(Map<String, String> argumentList) {
        String description = argumentList.get("");
        String byDate = argumentList.get("at");
        Event eventTask = new Event(description, byDate);
        taskList.add(eventTask);
        System.out.println(String.format(TASK_ADDED_MESSAGE_FORMAT, eventTask.toString()));
    }
}
