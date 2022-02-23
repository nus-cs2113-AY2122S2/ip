package jrobo.storage;

import jrobo.command.InputParser;
import jrobo.exception.InvalidTypeException;
import jrobo.task.Task;
import jrobo.task.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Storage is the class that provides save and load functionalities on each run of the program.
 *
 * @author Ege Demirkirkan
 */
public class Storage {
    TaskManager manager;

    public Storage(TaskManager manager) {
        this.manager = manager;
    }

    /**
     * This method saves tasks to the storage
     */
    public void save() {
        try {
            FileWriter fileWriter = new FileWriter("./ip/data/tasks.txt", true);
            clear();
            for (Task task : manager.getTaskList()) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            manager.printWithSeparator(e.getMessage());
        }
    }

    /**
     * This method loads tasks from the storage
     */
    public void load() {
        try {
            File file = new File("./ip/data/tasks.txt");

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String taskStr = scanner.nextLine();
                InputParser parser = new InputParser(taskStr);
                String[] taskDetails = parser.strToTask(taskStr);
                manager.addTask(taskDetails[0], taskDetails[1], taskDetails[2], true);
            }
        } catch (IOException | InvalidTypeException e) {
            manager.printWithSeparator(e.getMessage());
        }
    }

    private void clear() {
        try {
            FileWriter fwOb = new FileWriter("./ip/data/tasks.txt", false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException e) {
            manager.printWithSeparator(e.getMessage());
        }
    }
}
