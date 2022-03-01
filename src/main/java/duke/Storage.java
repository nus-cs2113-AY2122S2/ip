package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.TaskList.taskList;
import static task.Deadline.getDeadlineDate;


/**
 * This class is used to store and retrieve to file
 *
 */
public class Storage {
    protected String filePath;
    public static final String DIRECTORYPATH = "data/";
    public static final String FILENAME = "duke1.txt";
    public static final String TASK_DETAILS_SEPERATOR = " | ";
    public static final String NEW_LINE = "\n";

    public Storage(){
        filePath = DIRECTORYPATH + FILENAME;
    }


    //get home path of user
    private static final String HOME_PATH = System.getProperty("user.dir");

    public static void writeToFile(ArrayList<Task> taskList){
        try{
            FileWriter fw = new FileWriter(DIRECTORYPATH + FILENAME);
            for (Task taskInList : taskList){
                if (taskInList instanceof Event){
                    fw.write( taskInList.getType() + TASK_DETAILS_SEPERATOR + taskInList.getStatusIcon() + TASK_DETAILS_SEPERATOR + taskInList.getTaskName() + TASK_DETAILS_SEPERATOR + ((Event) taskInList).getAt() + NEW_LINE);
                }
                else if (taskInList instanceof Deadline){
                    fw.write( taskInList.getType() + TASK_DETAILS_SEPERATOR + taskInList.getStatusIcon() + TASK_DETAILS_SEPERATOR + taskInList.getTaskName() + TASK_DETAILS_SEPERATOR + "/by " + ((Deadline) taskInList).getBy() + NEW_LINE);
                }
                else{
                    fw.write( taskInList.getType() + TASK_DETAILS_SEPERATOR + taskInList.getStatusIcon() + TASK_DETAILS_SEPERATOR + taskInList.getTaskName() + TASK_DETAILS_SEPERATOR + NEW_LINE);
                }

            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processTasks(String task){
        String[] taskArray = task.split("\\|");
        switch (taskArray[0].trim()) {
        case "T":
            Todo newTask = new Todo(taskArray[2]);
            newTask.setDone(newTask.getStatus(taskArray[1]));
            taskList.add(newTask);
            break;
        case "D":
            Deadline newDeadline = new Deadline(taskArray[2],getDeadlineDate(taskArray[3]));
            newDeadline.setDone(newDeadline.getStatus(taskArray[1]));
            taskList.add(newDeadline);
            break;
        case "E":
            Event newEvent = new Event(taskArray[2],taskArray[3]);
            newEvent.setDone(newEvent.getStatus(taskArray[1]));
            taskList.add(newEvent);
            break;
        }

    }

    public ArrayList<Task> readFromFile() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            processTasks(scanner.nextLine());
        }
        return taskList;
    }

}
