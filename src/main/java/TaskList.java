import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected static List<Task> listOfTask = new ArrayList<>();
    private static int numOfTask = 0;

    public static Task getTaskFromListOfTask(int taskNumber) {
        // -1 to offset the counting of array list from 0
        return listOfTask.get(taskNumber - 1);
    }

    public static int getTaskNumberFromInput(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        // check to see if an index of < 0 was given
        System.out.println(index);
        if (index < 0 || index > numOfTask) {
            // -1 to indicate error
            System.out.println("Invalid Task to be marked!");
            return -1;
        }
        return index;
    }

    public static void markTask(String input, String taskStatus) {
        int taskNumber = getTaskNumberFromInput(input);
        if (taskNumber == -1) {
            return;
        }
        // true if it is "mark", set to false if it's not "mark"
        boolean isTaskDone = taskStatus.equalsIgnoreCase("mark");
        Task markedTask = getTaskFromListOfTask(taskNumber);
        markedTask.setDone(isTaskDone);
        if (isTaskDone) {
            System.out.println("Nice! I'v marked this task as done:");
        } else {
            System.out.println("Okay! I'v marked this task as not done:");
        }
        System.out.println(markedTask);
    }

    public static Deadline createDeadlineTask(String input) {
        String deadlineDescription = CommandParser.getDeadlineTaskDescription(input);
        String dueDateTime = CommandParser.getDeadlineDate(input);
        return new Deadline(deadlineDescription, dueDateTime);
    }

    public static Event createEventTask(String input) {
        String eventDescription = CommandParser.getEventTaskDescription(input);
        String dueDate = CommandParser.getEventDateTime(input);
        return new Event(eventDescription, dueDate);
    }

    public static Todo createTodoTask(String input) {
        String todoDescription = CommandParser.getToDoTaskDescription(input);
        return new Todo(todoDescription);
    }

    public static void addTaskToTaskList(String input, String type) {
        Task newTask;
        switch (type){
        case "deadline":
            newTask = createDeadlineTask(input);
            break;
        case "event":
            newTask = createEventTask(input);
            break;
        case "todo":
            newTask = createTodoTask(input);
            break;
        default:
            System.out.println("Invalid type of task given!");
            return;
        }
        listOfTask.add(newTask);
        numOfTask++;
        printTaskListUpdate(newTask);
    }

    public static void printTaskListUpdate(Task newTask) {
        System.out.println("Got it!. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " +numOfTask +" tasks in the list.");
    }

    public static void printTaskList() {
        int taskCount = 1;
        for (Task task : listOfTask) {
            System.out.print(" "+taskCount +".");
            System.out.println(task);
            taskCount++;
        }
    }
}
