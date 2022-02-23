package bim;

import bim.task.Deadline;
import bim.task.Event;
import bim.task.Task;
import bim.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with the storage of tasks created by the user. This includes reading the file and restoring previously
 * created tasks, updating any tasks already in the data file and writing newly created tasks into the file.
 */
public class Storage {
    private File dataDirectory;
    private File dataFile;

    private static final String ERROR_WRITING_DATA_FILE = "Failed to write to data file!";
    private static final String ERROR_FINDING_DATA_FILE = "Could not find data file!";
    private static final String ERROR_CREATING_DATA_FILE = "Failed to create data file!";

    private static final String DELIMITER_DATA = " \\| ";

    private static final String OP_MARK = "mark";

    private static final String TYPE_DEADLINE = "D";
    private static final String TYPE_EVENT = "E";
    private static final String TYPE_TODO = "T";

    private static final String DATA_FOLDER_NAME = "data";
    private static final String DATA_FILE_NAME = "bimData.txt";
    private static final String DATA_FILE_SEPARATOR = " | ";
    private static final String DATA_FILE_UNMARKED_TASK = "0";
    private static final String DATA_FILE_MARKED_TASK = "1";
    private static final String DATA_FILE_EMPTY_FIELD = "-";
    private static final String NEWLINE = "\n";

    public Storage() {
        this.dataDirectory = new File(getDataDirectoryPath());
        this.dataFile = new File(getDataFilePath());
    }

    private String getDataDirectoryPath() {
        return System.getProperty("user.dir") + "\\" + DATA_FOLDER_NAME;
    }

    private String getDataFilePath() {
        return getDataDirectoryPath() + "\\" + DATA_FILE_NAME;
    }

    private Task createTask(String type, String mark, String description, String date) {
        Task newTask;
        switch (type) {
        case TYPE_EVENT:
            newTask = new Event(description, date);
            break;
        case TYPE_DEADLINE:
            newTask = new Deadline(description, date);
            break;
        default:
            newTask = new ToDo(description);
            break;
        }
        if (mark.equals(DATA_FILE_MARKED_TASK)) {
            newTask.setAsDone();
        }
        return newTask;
    }

    /**
     * Create a new data directory and data file.
     *
     * @throws BimException If the file or directory cannot be created
     */
    public void createDataFile() throws BimException {
        try {
            dataDirectory.mkdir();
            dataFile.createNewFile();
        } catch (IOException exception) {
            throw new BimException(ERROR_CREATING_DATA_FILE);
        }
    }

    /**
     * Reads the data file. If the data file cannot be read, a new data file is created.
     * Else, tasks are created from the content of the data file and added to an arraylist. <br>
     * Format: TASK_TYPE | MARK | DESCRIPTION | DATE <br>
     * TASK_TYPE - D for Deadline, E for Event and T for ToDo <br>
     * MARK - 1 if marked, 0 if unmarked <br>
     * DESCRIPTION - Task description <br>
     * DATE - Deadline or time of event. '-' is used for ToDo tasks
     *
     * @return An arraylist of tasks imported from the data file.
     * @throws BimException If the data file cannot be found and/or the directory and file could not be created.
     */
    public ArrayList<Task> loadDataFile() throws BimException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner dataReader = new Scanner(dataFile);
            while (dataReader.hasNextLine()) {
                String task = dataReader.nextLine();
                String[] taskParts = task.split(DELIMITER_DATA);
                tasks.add(createTask(taskParts[0], taskParts[1], taskParts[2], taskParts[3]));
            }
            dataReader.close();
        } catch (FileNotFoundException exception) {
            try {
                createDataFile();
            } catch (BimException bimexception) {
                throw new BimException(ERROR_FINDING_DATA_FILE + NEWLINE + bimexception.getMessage());
            }
            throw new BimException(ERROR_FINDING_DATA_FILE);
        }
        return tasks;
    }

    /**
     * Saves a task in the data file.
     *
     * @param newTask Newly added task
     * @throws BimException If file could not be written to.
     */
    public void writeData(Task newTask) throws BimException {
        try {
            FileWriter writer = new FileWriter(getDataFilePath(), true);
            if (newTask instanceof Event) {
                writer.write(TYPE_EVENT + DATA_FILE_SEPARATOR + DATA_FILE_UNMARKED_TASK + DATA_FILE_SEPARATOR
                        + newTask.getDescription() + DATA_FILE_SEPARATOR + ((Event) newTask).getDate() + NEWLINE);
            }
            else if (newTask instanceof Deadline) {
                writer.write(TYPE_DEADLINE + DATA_FILE_SEPARATOR + DATA_FILE_UNMARKED_TASK + DATA_FILE_SEPARATOR
                        + newTask.getDescription() + DATA_FILE_SEPARATOR + ((Deadline) newTask).getDeadline() + NEWLINE);
            }
            else {
                writer.write(TYPE_TODO + DATA_FILE_SEPARATOR + DATA_FILE_UNMARKED_TASK + DATA_FILE_SEPARATOR
                        + newTask.getDescription() + DATA_FILE_SEPARATOR + DATA_FILE_EMPTY_FIELD + NEWLINE);
            }
            writer.close();
        } catch (IOException exception) {
            throw new BimException(ERROR_WRITING_DATA_FILE);
        }
    }

    /**
     * Updates the file when a task is marked or unmarked.
     * The whole file will be read line by line and changes will be made only to the task to be modified
     * The data file will then be overwritten with the new content.
     *
     * @param mode  mark/unmark
     * @param index the index of the task to be marked / unmarked
     * @throws BimException If file could not be written to.
     */
    public void modifyData(String mode, int index) throws BimException {
        try {
            Scanner dataReader = new Scanner(dataFile);
            String newDataFileContent = "";
            int i = 0;
            while (dataReader.hasNextLine()) {
                String currentLine = dataReader.nextLine();
                if (i == index) {
                    String[] currentParts = currentLine.split(DELIMITER_DATA);
                    if (mode.equals(OP_MARK)) {
                        currentParts[1] = DATA_FILE_MARKED_TASK;
                    }
                    else {
                        currentParts[1] = DATA_FILE_UNMARKED_TASK;
                    }
                    currentLine = String.join(DATA_FILE_SEPARATOR, currentParts);
                }
                newDataFileContent += currentLine + NEWLINE;
                i++;
            }

            FileWriter writer = new FileWriter(dataFile);
            writer.write(newDataFileContent);
            dataReader.close();
            writer.close();
        } catch (IOException exception) {
            throw new BimException(ERROR_WRITING_DATA_FILE);
        }
    }

    /**
     * Updates the file when a task is deleted
     * The whole file will be read line by line and changes will be made only to the task to be deleted
     * The data file will then be overwritten with the new content.
     *
     * @param index The index of the deleted task
     * @throws BimException If the file could not be written to.
     */
    public void deleteData(int index) throws BimException {
        try {
            Scanner dataReader = new Scanner(dataFile);
            String newDataFileContent = "";
            int i = 0;

            while (dataReader.hasNextLine()) {
                String currentLine = dataReader.nextLine();
                if (i != index) {
                    newDataFileContent += currentLine + NEWLINE;
                }
                ++i;
            }

            FileWriter writer = new FileWriter(dataFile);
            writer.write(newDataFileContent);
            dataReader.close();
            writer.close();
        } catch (IOException exception) {
            throw new BimException(ERROR_WRITING_DATA_FILE);
        }
    }
}
