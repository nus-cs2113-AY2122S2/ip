package duke;

import command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static ArrayList<Task> tasks = taskList.getTasks();
    private static int taskCount = 0;
    public static final String FILE_PATH = "data/tasks.txt";
    public static final String DIRECTORY_PATH = "data";
    public static final Ui ui = new Ui();
    public static void main(String[] args) {
//        loadTasks();
        ui.showWelcome();

        String userInput, message;
        Scanner in = new Scanner(System.in);
        do{
            userInput = in.nextLine();
            Command command = new Parser().parseCommand(userInput, taskList);
            command.execute();
//            saveTasks();
        }while(!userInput.equals("bye"));

        ui.showGoodbye();
    }

    public static void loadTasks(){
        try{
            File f = new File(FILE_PATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                readTaskFromFile(s.nextLine());
            }
            System.out.println("Tasks loaded!");
        } catch(FileNotFoundException e){
            System.out.println("No file found!");
            System.out.println("Creating file...");
            createNewFile(FILE_PATH);
        }
    }

    public static void createNewFile(String filepath){
        try {
            File taskFile = new File(filepath);
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdir();
            }
            taskFile.createNewFile();
            System.out.println("File created!");
        } catch (IOException e) {
            System.out.println("Oops.. something went wrong with creating the file.");
        }
    }

    public static void readTaskFromFile(String taskDetails){
        String[] task = taskDetails.split("\\|");
        switch (task[0]) {
        case "[T]":
            tasks.add(new Todo(task[2], task[1].equals("true")));
            taskCount++;
            break;
        case "[D]":
            tasks.add(new Deadline(task[2], task[1].equals("true")));
            taskCount++;
            break;
        case "[E]":
            tasks.add(new Event(task[2], task[1].equals("true")));
            taskCount++;
            break;
        default:
            System.out.println("There is nothing to load!");
            break;
        }
    }

    public static void saveTasks(){
        FileWriter writer;
        String description;
        try{
            writer = new FileWriter(FILE_PATH);
            for(int j=0; j<taskCount; j++){
                description = tasks.get(j).getDescription();
                if(tasks.get(j).getSymbol().equals("[D]") || tasks.get(j).getSymbol().equals("[E]")){
                    description = formatDescDate(description);
                }
                writer.write(tasks.get(j).getSymbol() + "|" + (tasks.get(j).getIsDone() ? "true" : "false") + "|" + description + "\n");
            }
            writer.close();
        } catch(IOException e){
            System.out.println("Error occurred while saving...");
        }
    }

    public static String formatDescDate(String description){
        String formattedDesc;
        int startOfDate, endOfDate;
        startOfDate = description.lastIndexOf("(");
        endOfDate = description.lastIndexOf(")");
        formattedDesc = description.substring(0,startOfDate) + "/" + description.substring(startOfDate+1, startOfDate+3) + description.substring(startOfDate+4, endOfDate);

        return formattedDesc;
    }
}
