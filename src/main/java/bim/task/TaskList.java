package bim.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String LIST_DOT = ".";
    private static final String NEWLINE = "\n";
    private static final String LINE_INDENT = "\t";
    private static final String EMPTY_LIST = "404 Not Found";
    private static final String PRINT_LIST = "Here you go!";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).setAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).setAsNotDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return EMPTY_LIST;
        }

        String output = PRINT_LIST + NEWLINE;
        for(int i = 0; i < tasks.size(); i++) {
            output += LINE_INDENT + (i+1) + LIST_DOT + tasks.get(i);
            if (i != tasks.size() - 1) {
                output += NEWLINE;
            }
        }
        return output;
    }
}
