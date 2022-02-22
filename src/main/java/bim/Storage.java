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

public class Storage {
    private File dataDirectory;
    private File dataFile;

    private static final String ERROR_WRITING_DATA_FILE = "Failed to write to data file!";
    private static final String ERROR_FINDING_DATA_FILE = "Could not find data file!";
    private static final String ERROR_CREATING_DATA_FILE = "Failed to create data file!";

    private static final String DELIMITER_DATA = " \\| ";

    private static final String OP_MARK = "mark";
    private static final String OP_UNMARK = "unmark";

    private static final String DATA_FOLDER_NAME = "data";
    private static final String DATA_FILE_NAME = "bimData.txt";
    private static final String DATA_FILE_SEPARATOR = " | ";
    private static final String DATA_FILE_NEW_LINE = "\n";
    private static final String DATA_FILE_DEADLINE = "D";
    private static final String DATA_FILE_EVENT = "E";
    private static final String DATA_FILE_TODO = "T";
    private static final String DATA_FILE_UNMARKED_TASK = "0";
    private static final String DATA_FILE_MARKED_TASK = "1";

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

    /**
     * Create a new data directory and data file.
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
     * Else, tasks are created from the content of the data file and added to an arraylist.
     * @return An arraylist of tasks imported from the data file.
     * @throws BimException If the data file cannot be found.
     */
    public ArrayList<Task> loadDataFile() throws BimException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner dataReader = new Scanner(dataFile);
            while (dataReader.hasNextLine()) {
                String task = dataReader.nextLine();
                String[] taskParts = task.split(DELIMITER_DATA);
                Task newTask;

                switch (taskParts[0]) {
                case DATA_FILE_EVENT:
                    newTask = new Event(taskParts[2], taskParts[3]);
                    break;
                case DATA_FILE_DEADLINE:
                    newTask = new Deadline(taskParts[2], taskParts[3]);
                    break;
                default:
                    newTask = new ToDo(taskParts[2]);
                    break;
                }
                if (taskParts[1].equals(DATA_FILE_MARKED_TASK)) {
                    newTask.setAsDone();
                }
                tasks.add(newTask);
            }
            dataReader.close();
        } catch (FileNotFoundException exception) {
            createDataFile();
            throw new BimException(ERROR_FINDING_DATA_FILE);
        }

        return tasks;
    }

    /**
     * Appends the newly added task to the file.
     * @param newTask Newly added task
     * @throws BimException If file could not be written to.
     */
    public void writeData(Task newTask) throws BimException {
        try {
            FileWriter writer = new FileWriter(getDataFilePath(), true);
            if (newTask instanceof ToDo) {
                writer.write(DATA_FILE_TODO + DATA_FILE_SEPARATOR + DATA_FILE_UNMARKED_TASK + DATA_FILE_SEPARATOR + newTask.getDescription() + DATA_FILE_NEW_LINE);
            }
            else if (newTask instanceof Deadline) {
                writer.write(DATA_FILE_DEADLINE + DATA_FILE_SEPARATOR + DATA_FILE_UNMARKED_TASK + DATA_FILE_SEPARATOR + newTask.getDescription() + DATA_FILE_SEPARATOR + ((Deadline) newTask).getDeadline() + DATA_FILE_NEW_LINE);
            }
            else {
                writer.write(DATA_FILE_EVENT + DATA_FILE_SEPARATOR + DATA_FILE_UNMARKED_TASK + DATA_FILE_SEPARATOR + newTask.getDescription() + DATA_FILE_SEPARATOR + ((Event) newTask).getDate() + DATA_FILE_NEW_LINE);
            }
            writer.close();
        } catch (IOException exception) {
            throw new BimException(ERROR_WRITING_DATA_FILE);
        }
    }

    /**
     * Updates the file when a task is marked or unmarked.
     * @param mode mark/unmark
     * @param index the index of the task to be marked / unmarked
     * @throws BimException If file could not be written to.
     */
    public void modifyData(String mode, int index) throws BimException {
        try {
            Scanner dataReader = new Scanner(dataFile);
            String dataFileContent = "";
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
                dataFileContent += currentLine + DATA_FILE_NEW_LINE;
                ++i;
            }
            FileWriter writer = new FileWriter(getDataFilePath());
            writer.write(dataFileContent);
            dataReader.close();
            writer.close();

        } catch (IOException exception) {
            throw new BimException(ERROR_WRITING_DATA_FILE);
        }
    }

    /**
     * Updates the file when a task is deleted
     * @param index The index of the deleted task
     * @throws BimException If the file could not be written to.
     */
    public void deleteData(int index) throws BimException {
        try {
            Scanner dataReader = new Scanner(dataFile);
            String dataFileContent = "";
            int i = 0;

            while (dataReader.hasNextLine()) {
                String currentLine = dataReader.nextLine();
                if (i != index) {
                    dataFileContent += currentLine + DATA_FILE_NEW_LINE;
                }
                ++i;
            }
            FileWriter writer = new FileWriter(getDataFilePath());
            writer.write(dataFileContent);
            dataReader.close();
            writer.close();
        } catch (IOException exception) {
            throw new BimException(ERROR_WRITING_DATA_FILE);
        }
    }
}
