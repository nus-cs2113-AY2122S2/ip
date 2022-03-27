package controller;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class TaskList {
    private static final String FILE_SEPARATOR = " | ";
    private static final String INDENT = "    ";
    public static final String SPACE = " ";
    private static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(String data) {
        if(data == null || data.isEmpty()) return;
        String[] taskStrList = data.split(System.lineSeparator());
        for (int i = 0; i < taskStrList.length; i++) {
            String newLine = taskStrList[i];
            Task newTask;
            StringTokenizer st = new StringTokenizer(newLine, FILE_SEPARATOR);
            String taskType = st.nextToken();
            String taskStatus = st.nextToken();
            String description = st.nextToken();

            if (taskType.equals("D")) {
                String time = st.nextToken();
                newTask = new Deadline(description, time);
            } else if (taskType.equals("E")) {
                String time = st.nextToken();
                newTask = new Event(description, time);
            } else {
                newTask = new Todo(description);
            }

            if (taskStatus.equals("X")) newTask.markAsDone();
            taskList.add(newTask);
        }
    }

    public TaskList() {
            taskList = new ArrayList<>();
        }
    /**
     * Return total task count in the task list
     * @return total task count
     */
    public int getCount () {
        return taskList.size();
    }

    /**
     * Return task of corresponding index.
     * @param i  the index of the target task.
     * @return task of corresponding index.
     */
    public Task getTaskByIdx ( int i){
        return taskList.get(i);
    }

    /**
     * Remove corresponding task by index
     * @param i the index of target task.
     * @return total task count
     */
    public String removeTaskByIdx ( int i){
        String task = taskList.get(i).toString();
        taskList.remove(i);
        return task;
    }

    /**
     * Return all tasks in the list
     * @return arraylist of tasks
     */
    public ArrayList<Task> getTaskList () {
        return taskList;
    }

    public String markTaskByIdx(int i){
        taskList.get(i).markAsDone();
        return taskList.get(i).toString();
    }

    public String unmarkTaskByIdx(int i){
        taskList.get(i).markAsUndone();
        return taskList.get(i).toString();
    }

    public void addNewTodo(String description) {
        taskList.add(new Todo(description));
    }

    public void addNewEvent(String description, String time) {
        taskList.add(new Event(description,time));
    }

    public void addNewDeadline(String description, String time) {
        taskList.add(new Deadline(description, time));
    }

    public String getNewAdd() {
        return taskList.get(taskList.size()-1).toString();
    }
}


