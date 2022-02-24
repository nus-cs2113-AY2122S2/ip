package duke;

import task.Task;

import java.io.*;
import java.util.ArrayList;

import static duke.TaskList.taskList;
import static duke.TaskList.taskCounter;
import static errors.Errors.ErrorTypes.FILE_NOT_FOUND;
import static errors.Errors.ErrorTypes.IO_ERROR;



public class Storage {
    protected String filePath;
    public static final String DIRECTORYPATH = "data/";
    public static final String FILENAME = "/duke1.txt";
    public static final String TASK_DETAILS_SEPERATOR = " | ";

    public Storage(){
        filePath = DIRECTORYPATH + FILENAME;
    }


    //get home path of user
    private static final String HOME_PATH = System.getProperty("user.dir");

        public static void writeToFile(ArrayList<Task> taskList){
            try{
                FileWriter fw = new FileWriter(DIRECTORYPATH + FILENAME);
                for (Task taskInList : taskList){
                    fw.write( taskInList.getType() + TASK_DETAILS_SEPERATOR + taskInList.getStatusIcon() + TASK_DETAILS_SEPERATOR + taskInList.getTaskName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public static void saveToFile(ArrayList<Task> taskArrayList){
            // check if file exists, if not create it
            writeToFile(taskArrayList);
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
    public static ArrayList<Task> populateFromTaskFile()  {

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
            return taskList;
        } catch (ClassNotFoundException ce) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_ERROR);
        }
        return new ArrayList<Task>();
    }
}
