package duke;

import task.Task;

import java.io.*;
import java.util.ArrayList;

import static duke.Duke.taskCounter;
import static duke.Duke.taskList;
import static errors.Errors.ErrorTypes.FILE_NOT_FOUND;
import static errors.Errors.ErrorTypes.IO_ERROR;

public class FileStrorage {

    public static final String DIRECTORYPATH = "data/";
    public static final String FILENAME = "/duke.txt";
    //get home path of user
    private static final String HOME_PATH = System.getProperty("user.dir");

        public static void saveToFile(ArrayList<Task> taskArrayList){
            // check if file exists, if not create it
            File directory = new File(DIRECTORYPATH);
            directory.mkdir();
            try{
                FileOutputStream fileOutStream = new FileOutputStream(DIRECTORYPATH + FILENAME);
                ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
                objectOutStream.writeObject(taskArrayList);
                objectOutStream.close();
            } catch (FileNotFoundException fnfe) {
                System.out.println(FILE_NOT_FOUND);
            } catch (IOException e) {
                System.out.println(IO_ERROR);
            }
            System.out.println("Updated file with task list");
        }
    public static void populateFromTaskFile()  {

        File directory = new File(DIRECTORYPATH);
        directory.mkdir();
        try{
            FileInputStream fileInStream = new FileInputStream(DIRECTORYPATH + FILENAME);
            ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
            Object obj = objectInStream.readObject();
            objectInStream.close();
            System.out.println("Retrieved from text file successfully");
            taskList = (ArrayList) obj;
            taskCounter = taskList.size();
        } catch (ClassNotFoundException ce) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_ERROR);
        }
    }
}
