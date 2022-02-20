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
     * This is the load method that loads the tasks from a file to a new list of tasks.
     * It creates a new list of tasks and passes it to the readFromFile method.
     *
     * @return The list of tasks that was stored in the file initially
     * @throws FileNotFoundException If no file with the filepath is found
     * @throws AdditionalException
     * @see FileNotFoundException
     * @see AdditionalException
     */
    public ArrayList<Task> load() throws FileNotFoundException, AdditionalException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        readFromFile(listOfTasks);
        return listOfTasks;
    }

    /**
     * This is the readFromFile method that reads the commands line by line from the file.
     * Using the command object returned from the parser, it will the run the executeFromFile method.
     *
     * @throws FileNotFoundException If no file with the filepath is found
     * @throws AdditionalException
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

    private void executeIfMarked(String nextLine, ArrayList<Task> listOfTasks) throws AdditionalException {
        String[] words = nextLine.split("\\|");
        String toMark = words[1].toLowerCase();
        if (toMark.equalsIgnoreCase("true")) {
            String fullCommand = "mark " + Integer.toString(listOfTasks.size());
            Command c = Parser.parse(fullCommand);
            c.executeFromFile(listOfTasks);
        }
    }

    private String removeToMark(String[] words, String[] actualRequest, String request) {
        for (int i = 2; i < words.length; i++) {
            actualRequest[i - 1] = words[i];
            request += " ";
            request += words[i];
        }
        return request;
    }

    /**
     * This is the save method that takes in a task and saves this task to the file.
     *
     * @param task This is the task to be saved to the file
     * @throws IOException If there is an error writing to the file
     * @see IOException
     */
    public void save(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        writeDataToFile(task, task.getTypeOfTask(), fileWriter);
        fileWriter.close();
    }

    /**
     * This is the saveAll function that saves all the tasks after the tasks have been marked, unmarked or deleted.
     * The method takes in the Tasklist tasks, creates and new FileWriter and passes both to another method
     * saveAllTasksToFile.
     *
     * @param tasks This is the TaskList tasks that contains all the tasks
     * @throws IOException If there is an error writing to the file
     * @see IOException
     */
    public void saveAll(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        saveAllTasksToFile(fileWriter, tasks);
        fileWriter.close();
    }


    /**
     * This is the saveAllTasksToFile that iterates through all the tasks in the TaskList and write it to file by
     * passing the task, the type of task of the description and the fileWriter to writeDataToFile method.
     *
     * @param fileWriter The fileWriter is used to write the data to file
     * @param tasks The Task List that contains the list of tasks
     * @throws IOException If there is an error writing to the file
     * @see IOException
     */
    private void saveAllTasksToFile(FileWriter fileWriter, TaskList tasks) throws IOException {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            writeDataToFile(task, task.getTypeOfTask(), fileWriter);
        }
    }

    /**
     * This is the writeDataToFile method that takes in the task, the typeOfTask and fileWriter.
     * It parses the task and saves it in a format such that it can be easily read in the future.
     *
     * @param task The task to be saved to the file
     * @param typeOfTask The type of task to be saved to the file
     * @param fileWriter The fileWriter is used to write the data to the file
     * @throws IOException If there is an error writing to the file
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
                    "|/by " + deadline.getBy() + "\n");
            break;
        case "event":
            Event event = (Event) task;
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() +
                    "|/at " + event.getAt() + "\n");
            break;
        default:
            System.out.println("Error writing data to file");
        }
    }

}
