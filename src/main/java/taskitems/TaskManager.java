package taskitems;


import helper.Parser;
import helper.Storage;
import helper.Ui;
import taskitems.exceptions.IllegalInputException;
import taskitems.task.Deadline;
import taskitems.task.Event;
import taskitems.task.TaskList;
import taskitems.task.Todo;


import java.io.FileNotFoundException;

// TaskManager class that deals with operations regarding Tasks and TaskLists
public class TaskManager {
    private final String PATH = "data.txt";
    private TaskList tasks = new TaskList();
    private TaskList bin = new TaskList();
    private Storage storage = new Storage(tasks);
    private Parser parser = new Parser();

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

    // Method to print a single task
    // @param (number) (number refers to the indexing(1-based) of that particular task in the list)
    private void printTask(int number) {
        ui.print(tasks.getTask(number));
    }

    // Method to mark a single task
    // @param (number) (number refers to the indexing(1-based) of that particular task in the list)
    // throws NumberFormatException when number does not fall within acceptable range
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

    // Method to unmark a single task
    // @param (number) (number refers to the indexing(1-based) of that particular task in the list)
    // throws NumberFormatException when number does not fall within acceptable range
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

    // Method to add a single Todo Task
    // @param (taskName) (refers to the name that user wishes to give the task)
    // throws IllegalInputException when taskName is empty
    public void addToTasks(String taskName) throws IllegalInputException {
        if (taskName.equals("")) {
            throw new IllegalInputException();
        }
        tasks.add(new Todo(taskName.trim()));
        storage.saveData();
    }

    // Method to add a single Event or Deadline Task
    // @param (type) (single character param either "E" or "D" referring to Event or Deadline resp)
    // @param (taskName) (refers to the name that user wishes to give the task)
    // @param (date) (refers to the date and time string in the "YYYY-MM-DD HH:MM:SS" format)
    public void addToTasks(String type, String taskName,String date){
        if (type.equals("E")) {
            Event event = new Event(taskName, date);
            if (event.getEndDate() != null && event.getEndTime() != null) {
                tasks.add(event);
            }
        } else {
            Deadline deadline = new Deadline(taskName, date);
            if (deadline.getEndDate() != null && deadline.getEndTime() != null) {
                tasks.add(deadline);
            }
        }
        storage.saveData();
    }

    // Method to delete a single task
    // @param (number) (number refers to the indexing(1-based) of that particular task in the list)
    // throws NumberFormatException when number does not fall within acceptable range
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

    // Method to retrieve a single task from the bin
    // @param (number) (number refers to the indexing(1-based) of that particular task in the list)
    // throws NumberFormatException when number does not fall within acceptable range
    public void retrieveTask(int number) throws NumberFormatException {
        if (number > bin.size || number < 1) {
            throw new NumberFormatException();
        } else {
            ui.print("The following task has been retrieved from the rubbish bin.");
            ui.print(bin.getTask(number));
            ui.printCont("You can say \"list\" to view all your saved items.");
            tasks.add(bin.getTask(number), true);
            bin.delete(number);
        }
    }

    // Method to print all tasks in list
    public void printTasks() {
        if (tasks.size == 0) {
            ui.print("You have not added any Tasks!");
        } else {
            ui.print("List: ");
            ui.print(tasks);
        }
    }

    // Method to print all tasks in bin
    public void printDeletedTasks() {
        if (bin.size == 0) {
            ui.print("There are no items in the rubbish bin right now");
        } else {
            ui.print("Rubbish Bin:");
            ui.print(bin);
        }
    }


    //Method to find task by name
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

