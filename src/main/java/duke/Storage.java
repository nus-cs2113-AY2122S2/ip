package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the saving and loading of tasks
 */
public class Storage {
    private String filePath;
    private String directoryPath;
    private TaskList taskList;

    /**
     * Initialise storage with necessary parameters
     * @param filePath name of file
     * @param directoryPath where the file is going to be stored
     * @param taskList list of tasks in current session of application
     */
    public Storage(String filePath, String directoryPath, TaskList taskList){
        this.filePath = filePath;
        this.directoryPath = directoryPath;
        this.taskList = taskList;
    }

    /**
     * Loads the list of tasks into application from saved file, and creates a new file if no file is found
     */
    // Solution below adapted from https://nus-cs2113-ay2122s2.github.io/website/schedule/week6/topics.html#W6-3
    public void loadTasks(){
        try{
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                readTaskFromFile(s.nextLine());
            }
            System.out.println("Tasks loaded!");
        } catch(FileNotFoundException e){
            System.out.println("No file found!");
            System.out.println("Creating file...");
            createNewFile(filePath);
        }
    }

    /**
     * Reads a line from the saved file and uses it to load the list of tasks
     * @param taskDetails line from saved file which specifies the task to be loaded
     */
    public void readTaskFromFile(String taskDetails){
        String[] task = taskDetails.split("\\|");
        switch (task[0]) {
        case "[T]":
            taskList.addTask(new Todo(task[2], task[1].equals("true")));
            break;
        case "[D]":
            taskList.addTask(new Deadline(task[2], task[1].equals("true")));
            break;
        case "[E]":
            taskList.addTask(new Event(task[2], task[1].equals("true")));
            break;
        default:
            System.out.println("There is nothing to load!");
            break;
        }
    }

    /**
     * Creates a new file for data to be stored into
     * @param filepath storage location of file
     */
    // Solution below adapted from https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
    public void createNewFile(String filepath){
        try {
            File taskFile = new File(filepath);
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            taskFile.createNewFile();
            System.out.println("File created!");
        } catch (IOException e) {
            System.out.println("Oops.. something went wrong with creating the file.");
        }
    }

    /**
     * Translates each task in the current list of tasks into individual strings to be written into data file
     */
    // Solution below adapted from https://nus-cs2113-ay2122s2.github.io/website/schedule/week6/topics.html#W6-3
    public void saveTasks(){
        FileWriter writer;
        String description;
        try{
            writer = new FileWriter(filePath);
            for(int j=0; j<taskList.getTaskCount(); j++){
                description = taskList.getTasks().get(j).getDescription();
                if(taskList.getTasks().get(j).getSymbol().equals("[D]") || taskList.getTasks().get(j).getSymbol().equals("[E]")){
                    description = formatDescDate(description);
                }
                writer.write(taskList.getTasks().get(j).getSymbol() + "|" + (taskList.getTasks().get(j).getIsDone() ? "true" : "false") + "|" + description + "\n");
            }
            writer.close();
        } catch(IOException e){
            System.out.println("Error occurred while saving...");
        }
    }

    /**
     * Formats the task details such that it is easy for reading from the file when loading
     * @param description unformatted description of task
     * @return formatted description of task
     */
    public String formatDescDate(String description){
        String formattedDesc;
        int startOfDate, endOfDate;
        startOfDate = description.lastIndexOf("(");
        endOfDate = description.lastIndexOf(")");
        formattedDesc = description.substring(0,startOfDate) + "/" + description.substring(startOfDate+1, startOfDate+3) + description.substring(startOfDate+4, endOfDate);

        return formattedDesc;
    }
}
