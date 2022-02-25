import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {

    public static final String FILE_PATH = "data/duke.txt";
    public static final String FOLDER_NAME = "data/";
    public static final String READABLE_FILE_PATH = "data/list.txt";

    public static void load() {
        try {
            FileInputStream fileIn = new FileInputStream(FILE_PATH);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj;
            while (true) {
                try {
                    obj = objectIn.readObject();
                } catch (EOFException e) {
                    break;
                }
                TaskList.add((Task) obj);
            }
            System.out.println("Task File Uploaded\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No input file located\n");
        }
    }

    private static void saveListState() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

        for (int i = 0; i < TaskList.size(); i++) {
            objectOut.writeObject(TaskList.get(i));
        }
        objectOut.close();
    }

    private static void saveReadableList() throws IOException {
        FileWriter fw = new FileWriter(READABLE_FILE_PATH);
        for (int i = 0; i < TaskList.size(); i++) {
            int listIndex = i + 1;
            fw.write(listIndex + "." + TaskList.get(i) + System.lineSeparator());
        }
        fw.close();
    }

    public static void save() {
        File dir = new File(FOLDER_NAME);
        dir.mkdirs();
        try {
            saveListState();
            saveReadableList();
        } catch (IOException e) {
            System.out.println("IO Error");
        }
        System.out.println("Task File Updated");
    }

}
