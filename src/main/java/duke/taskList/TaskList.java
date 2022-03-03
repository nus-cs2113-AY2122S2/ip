package duke.taskList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

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
        Ui.printLine();
        Ui.printDisplayTask();
        for (int i = 0; i < this.getSize(); i++) {
            System.out.print(i + 1);
            System.out.println("." + taskList.get(i));
        }
        Ui.printLine();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void deleteTask(int index) {
        try {
            Task task = taskList.get(index - 1);
            taskList.remove(index - 1);
            Ui.printTaskRemove();
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsException();
        }
    }

    public void addTodo(String input) {
        String todo = input.substring(input.indexOf(" ") + 1);
        System.out.println(todo);
        taskList.add(new Todo(todo));
        Ui.printTaskAdd();
        System.out.println(taskList.get(taskList.size()-1));
    }


    public void addEvent(String input) {
        String event = input.substring(input.indexOf("/") + 4);
        String description = input.substring(input.indexOf(" "), input.indexOf("/"));
        taskList.add(new Event(description, event));
        Ui.printTaskAdd();
        System.out.println(taskList.get(taskList.size()-1));
    }


    public void addDeadline(String input) {
        String deadline = input.substring(input.indexOf("/") + 4);
        String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
        taskList.add(new Deadline(description, deadline));
        Ui.printTaskAdd();
        System.out.println(taskList.get(taskList.size()-1));
        Ui.printLine();
    }

    public void unmarkTask(int indexOfTask, String command) {
        taskList.get(indexOfTask - 1).unmark();
        Ui.printUnmarked();
        System.out.print(indexOfTask);
        System.out.println("." + taskList.get(indexOfTask - 1));
        Ui.printLine();
    }

    public void markTask(int indexOfTask, String command) {
        taskList.get(indexOfTask - 1).mark();
        Ui.printMarked();
        System.out.print(indexOfTask);
        System.out.println("." + taskList.get(indexOfTask - 1));
        Ui.printLine();
    }


}