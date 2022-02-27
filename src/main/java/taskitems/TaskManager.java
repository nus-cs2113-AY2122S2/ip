package taskitems;


import helper.Storage;
import helper.Ui;
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

    private Ui ui = new Ui();

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
        ui.print(tasks.getTask(number));
    }

    public void markTask(int number) throws NumberFormatException {
        if (number > tasks.size || number < 1) {
            throw new NumberFormatException();
        }
        if (tasks.getTask(number).isMarked()) {
            ui.print("Err, this task is already marked...");
        } else {
            tasks.mark(number);
            ui.print("Nice! I've marked this task as done: ");
            ui.print(tasks.getTask(number));
        }
        storage.saveData();
    }

    public void unmarkTask(int number) throws NumberFormatException {
        if(number > tasks.size || number < 1){
            throw new NumberFormatException();
        }
        if(!tasks.getTask(number).isMarked()){
            ui.print("I cannot unmark something that was never marked...");
        } else {
            tasks.unmark(number);
            ui.print("Okay Boss! The following task has been unmarked: ");
            ui.print(tasks.getTask(number));
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
            ui.print("The following task has been shifted to the rubbish bin");
            printTask(number);
            ui.printCont("You can say \"bin\" to view deleted items.");
            bin.add(tasks.getTask(number), true);
            tasks.delete(number);
        }
        storage.saveData();
    }

    public void printTasks() {
        if (tasks.size == 0) {
            ui.print("You have not added any Tasks!");
        } else {
            ui.print("List: ");
            ui.print(tasks);
        }
    }

    public void printDeletedTasks() {
        if (bin.size == 0) {
            ui.print("There are no items in the rubbish bin right now");
        } else {
            ui.print("Rubbish Bin:");
            ui.print(bin);
        }
    }

    public void findTask(String key) {
        ui.print("Following tasks matches your search");
        boolean hasPassed = false;
        for (int i = 1; i <= tasks.size; i++) {
            if (tasks.getTask(i).getName().contains(key.trim())) {
                ui.print(tasks.getTask(i));
                hasPassed = true;
            }
        }
        if (!hasPassed) {
            ui.printCont("No tasks matches your search term.");
        }
    }
}

