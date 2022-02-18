package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;
    public static final String FILE_PATH = "data/tasks.txt";
    public static final String DIRECTORY_PATH = "data";
    public static void main(String[] args) {
        loadTasks();
        welcomeMessage();
        int taskStartIndex, taskDeleteIndex;

        String userInput, message;
        Scanner in = new Scanner(System.in);
        do{
            userInput = in.nextLine();

            if(userInput.startsWith("mark")){
                taskStartIndex = Integer.parseInt(userInput.substring(5));
                try {
                    tasks.get(taskStartIndex-1).markAsDone();
                    System.out.println(tasks.get(taskStartIndex-1).printTask());
                    saveTasks();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist!");
                } catch (NumberFormatException e){
                    System.out.println("Please use numbers only to specify task!");
                }
            }else if(userInput.startsWith("unmark")){
                taskStartIndex = Integer.parseInt(userInput.substring(7));
                try {
                    tasks.get(taskStartIndex-1).markAsUndone();
                    System.out.println(tasks.get(taskStartIndex-1).printTask());
                    saveTasks();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist!");
                } catch (NumberFormatException e){
                    System.out.println("Please use numbers only to specify task!");
                }
            }else if(userInput.startsWith("delete")){
                taskDeleteIndex = Integer.parseInt(userInput.substring(7));
                try {
                    message = tasks.get(taskDeleteIndex-1).printTask();
                    deleteTask(taskDeleteIndex-1);
                    taskCount--;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(message);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    saveTasks();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist!");
                } catch (NumberFormatException e){
                    System.out.println("Please use numbers only to specify task!");
                }
            } else{
                switch(userInput){
                case "bye":
                    System.out.println("================================================");
                    break;
                case "list":
                    listTasks(taskCount);
                    break;
                default:
                    System.out.println("================================================");
                    try {
                        if(addTask(userInput) != null) {
                            tasks.add(addTask(userInput));
                            System.out.println(tasks.get(taskCount).printTask());
                            taskCount++;
                            System.out.println("Now you have " + (taskCount) + " tasks in the list.");
                            saveTasks();
                        }else{
                            System.out.println("Please try again!");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Please try again!");
                    }
                    System.out.println("================================================");
                    break;
                }
            }
        }while(!userInput.equals("bye"));

        saveTasks();

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("================================================");
    }

    public static Task addTask(String userInput){
        if (userInput.startsWith("todo")) {
            try{
                checkEmptyDescription(userInput.substring(5));
                return new Todo(userInput.substring(5));
            }catch(EmptyDescriptionException | StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (userInput.startsWith("deadline")) {
            try{
                checkEmptyDescription(userInput.substring(5));
                return new Deadline(userInput.substring(9));
            }catch(EmptyDescriptionException | StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            }
        }else if (userInput.startsWith("event")){
            try{
                checkEmptyDescription(userInput.substring(5));
                return new Event(userInput.substring(6));
            }catch(EmptyDescriptionException | StringIndexOutOfBoundsException e){
                System.out.println("OOPS!!! The description of an event cannot be empty.");
            }
        }else{
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return null;
    }

    public static void deleteTask(int index){
        tasks.remove(index);
    }

    public static void checkEmptyDescription(String description) throws EmptyDescriptionException{
        if(description.isBlank()){
            throw new EmptyDescriptionException();
        }
    }

    public static void listTasks(int numOfTasks){
        System.out.println("================================================");
        System.out.println("Here are the tasks in your list:");
        for(int j=0; j<numOfTasks; j++){
            System.out.println((j+1) + "." + tasks.get(j).printTask());
        }
        System.out.println("================================================");
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
