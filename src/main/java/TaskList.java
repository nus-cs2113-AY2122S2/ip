import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public String addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
        return String.format("added: %s", newTask.toString());
    }

    public String markTask(String markCommand, String id) {
        int index = Integer.valueOf(id) - 1;
        if (markCommand.equals("unmark")) {
            return unmarkDone(index);
        }
        return markDone(index);
    }

    private String markDone(int index) {
        return tasks.get(index).markDone();
    }

    private String unmarkDone(int index) {
        return tasks.get(index).unmarkDone();
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
