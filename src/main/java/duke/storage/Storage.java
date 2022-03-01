package duke.storage;

import duke.TaskList;
import duke.command.Command;
import duke.exception.AdditionalException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage manager that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from a file to a new list of tasks.
     * It creates a new list of tasks and passes it to the readFromFile method.
     *
     * @return The list of tasks that was stored in the file initially.
     * @throws FileNotFoundException If no file with the filepath is found.
     * @throws AdditionalException If the file has been edited and hence the fullCommand is incorrect.
     * @see FileNotFoundException
     * @see AdditionalException
     */
    public ArrayList<Task> load() throws FileNotFoundException, AdditionalException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        readFromFile(listOfTasks);
        return listOfTasks;
    }

    /**
     * Reads the commands line by line from the file.
     * Using the command object returned from the parser, it will then run the executeFromFile method.
     *
     * @throws FileNotFoundException If no file with the filepath is found.
     * @throws AdditionalException If the file has been edited and hence the fullCommand is incorrect.
     * @see FileNotFoundException
     * @see AdditionalException
     */
    private void readFromFile(ArrayList<Task> listOfTasks) throws FileNotFoundException, AdditionalException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            Command c = Parser.parseFromFile(nextLine);
            c.executeFromFile(listOfTasks);
            executeIfMarked(nextLine, listOfTasks);
        }
    }

    /**
     * Checks if the task that is loaded form file is marked.
     * If the task is marked, a command object is created and the method executeFromFile of the object will run.
     *
     * @param nextLine The next line from the file.
     * @param listOfTasks The list of tasks that was stored in the file initially.
     * @throws AdditionalException If there is an error when marking the file.
     * @see AdditionalException
     */
    private void executeIfMarked(String nextLine, ArrayList<Task> listOfTasks) throws AdditionalException {
        String[] words = nextLine.split("\\|");
        String toMark = words[1].toLowerCase();
        if (toMark.equalsIgnoreCase("true")) {
            String fullCommand = "mark " + Integer.toString(listOfTasks.size());
            Command c = Parser.parse(fullCommand);
            c.executeFromFile(listOfTasks);
        }
    }

    /**
     * Saves a task to the file.
     *
     * @param task This is the task to be saved to the file.
     * @throws IOException If there is an error writing to the file.
     * @see IOException
     */
    public void save(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        writeDataToFile(task, task.getTypeOfTask(), fileWriter);
        fileWriter.close();
    }

    /**
     * Saves all the tasks after the tasks have been marked, unmarked or deleted.
     *
     * @param tasks This is the TaskList tasks that contains all the tasks.
     * @throws IOException If there is an error writing to the file.
     * @see IOException
     */
    public void saveAll(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        saveAllTasksToFile(fileWriter, tasks);
        fileWriter.close();
    }


    /**
     * Iterates through all the tasks in the TaskList and writes them to file.
     *
     * @param fileWriter The fileWriter is used to write the data to file.
     * @param tasks The Task List that contains the list of tasks.
     * @throws IOException If there is an error writing to the file.
     * @see IOException
     */
    private void saveAllTasksToFile(FileWriter fileWriter, TaskList tasks) throws IOException {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            writeDataToFile(task, task.getTypeOfTask(), fileWriter);
        }
    }

    /**
     * Parses the tasks and saves it in the file using a format that can be easily read in the future.
     *
     * @param task The task to be saved to the file.
     * @param typeOfTask The type of task to be saved to the file.
     * @param fileWriter The fileWriter is used to write the data to the file.
     * @throws IOException If there is an error writing to the file.
     * @see IOException
     */
    private void writeDataToFile(Task task, String typeOfTask, FileWriter fileWriter) throws IOException {
        switch(typeOfTask) {
        case "todo":
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() + "\n");
            break;
        case "deadline":
            Deadline deadline = (Deadline) task;
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() +
                    "|/by " + deadline.getDate() + "\n");
            break;
        case "event":
            Event event = (Event) task;
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() +
                    "|/at " + event.getLocation() + "|/on " + event.getDate() + "\n");
            break;
        default:
            System.out.println("Error writing data to file");
        }
    }

}
