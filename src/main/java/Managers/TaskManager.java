package Managers;

import java.util.ArrayList;

import Components.Task;

import Exceptions.BadIndexException;
import Exceptions.MaxTaskException;



import java.io.IOException;

import static Constants.TaskManagerConstants.MAX_TASKS;

public class TaskManager {
    private ArrayList<Task> tasks;
    private int numTasks;
    private Storage storage;

    public TaskManager(Storage storage) {
        numTasks = 0;
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    public void addTask(Task task) throws MaxTaskException {
        try {
            if (numTasks == MAX_TASKS) {
                throw new MaxTaskException("Max tasks reached");
            }

            numTasks++;
            tasks.add(task);
            saveTasklist();
        } catch (Exception e) {
            throw e;
        }
    }

    public Task deleteTask(int index) throws BadIndexException, NumberFormatException {
        try {
            Task deletedTask = tasks.remove(index);
            numTasks--;
            saveTasklist();
            return deletedTask;
        } catch (Exception e) {
            throw e;
        }
    }

    public String markTask(int index) throws NumberFormatException, BadIndexException {
        try {

            if (index > numTasks){
                throw new BadIndexException("No task number " + index);
            }

            tasks.get(index).setIsDone(true);
            saveTasklist();
            return tasks.get(index).toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public String unmarkTask(int index) throws NumberFormatException, BadIndexException {
        try {
            if (index > numTasks){
                throw new BadIndexException("No task number " + index);
            }

            tasks.get(index).setIsDone(false);
            saveTasklist();
            return tasks.get(index).toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listTasks() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(i+1 + ". " + tasks.get(i).toString());
        }
    }

    public int getNumTasks() {
        return numTasks;
    }

    public void loadTasklist() throws IOException {
        try {
            tasks = storage.loadTasklist();
            numTasks = tasks.size();
        } catch (IOException e) {
            throw e;
        }
    }

    private void saveTasklist() {
        try {
            storage.saveTasklist(tasks);
        } catch (IOException e) {
            System.out.println("Saving failed.");
        }
    }
}
