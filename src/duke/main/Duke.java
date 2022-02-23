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

public class Duke{
    public static final String LINEBREAK = "____________________________________________________________";
    public static final String FILEPATH = "data/duke.txt";
    public static final String DIRPATH = "data";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    public static ArrayList<Task> list = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);
    public static int taskCount = 0;
    public static boolean isLoaded = false;

    public static void printExceptionMessage(String message){
        System.out.println(LINEBREAK);
        System.out.println(" Error: " + message);
        System.out.println(LINEBREAK);
    }
    public static void loadSavedTasks(){
        try{
            File dirPath = new File(DIRPATH);
            if (!dirPath.exists()){
                dirPath.mkdirs();
            }
            File file = new File(FILEPATH);
            Scanner txtScanner = new Scanner(file);
            while (txtScanner.hasNext()) {
                isLoaded = true;
                String line = txtScanner.nextLine();
                String[] fields = line.split(" \\| ");
                String taskType = fields[0];
                String marked = fields[1];
                String taskName = fields[2];
                String timeField = "";
                if (fields.length == 4){
                    timeField = fields[3];
                }
                switch(taskType){
                case TODO:
                    list.add(new Todo(taskName, marked.equals("1")));
                    break;
                case DEADLINE:
                    list.add(new Deadline(taskName, marked.equals("1"), timeField));
                    break;
                case EVENT:
                    list.add(new Event(taskName, marked.equals("1"), timeField));
                    break;
                default:
                    throw new DukeException("Invalid file format.");
                }
                taskCount++;
            }
        }catch (IOException e){
            printExceptionMessage(e.getMessage());
            return;
        }catch (DukeException e){
            printExceptionMessage(e.getMessage());
            return;
        }
    }
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
            printExceptionMessage(e.getMessage());
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
                throw new DukeException("Task Index is out of bounds.");
            }
        }catch (NumberFormatException e){
            printExceptionMessage("Invalid index (Not an integer).");
            return;
        }catch (DukeException e){
            printExceptionMessage(e.getMessage());
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
                throw new DukeException("Task Index is out of bounds.");
            }
        }catch (NumberFormatException e){
            printExceptionMessage(e.getMessage());
            return;
        }catch (DukeException e){
            printExceptionMessage(e.getMessage());
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
    public static void addTodo(String line) throws DukeException {
        if (line.equals("")){
            throw new DukeException("Argument of todo should not be empty.");
        }
        list.add(new Todo(line, false));
    }

    public static void addDeadline(String line) throws DukeException {
        String[] taskNameAndDeadline = line.split(" /by ");
        if (taskNameAndDeadline.length < 2){
            throw new DukeException("A Deadline Task should have the deadline.");
        }
        list.add(new Deadline(taskNameAndDeadline[0], false, taskNameAndDeadline[1]));
    }

    public static void addEvent(String line) throws DukeException {
        String[] taskNameAndTiming = line.split(" /at ");
        if (taskNameAndTiming.length < 2){
            throw new DukeException("An Event Task should have the event timing.");
        }
        list.add(new Event(taskNameAndTiming[0], false, taskNameAndTiming[1]));
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
            case TODO:
                addTodo(line);
                break;
            case DEADLINE:
                addDeadline(line);
                break;
            case EVENT:
                addEvent(line);
                break;
            default:
                throw new DukeException("Command not recognised.");
            }
        }catch (DukeException e){
            printExceptionMessage(e.getMessage());
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
            boolean isMarkCommand = line.split(" ")[0].equals("mark");
            boolean isUnmarkCommand = line.split(" ")[0].equals("unmark");
            boolean isDeleteCommand = line.split(" ")[0].equals("delete");
            if (line.equals("bye")){
                break;
            }else if (line.equals("list")){
                listTasks();
            }else if (isMarkCommand){
                updateMarkTask(line, true);
            }else if (isUnmarkCommand){
                updateMarkTask(line, false);
            }else if (isDeleteCommand){
                deleteTask(line);
            }else{
                addNewTask(line);
            }

        }
    }
    public static void main(String[] args){
        System.out.println(" Hello! I'm Duke\n What can I do for you?");
        System.out.println(LINEBREAK);
        loadSavedTasks();
        if (isLoaded){
            System.out.println(" Loaded Save File.");
            System.out.println(LINEBREAK);
        }
        waitForInput();
        System.out.println(LINEBREAK);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINEBREAK);
    }
}
