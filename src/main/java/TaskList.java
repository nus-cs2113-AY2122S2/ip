import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;
    private static String TODO = "todo";
    private static String DEADLINE = "deadline";
    private static String EVENT = "event";

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    private Task createTask(String taskType, String[] taskDescription) {
        Task task;
        if (taskType.equals(DEADLINE)) {
            task = new Deadline(taskDescription[0], taskDescription[1]);
        } else if (taskType.equals(EVENT))
            task = new Event(taskDescription[0], taskDescription[1]);
        else {
            task = new Todo(taskDescription[0]);
        }
        return task;
    }

    public String addTask(String taskType, String[] taskDescription) {
        Task newTask = createTask(taskType, taskDescription);
        tasks.add(newTask);
        return String.format("Got it. I've added this task:\n%s\n" +
                                "Now you have %d tasks in list.",
                                newTask.toString(), tasks.size());
    }

    public String markTask(String markCommand, String id) {
        int index = Integer.valueOf(id) - 1;
        boolean isDone = true;
        if (markCommand.equals("unmark")) {
            isDone = false;
        }
        return tasks.get(index).setDone(isDone);
    }

    @Override
    public String toString() {
        String taskListString = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            taskListString += String.format("%d. %s", i, tasks.get(i - 1));
            if (i != tasks.size()) {
                taskListString += "\n";
            }
        }
        return taskListString;
    }
}
