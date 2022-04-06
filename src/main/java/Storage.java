import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The Storage class handles anything related to the loading or saving of tasks
 */
public class Storage {

    public static final String FILE_PATH = "data/duke.txt";
    public static final String FOLDER_NAME = "data/";
    public static final String READABLE_FILE_PATH = "data/list.txt";

    /**
     * Locate the file specified by FILE_PATH and read each object in the file
     * Cast each object to Task, and add these tasks into the user's TaskList
     */
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

    /**
     * Locate the output file specified by FILE_PATH
     * Cast every Task in user's TaskList as object, and save them in the output file
     *
     * @throws IOException If saving TaskList into the output file failed or was interrupted
     */
    private static void saveListState() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        for (int i = 0; i < TaskList.size(); i++) {
            objectOut.writeObject(TaskList.get(i));
        }
        objectOut.close();
    }

    /**
     * Locate the output file specified by READABLE_FILE_PATH
     * Write every Task in user's TaskList into the output file
     *
     * @throws IOException If writing Tasks into the output file failed or was interrupted
     */
    private static void saveReadableList() throws IOException {
        FileWriter fw = new FileWriter(READABLE_FILE_PATH);
        for (int i = 0; i < TaskList.size(); i++) {
            int listIndex = i + 1;
            fw.write(listIndex + "." + TaskList.get(i) + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Save the current tasks in user's TaskList to create a persistent storage feature
     * There are 2 things to be saved - File that stores user's TaskList as an object (unreadable)
     *                                - File that stores a readable version of the tasks in user's TaskList
     */
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
