package bim.task;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String LIST_DOT = ".";
    private static final String NEWLINE = "\t";
    private static final String EMPTY_LIST = "404 Not Found";


    private void addTask(Task newTask) {
        tasks.add(newTask);
    }

    private void deleteTask(int index) {
        tasks.remove(index);
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return EMPTY_LIST;
        }

        int i = 1;
        String output = "";
        for (Task t : tasks) {
            output += i + LIST_DOT + t + NEWLINE;
            ++i;
        }
        return output;
    }
}
