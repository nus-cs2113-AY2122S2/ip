package taskitems;

import taskitems.exceptions.IllegalInputException;
import taskitems.task.Deadline;
import taskitems.task.Event;
import taskitems.task.Task;
import taskitems.task.Todo;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;
    static Greet greet;

    public TaskManager() {
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void markTask(int number) throws IllegalInputException {
        greet.printDecoration();
        if (number > taskCount || number < 1) {
            throw new IllegalInputException();
        }
        if (tasks.get(number - 1).isMarked()) {
            System.out.println("Err, this task is already marked...");
        } else {
            tasks.get(number - 1).setMarked(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(tasks.get(number - 1));
        }
        greet.printDecoration();

    }

    public void unmarkTask(int number) throws IllegalInputException {
        greet.printDecoration();
        if(number > taskCount || number < 1){
            throw new IllegalInputException();
        }
        if(!tasks.get(number - 1).isMarked()){
            System.out.println("I cannot unmark something that was never marked...");
        } else {
            tasks.get(number - 1).setMarked(false);
            System.out.println("Okay Boss! The following task has been unmarked: ");
            System.out.println(tasks.get(number - 1));
        }
        greet.printDecoration();
    }

    public void addToTasks(String taskName) throws IllegalInputException {
        if (taskName.equals("")) {
            throw new IllegalInputException();
        }
        greet.printDecoration();
        tasks.add(new Todo(taskName));
        System.out.println("added: " + tasks.get(taskCount));
        taskCount++;
        System.out.println("You now have " + taskCount + " tasks in the list.");
        greet.printDecoration();
    }

    public void addToTasks(String type, String taskName,String date){
        greet.printDecoration();
        if (type.equals("E")) {
            tasks.add(new Event(taskName, date));
        } else {
            tasks.add(new Deadline(taskName, date));
        }
        System.out.println("added: " + tasks.get(taskCount));
        taskCount++;
        System.out.println("You now have " + taskCount + " tasks in the list.");
        greet.printDecoration();
    }

    public void printTasks(){
        greet.printDecoration();
        if (taskCount == 0) {
            System.out.println("You have not added any Tasks!");
        } else {
            for (int i = 0; i < taskCount ; i++) {
                System.out.print(i+1 + ". ");
                System.out.println(tasks.get(i));
            }
        }
        greet.printDecoration();
    }
}

