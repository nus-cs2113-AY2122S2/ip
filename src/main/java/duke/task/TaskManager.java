package duke.task;

import duke.exception.DukeException;
import duke.iomethods.IOMethods;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int numOfTasks = 0;

    public TaskManager(){

    }

    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(String type, String description) {
        Task toBeAdded;
        ArrayList<String> words = IOMethods.splitToTwo(description, "/");

        String taskName = words.get(0);
        String addInfo = words.size() >= 2 ? words.get(1) : null;

        switch (type) {
        case "todo":
            toBeAdded = new ToDo(taskName);
            break;
        case "deadline":
            toBeAdded = new Deadline(taskName, addInfo);
            break;
        case "event":
            toBeAdded = new Event(taskName, addInfo);
            break;
        default:
            toBeAdded = new Task(taskName);
            break;

        }
        this.numOfTasks++;
        this.tasks.add(toBeAdded);

        IOMethods.printWithDivider("Got it. I've added this task:\n\t" + toBeAdded.toString()
                + String.format("\nNow you have %d task%s in the list.", this.numOfTasks,
                this.numOfTasks > 1 ? "s" : ""));

    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numOfTasks = tasks.size();
    }

    public void markCompleted  (int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.setCompleted(true);
            IOMethods.printWithDivider(task.toString());
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds!");
        }
    }

    public void unmarkCompleted (int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.setCompleted(false);
            IOMethods.printWithDivider(task.toString());
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds!");
        }
    }

    @Override
    public String toString() {
        String output = "";
        int number = 1 ;
        for (Task item : this.tasks) {
            output += String.format("%d. %s", number, item.toString());
            if (number != numOfTasks) {
                output += "\n";
            }
            number ++;
        }
        return output;
    }
}
