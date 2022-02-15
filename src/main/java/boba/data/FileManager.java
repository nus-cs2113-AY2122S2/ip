package boba.data;

import boba.response.BobaResponse;
import boba.task.Deadline;
import boba.task.Event;
import boba.task.Task;
import boba.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that manages anything that relates to files
 * for Boba. Main functionality is reading and writing
 * to the save file
 */
public class FileManager {

    /** Magic Constants to better detail why constants are used */
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";
    private static final String DONE = "X";
    private static final String NOT_DONE = "O";
    private static final String SEPARATOR = " : ";
    private static final String NEW_LINE = "\n";

    /** The String containing the file path of the save file*/
    private String filePath;

    /**
     * Constructor that instantiates filePath
     * @param path The path to the save file
     */
    public FileManager(String path) {
        filePath = path;
    }

    /**
     * Reads the save file and interprets the data
     * stored to recreate the saved list
     * @return The list of tasks that were saved
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(filePath));
            String line = input.readLine();
            while (line != null) {
                taskList.add(parseLine(line));
                line = input.readLine();
            }
            input.close();
        } catch (IOException e) {
            BobaResponse.addResponse("Oh no! Looks like we not could retrieve your save file");
            BobaResponse.addResponse("Sorry, but you will have to start again on new list");
            BobaResponse.printResponse();
            createFile();
        }
        return taskList;
    }

    /**
     * Parse a line from a file and uses it parts
     * to recreate the Task stored.
     * @param line Line from a file
     * @return The saved Task
     */
    private Task parseLine(String line) {
        String[] tokens = line.split(SEPARATOR);
        Task task;
        if (tokens[0].equals(TODO)) {
            task = new Todo(tokens[2]);
        } else if (tokens[0].equals(DEADLINE)) {
            task = new Deadline(tokens[2], tokens[3]);
        } else if (tokens[0].equals(EVENT)) {
            task = new Event(tokens[2], tokens[3]);
        } else {
            task = new Task(tokens[2]);
        }
        if (tokens[1].equals(DONE)) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates the file and directory of the save file.
     * Use only if the directory or save file does not exist
     */
    private void createFile() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e){
            BobaResponse.printThis("Something really went wrong while creating a new save file");
        }
    }

    /**
     * Save the current list into the save file.
     * @param taskList The list of tasks
     */
    public void writeFile(ArrayList<Task> taskList) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                String description = task.getDescription();
                String mark = (task.isDone()) ? DONE : NOT_DONE;
                if (task instanceof Todo) {
                    output.write(TODO + SEPARATOR + mark + SEPARATOR + description);
                } else if (task instanceof Deadline) {
                    String by = ((Deadline) task).getBy();
                    output.write(DEADLINE + SEPARATOR + mark + SEPARATOR + description + SEPARATOR + by);
                } else {
                    String at = ((Event) task).getAt();
                    output.write(EVENT + SEPARATOR + mark + SEPARATOR + description + SEPARATOR + at);
                }
                output.write(NEW_LINE);
            }
            output.close();
        } catch (IOException e) {
            BobaResponse.printThis("Yikes! Something went wrong while saving the file");
        }
    }

}
