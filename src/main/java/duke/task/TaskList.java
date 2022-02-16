package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    //private int taskCount = 0;

    public void printLine(){
        System.out.println("\t" + "-----------------------------------------");
    }

    public void addTask(Task newTask){
        taskList.add(newTask);
    }

    public void printTaskList(){
        printLine();
        System.out.println("\t" + " Here are the tasks in your list:");
        if (taskList.size() == 0){
            System.out.println("List is empty! No task is there!");
        }
        for(int i = 0 ; i < taskList.size(); i++){
            System.out.println("\t" + " " + (i + 1) + "." + taskList.get(i));
        }
        printLine();
    }

    public int getSize(){
        return taskList.size();
    }

    public void markDone(int taskId){
        taskList.get(taskId - 1).setDone();
        printLine();
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t" + taskList.get(taskId - 1));
        printLine();
    }

    public void unmark(int taskId){
        taskList.get(taskId - 1).setNotDone();
        printLine();
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println("\t" + taskList.get(taskId - 1));
        printLine();
    }
}
