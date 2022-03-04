package marites;

import marites.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() {
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            return new TaskList();
        }
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            return (TaskList)objIn.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            return new TaskList();
        }
    }

    public void save(TaskList list) {
        try {
            File saveFile = new File(filePath);
            saveFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(list);
        } catch (IOException e) {
            System.out.println("WARNING: Task list not saved to disk successfully");
        }
    }
}
