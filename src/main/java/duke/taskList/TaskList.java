package duke.taskList;

import duke.exceptions.ChangeStatusException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> taskList;


    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public void printAllTasks() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.getSize(); i++) {
            System.out.print(i + 1);
            System.out.println("." + taskList.get(i));
        }
        System.out.println("----------------------------------------------------------------");
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskList.get(index - 1));
        taskList.remove(index - 1);

    }

    public void addTodo(String input) {
        String description = input.substring(input.indexOf(" ") + 1);
        taskList.add(new Todo(description));
        System.out.println("Got it. I've added this task:  ");
        System.out.println(taskList.get(taskList.size()-1));
    }


    public void addEvent(String input) {

        String event = input.substring(input.indexOf("/") + 4);
        String description = input.substring(input.indexOf(" "), input.indexOf("/"));
        taskList.add(new Event(description, event));

        System.out.println("Got it. I've added this task:  ");
        System.out.println(taskList.get(taskList.size()-1));
    }


    public void addDeadline(String input) {
        String deadline = input.substring(input.indexOf("/") + 4);
        String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
        taskList.add(new Deadline(description, deadline));

        System.out.println("Got it. I've added this task:  ");
        System.out.println(taskList.get(taskList.size()-1));
        System.out.println("----------------------------------------------------------------");
    }

    public void unmarkTask(int indexOfTask, String command) {
        taskList.get(indexOfTask - 1).unmark();
        System.out.println("OK, I've unmarked this task:");
        System.out.print(indexOfTask);
        System.out.println("." + taskList.get(indexOfTask - 1));
        System.out.println("----------------------------------------------------------------");
    }

    public void markTask(int indexOfTask, String command) {
        taskList.get(indexOfTask - 1).mark();
        System.out.println("OK, I've marked this task as done:");
        System.out.print(indexOfTask);
        System.out.println("." + taskList.get(indexOfTask - 1));
        System.out.println("----------------------------------------------------------------");
    }


}