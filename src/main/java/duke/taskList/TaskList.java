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
        System.out.println("\n----------------------------------------------------------------");
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
    }


    public void toggleStatus(int indexOfTask, String command) throws ChangeStatusException {
        taskList.get(indexOfTask - 1).changeStatus();
        if ((taskList.get(indexOfTask -1).isDone && command == "mark") ||
            (!taskList.get(indexOfTask -1).isDone && command == "unmark"))
            throw new ChangeStatusException("This task is already marked/unmarked");

        System.out.println("OK, I've marked this task as (not) done:");
        System.out.print(indexOfTask);
        System.out.println("." + taskList.get(indexOfTask - 1));
    }


}