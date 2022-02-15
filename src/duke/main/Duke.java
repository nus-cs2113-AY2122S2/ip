package duke.main;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.Event;
import duke.task.Todo;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke{
    public static final String LINEBREAK = "____________________________________________________________";
    public static final String FILEPATH = "data/duke.txt";
    public static final String DIRPATH = "data";
    public static ArrayList<Task> list = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);
    public static int taskCount = 0;
    public static void saveTasks(){
        try{
            File dirPath = new File(DIRPATH);
            if (!dirPath.exists()){
                dirPath.mkdirs();
            }
            FileWriter file = new FileWriter(FILEPATH, false);
            String data = "";
            for (int i = 0; i < taskCount; i++){
                data += list.get(i).getTaskDetails();
            }
            file.write(data);
            file.close();
        }catch (IOException e){
            System.out.println(LINEBREAK);
            System.out.println(" Error: " + e.getMessage());
            System.out.println(LINEBREAK);
            return;
        }
    }
    public static void listTasks(){
        System.out.println(LINEBREAK);
        for (int j = 0; j < taskCount; j++){
            System.out.println(Integer.toString(j + 1) + list.get(j));
        }
        System.out.println(LINEBREAK);
    }
    public static void deleteTask(String line){
        int index;
        try{
            index = Integer.parseInt(line.split(" ")[1]) - 1;
            if (index >= taskCount || index < 0){
                throw new DukeException("Error: Task Index is out of bounds.");
            }
        }catch (NumberFormatException e){
            System.out.println(LINEBREAK);
            System.out.println(" Error: Invalid index (Not an integer).");
            System.out.println(LINEBREAK);
            return;
        }catch (DukeException e){
            System.out.println(LINEBREAK);
            System.out.println(e.getMessage());
            System.out.println(LINEBREAK);
            return;
        }
        System.out.println(LINEBREAK);
        System.out.println(" I have removed this task:");
        System.out.println(list.get(index));
        taskCount--;
        System.out.println(" Total number of tasks now: " + taskCount);
        System.out.println(LINEBREAK);
        list.remove((index));
        saveTasks();
    }
    public static void updateMarkTask(String line, boolean mark){
        int index;
        try{
            index = Integer.parseInt(line.split(" ")[1]) - 1;
            if (index >= taskCount || index < 0){
                throw new DukeException("Error: Task Index is out of bounds.");
            }
        }catch (NumberFormatException e){
            System.out.println(LINEBREAK);
            System.out.println(" Error: Invalid index (Not an integer).");
            System.out.println(LINEBREAK);
            return;
        }catch (DukeException e){
            System.out.println(LINEBREAK);
            System.out.println(e.getMessage());
            System.out.println(LINEBREAK);
            return;
        }
        System.out.println(LINEBREAK);
        if (mark){
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("  [X] " + list.get(index).getName());
        }else{
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + list.get(index).getName());
        }
        System.out.println(LINEBREAK);
        list.get(index).setMarked(mark);
        saveTasks();
    }
    public static void addNewTask(String line){
        String taskType = line.split(" ")[0];
        if (line.length() > taskType.length()){
            line = line.substring(taskType.length() + 1);
        }else{
            line = "";
        }
        try {
            switch (taskType) {
            case "todo":
                if (line.equals("")){
                    throw new DukeException("Error: Argument of todo should not be empty.");
                }
                list.add(new Todo(line, false));
                break;
            case "deadline":
                String[] taskNameAndDeadline = line.split(" /by ");
                if (taskNameAndDeadline.length < 2){
                    throw new DukeException("Error: A Deadline Task should have the deadline.");
                }
                list.add(new Deadline(taskNameAndDeadline[0], false, taskNameAndDeadline[1]));
                break;
            case "event":
                String[] taskNameAndTiming = line.split(" /at ");
                if (taskNameAndTiming.length < 2){
                    throw new DukeException("Error: An Event Task should have the event timing.");
                }
                list.add(new Event(taskNameAndTiming[0], false, taskNameAndTiming[1]));
                break;
            default:
                throw new DukeException("Error: Command not recognised.");
            }
        }catch (DukeException e){
            System.out.println(LINEBREAK);
            System.out.println(e.getMessage());
            System.out.println(LINEBREAK);
            return;
        }

        System.out.println(LINEBREAK);
        System.out.println(" added: " + list.get(taskCount));
        System.out.println(" Total number of tasks now: " + (taskCount + 1));
        System.out.println(LINEBREAK);
        taskCount++;
        saveTasks();
    }
    public static void waitForInput(){
        while (true){
            String line = in.nextLine();
            if (line.equals("bye")){
                break;
            }
            if (line.equals("list")){
                listTasks();
            }else if (line.split(" ")[0].equals("mark")){
                updateMarkTask(line, true);
            }else if (line.split(" ")[0].equals("unmark")){
                updateMarkTask(line, false);
            }else if (line.split(" ")[0].equals("delete")){
                deleteTask(line);
            }else{
                addNewTask(line);
            }

        }
    }
    public static void main(String[] args){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINEBREAK);
        System.out.println(" Hello! I'm Duke\n What can I do for you?");
        System.out.println(LINEBREAK);

        waitForInput();
        System.out.println(LINEBREAK);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINEBREAK);
    }
}
