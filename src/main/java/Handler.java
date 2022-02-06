import java.util.ArrayList;
import java.util.Map;

/**
 * Stores the
 */
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
    public static final String LIST_POST_MESSAGE_FORMAT =
            "____________________________________________________________";


    protected ArrayList<Task> taskList;

    public Handler() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Execute a command based on user input
     *
     * @param userCommand the command user entered
     * @param argumentList the accompanying arguments for the command
     * @return
     */
    public Boolean execute(String userCommand, Map<String, String> argumentList) {
        Task taskToAct;
        switch (userCommand) {
        case "bye":
            return false;
        case "list":
            System.out.println(LIST_PRE_MESSAGE_FORMAT);
            for (int i = 0; i<taskList.size(); i++) {
                System.out.println((i+1)+". "+taskList.get(i).toString());
            }
            System.out.println(LIST_POST_MESSAGE_FORMAT);
            break;
        case "mark":
            taskToAct = taskList.get(Integer.parseInt(argumentList.get(""))-1);
            taskToAct.setStatusIcon(true);
            System.out.println(String.format(TASK_MARKED_MESSAGE_FORMAT, taskToAct.toString()));
            break;
        case "unmark":
            taskToAct = taskList.get(Integer.parseInt(argumentList.get(""))-1);
            taskToAct.setStatusIcon(false);
            System.out.println(String.format(TASK_UNMARKED_MESSAGE_FORMAT, taskToAct.toString()));
            break;
        case "todo":
            taskToAct = new Todo(argumentList.get(""));
            taskList.add(taskToAct);
            System.out.println(String.format(TASK_ADDED_MESSAGE_FORMAT, taskToAct.toString()));
            break;
        case "deadline":
            taskToAct = new Deadline(argumentList.get(""), argumentList.get("by"));
            taskList.add(taskToAct);
            System.out.println(String.format(TASK_ADDED_MESSAGE_FORMAT, taskToAct.toString()));
            break;
        case "event":
            taskToAct = new Event(argumentList.get(""), argumentList.get("at"));
            taskList.add(taskToAct);
            System.out.println(String.format(TASK_ADDED_MESSAGE_FORMAT, taskToAct.toString()));
            break;
        default:
            break;
        }
        return true;
    }
}
