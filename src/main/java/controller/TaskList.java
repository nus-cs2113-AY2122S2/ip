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

    public int getCount () {
        return taskList.size();
    }

    public Task getTaskByIdx ( int i){
        return taskList.get(i);
    }

    public void removeTaskByIdx ( int i){
        taskList.remove(i);
    }

    public ArrayList<Task> getTaskList () {
        return taskList;
    }

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


