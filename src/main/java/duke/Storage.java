package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String directoryPath;
    private TaskList taskList;

    public Storage(String filePath, String directoryPath, TaskList taskList){
        this.filePath = filePath;
        this.directoryPath = directoryPath;
        this.taskList = taskList;
    }

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

    public String formatDescDate(String description){
        String formattedDesc;
        int startOfDate, endOfDate;
        startOfDate = description.lastIndexOf("(");
        endOfDate = description.lastIndexOf(")");
        formattedDesc = description.substring(0,startOfDate) + "/" + description.substring(startOfDate+1, startOfDate+3) + description.substring(startOfDate+4, endOfDate);

        return formattedDesc;
    }
}
