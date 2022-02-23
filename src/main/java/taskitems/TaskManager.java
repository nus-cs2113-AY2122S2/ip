package taskitems;

import taskitems.exceptions.IllegalInputException;
import taskitems.task.Deadline;
import taskitems.task.Event;
import taskitems.task.TaskList;
import taskitems.task.Todo;


import java.io.FileNotFoundException;

public class TaskManager {
    private final String PATH = "data.txt";
    private TaskList tasks = new TaskList();
    private TaskList bin = new TaskList();
    private Storage storage = new Storage(tasks);
    static Greet greet;

    public TaskManager() {
        try {
            storage.loadData();
        } catch (FileNotFoundException f) {
            System.out.println("No saved data found");
        }
    }

    public int getTaskCount() {
        return tasks.size;
    }

    private void printTask(int number) {
        System.out.println(tasks.getTask(number));
    }

    public void markTask(int number) throws NumberFormatException {
        if (number > tasks.size || number < 1) {
            throw new NumberFormatException();
        }
        if (tasks.getTask(number).isMarked()) {
            System.out.println("Err, this task is already marked...");
        } else {
            tasks.mark(number);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(tasks.getTask(number));
        }
        storage.saveData();
    }

    public void unmarkTask(int number) throws NumberFormatException {
        if(number > tasks.size || number < 1){
            throw new NumberFormatException();
        }
        if(!tasks.getTask(number).isMarked()){
            System.out.println("I cannot unmark something that was never marked...");
        } else {
            tasks.unmark(number);
            System.out.println("Okay Boss! The following task has been unmarked: ");
            System.out.println(tasks.getTask(number));
        }
        storage.saveData();
    }

    public void addToTasks(String taskName) throws IllegalInputException {
        if (taskName.equals("")) {
            throw new IllegalInputException();
        }
        tasks.add(new Todo(taskName));
        storage.saveData();
    }

    public void addToTasks(String type, String taskName,String date){
        if (type.equals("E")) {
            tasks.add(new Event(taskName, date));
        } else {
            tasks.add(new Deadline(taskName, date));
        }
        storage.saveData();
    }

    public void deleteTask(int number) throws NumberFormatException {
        if(number > tasks.size || number < 1){
            throw new NumberFormatException();
        } else {
            System.out.println("The following task has been shifted to the rubbish bin");
            printTask(number);
            System.out.println("You can say \"bin\" to view deleted items.");
            bin.add(tasks.getTask(number), true);
            tasks.delete(number);
        }
        storage.saveData();
    }

    public void printTasks() {
        if (tasks.size == 0) {
            System.out.println("You have not added any Tasks!");
        } else {
            for (int i = 1; i <= tasks.size ; i++) {
                System.out.print(i + ". ");
                System.out.println(tasks.getTask(i));
            }
        }
    }

    public void printDeletedTasks() {
        if (bin.size == 0) {
            System.out.println("There are no items in the rubbish bin right now");
        } else {
            System.out.println("Rubbish Bin:");
            for (int i = 1; i <= bin.size; i++) {
                System.out.println( (i) + ". " + bin.getTask(i));
            }
        }
    }
}

