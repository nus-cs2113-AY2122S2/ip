package duke.storage;

import duke.exception.InvalidDataPathException;
import duke.tasks.TaskList;
import duke.util.TaskDecoder;
import duke.util.TaskEncoder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the file which is used to store data.
 */

public class StorageFile {
    private String fileName;
    private String dataDirectory = "./Data/";
    private Path path;

    /**
     * @param dataPath path of the file
     * @throws InvalidDataPathException if the path is invalid
     */
    public StorageFile(String dataPath) throws InvalidDataPathException {
        if (!dataPath.endsWith(".txt")){
            throw new InvalidDataPathException();
        }
        this.fileName = dataPath;
        this.path = Paths.get(dataPath);
    }

    /**
     * Saves the {@code taskList} data.
     * @param taskList task list that contains all tasks
     */

    public void save(TaskList taskList){
        try {
            FileWriter fw = new FileWriter(dataDirectory+fileName);
            String encodedTasks = TaskEncoder.encodeTasks(taskList);
            fw.write(encodedTasks);
            fw.close();
            System.out.println("Data is saved successfully.");
        }catch (IOException ioe){
            System.out.println("The file does not exist.");
        }
    }

    /**
     * Load the {@code taskList} data from the storage file.
     * @return task list
     * @throws InvalidDataPathException if the path is invalid.
     */
    public TaskList load() throws InvalidDataPathException, IOException {
        try {
            return TaskDecoder.decodeTaskList(Arrays.asList(Files.readString(Paths.get(dataDirectory+fileName)).split("\n")));
        } catch (IOException e) {
            createFile(dataDirectory, fileName);
            return new TaskList();
        }
    }

    public static void createFile(String directory, String fileName){
        try{
            Files.createDirectory(Paths.get(directory));
            Files.createFile(Paths.get(directory+fileName));
            System.out.println("Yay! File created successfully. You can start using Duke now.");
        }
        catch (FileAlreadyExistsException e){

        }
         catch (IOException e) {
            System.out.println("File created unsuccessfully.");
        }
    }

}

