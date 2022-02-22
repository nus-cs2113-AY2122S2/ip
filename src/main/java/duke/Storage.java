package duke;

import duke.exceptions.DukeException;
import duke.exceptions.FileWriteException;
import duke.exceptions.FileReadException;
import duke.exceptions.IncorrectFileFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Storage {

    private static final String FILEIO_ERR_CREATING_FILE = "creating data file.";
    private static final String FILEIO_ERR_WRITING_FILE = "writing to data file.";
    private String filePath;
    /**
     * Reads from filePath and constructs a TaskList based on the given file
     * @param filePath the file to read from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        // load data into taskList
        ArrayList<Task> bufferTaskList = new ArrayList<Task>();
        try {
            File dataFile = new File(filePath);
            Scanner dataFileReader = new Scanner(dataFile);
            while (dataFileReader.hasNextLine()) {
                String data = dataFileReader.nextLine();
                // Data Format: <task type> | <0/1 representing unmarked/marked> | <description> | <date if applicable>
                String[] splitData = data.split(" \\| ");
                Task taskToAdd = createTask(splitData);
                bufferTaskList.add(taskToAdd);
            }
        } catch (FileNotFoundException e) {
            // No data file found
            DukeException exception = new FileReadException();
            throw exception;
        } catch (IllegalArgumentException e) {
            // Most probably attributed to error in data file format, specifically the column indicating task type.
            DukeException exception = new IncorrectFileFormatException();
            throw exception;
        } catch (IndexOutOfBoundsException e) {
            // Most probably attributed to error in data file format, such as indicating "Deadline" without having a proper date.
            DukeException exception = new IncorrectFileFormatException();
            throw exception;
        }
        return bufferTaskList;
    }

    public void write(TaskList taskList) throws FileWriteException {
        File fileToWriteTo = new File(filePath);
        createRequiredFiles(fileToWriteTo);
        writeToFile(fileToWriteTo, taskList);
    }

    private Task createTask(String[] splitData) {
        TaskType taskType = TaskType.fromString(splitData[0]);
        boolean isDone = splitData[1].equals("1");
        Task taskToAdd;
        switch (taskType) {
        case Todo:
            taskToAdd = new Todo(splitData[2]);
            taskToAdd.setIsDone(isDone);
            break;
        case Event:
            taskToAdd = new Event(splitData[2], splitData[3]);
            taskToAdd.setIsDone(isDone);
            break;
        case Deadline:
            taskToAdd = new Deadline(splitData[2], splitData[3]);
            taskToAdd.setIsDone(isDone);
            break;
        default:
            taskToAdd = null;
            break;
        }
        return taskToAdd;
    }

    private void createRequiredFiles(File fileToWriteTo) throws FileWriteException {
        try {
            fileToWriteTo.getAbsoluteFile().getParentFile().mkdirs();
            fileToWriteTo.createNewFile();
        } catch (IOException e) {
            FileWriteException exception = new FileWriteException(FILEIO_ERR_CREATING_FILE);
            throw exception;
        }
    }

    private void writeToFile(File fileToWriteTo, TaskList taskList) throws FileWriteException {
        try {
            //overwrite data
            FileWriter writer = new FileWriter(fileToWriteTo, false);
            for (int i = 0; i<taskList.size(); i++) {
                Queue<String> infoToWrite = new LinkedList<String>();
                Task task = taskList.get(i);
                task.getFileWriterFormatString(infoToWrite);
                String stringToWrite = infoToWrite.poll();
                // no information to write to begin with
                if (stringToWrite == null) {
                    continue;
                }
                while (infoToWrite.peek() != null) {
                    //delimiter
                    stringToWrite += " | ";
                    String infoBit= infoToWrite.poll();
                    stringToWrite += infoBit;
                }
                writer.write(stringToWrite+"\n");
            }
            writer.close();
        } catch (IOException e) {
            FileWriteException exception = new FileWriteException(FILEIO_ERR_WRITING_FILE);
            throw exception;
        }
    }
}
