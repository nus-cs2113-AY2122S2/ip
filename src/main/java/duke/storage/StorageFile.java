package duke.storage;

import duke.exception.InvalidDataPathException;
import duke.tasks.TaskList;
import duke.util.TaskDecoder;
import duke.util.TaskEncoder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StorageFile {
    private String dataPath;
    private Path path;
    public StorageFile(String dataPath) throws InvalidDataPathException {
        if (!dataPath.endsWith(".txt")){
            throw new InvalidDataPathException();
        }
        this.dataPath = dataPath;
        this.path = Paths.get(dataPath);
    }

    public void save(TaskList taskList){
        try {
            FileWriter fw = new FileWriter(dataPath);
            String encodedTasks = TaskEncoder.encodeTasks(taskList);
            fw.write(encodedTasks);
            fw.close();
            System.out.println("Data is saved successfully.");
        }catch (IOException ioe){
            System.out.println("The file does not exist.");
        }
    }

    public TaskList load() throws InvalidDataPathException {
        try {
            return TaskDecoder.decodeTaskList(Files.readAllLines(path));
        } catch (IOException e) {
            throw new InvalidDataPathException();
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}

