package storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.DaoReadStreamBrokenDukeException;
import exceptions.DaoWriteStreamBrokenDukeException;
import exceptions.DukeException;
import tasks.Task;
import tasks.TaskFactory;



/**
 * Dao for task
 */
public class TaskFileStorage extends FileStorage {

    /**
     * Initializes a file data access object
     *
     * @param basePath The base path for the file
     * @param fileName The name of the file
     * @throws DukeException Exception in creating taskFileDAO
     */
    public TaskFileStorage(String basePath, String fileName) throws DukeException {
        super(basePath, fileName);
    }

    /**
     * Writes the tasks in taskList into a local file
     *
     * @param taskList List of tasks
     * @throws DukeException Write stream broken exception
     */
    public void writeTasks(ArrayList<Task> taskList) throws DukeException {
        try {
            FileOutputStream writeData = new FileOutputStream(targetFile);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeInt(taskList.size());
            writeStream.writeChar(START_INDICATOR);
            for (int i = 0; i < taskList.size(); i++) {
                writeStream.writeObject(taskList.get(i).compress());
            }
        } catch (Exception e) {
            throw new DaoWriteStreamBrokenDukeException();
        }
    }

    /**
     * Reads all the tasks stored in the task dao file
     *
     * @return List of tasks
     * @throws DukeException Read stream broken exception
     */
    public ArrayList<Task> readTasks() throws DukeException {
        ArrayList<Task> newTaskList = new ArrayList<>();
        try {
            FileInputStream readData = new FileInputStream(targetFile);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            int size = readStream.readInt();
            readStream.readChar();
            TaskFactory taskFactory = new TaskFactory();
            for (int i = 0; i < size; i++) {
                Task currentTask = taskFactory.decompressTask((HashMap<String, Object>) readStream.readObject());
                newTaskList.add(currentTask);
            }
            readStream.close();
        } catch (Exception e) {
            throw new DaoReadStreamBrokenDukeException();
        }
        return newTaskList;
    }
}
