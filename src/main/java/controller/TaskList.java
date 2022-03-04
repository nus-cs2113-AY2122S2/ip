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
    private static final String LINE = "-------------------------------------------";
    public static final String SPACE = " ";
    private static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(String data) {
        String[] taskStrList = data.split(System.lineSeparator());
        System.out.println("string list length: " + taskStrList.length);
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
    public void removeTaskByIdx ( int i){
        taskList.remove(i);
    }

    /**
     * Return all tasks in the list
     * @return arraylist of tasks
     */
    public ArrayList<Task> getTaskList () {
        return taskList;
    }

    /**
     * Classify task type.
     * Add corresponding task into list according to input
     * @param input user input
     * @param type task type
     * @return task details
     */
    public String addTask (String type, String input){
        String[] inputWord = input.split(SPACE);
        if (inputWord.length == 1) {
            System.out.println(INDENT + "OOPS! The task doesn't have description :(");    // check description
            return null;
        }
        int desIdx = input.indexOf(" ") + 1;
        Task newTask = null;
        switch (type) {
        case "todo":
            String description = input.substring(desIdx);
            newTask = new Todo(description);
            break;
        case "deadline":
        case "event":
            if (!input.contains("/")) {
                // check time
                return null;
            }

            int timeIdx = input.indexOf("/") + 4;
            int desEndIdx = input.indexOf("/") - 1;
            description = input.substring(desIdx, desEndIdx);
            String time = input.substring(timeIdx);
            if (type.equals("deadline")) {
                newTask = new Deadline(description, time);
            } else {
                newTask = new Event(description, time);
            }
            break;
        }
        taskList.add(newTask);
        return newTask.toString();
    }

    public String markTaskByIdx(int i){
        taskList.get(i).markAsDone();
        return taskList.get(i).toString();
    }

    public String unmarkTaskByIdx(int i){
        taskList.get(i).markAsUndone();
        return taskList.get(i).toString();
    }

}


