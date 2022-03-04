package duke;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int taskIndex) {
        return this.tasks.remove(taskIndex);
    }

    public void writeTasksToFile() {
        // Write tasks to file
        try {
            String output = "";
            for (int i = 0; i < this.tasks.size(); i++) {
                output += tasks.get(i).saveString() + System.lineSeparator();
            }
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(output);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}
