<<<<<<< HEAD
package duke;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
=======
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
>>>>>>> branch-Level-6

public class Duke {

    //Class variables
<<<<<<< HEAD
    private ArrayList <Task> taskList;
=======
    private ArrayList<Task> taskList;
>>>>>>> branch-Level-6


    //Constructor
    public Duke() {
        this.taskList = new ArrayList<Task>();

    }

<<<<<<< HEAD
    Scanner scanner = new Scanner(System.in);

    public static String filePath = "files/TaskList.txt";

    //we will not use this methid in this branch, but it be useful in the next branch for testing purposes
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }



    public static Boolean isMark(String mark){
        if (mark.equals("X")){
            return true;
        }
        else {
            return false;
        }
    }

    public static void createTaskList(String filepathe, ArrayList<Task> taskList) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String ogString = s.nextLine();
                String[] words = ogString.split(" \\| ");
                String taskType = words[0];
                String mark = words[1];
                String description = words[2];
                Boolean isTaskDone = isMark(mark);
                if (taskType.equals("[T]")) {
                    Todo t = new Todo(description);
                    t.isDone = isTaskDone;
                    taskList.add(t);
                } else if (taskType.equals("[E]")) {
                    Event t = new Event(description);
                    t.isDone = isTaskDone;
                    taskList.add(t);
                } else {
                    Deadline t = new Deadline(description);
                    t.isDone = isTaskDone;
                    taskList.add(t);
                }

            }
        } catch(FileNotFoundException e){
            //Dont need to return anything as we hardcode the name of the file
        }
    }

    public static void saveTaskList(ArrayList<Task> taskList) {
        try {
            for (int i = 0; i < taskList.size(); i++){
                Task t = taskList.get(i);
                String description = t.getDescription();
                String typeIcon = t.getTypeIcon();
                String statusIcon = t.getStatusIcon();
                String stringToAdd = typeIcon + " | " + statusIcon + " | " + description;
                if (i == 0){
                    FileWriter fw = new FileWriter(filePath);
                    fw.write(stringToAdd);
                    fw.write("\n");
                    fw.close();
                } else {
                    FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
                    fw.write(stringToAdd);
                    fw.write("\n");
                    fw.close();

                }
            }
        }
        catch(IOException e){
        }
    }

    //Print all available tasks
    public void printTaskList(ArrayList<Task> taskList) {
        int taskCounter = 1;
        for (Task task : taskList) {
            String description = task.getDescription();
            String statusIcon = task.getStatusIcon();
            String typeIcon = task.getTypeIcon();
            System.out.println(taskCounter + ". " + typeIcon + " [" + statusIcon + "] " + description);
            taskCounter += 1;
=======
    //Print all available tasks
    public void printTaskList(ArrayList<Task> taskList){
        int numberOfTasks = 1;
        for (Task task : taskList){
            String description = task.getDescription();
            String statusIcon = task.getStatusIcon();
            String typeIcon = task.getLetter();
            System.out.println(numberOfTasks + ". "  + typeIcon +  " [" +  statusIcon + "] " +  description);
            numberOfTasks += 1;
>>>>>>> branch-Level-6
        }
    }

    //Mark as done
    public Boolean doneCheck(String line, ArrayList<Task> taskList) {
        String[] words = line.split(" ");
        int length_list = words.length;
        if (length_list == 1){
            if (words[0].toUpperCase().equals("MARK")) {
                System.out.println("Please specify (eg: done 2) or just add a new one");
                return true;
            }
        }
        Boolean isMarked= false;
        if (words[0].toUpperCase().equals("MARK")) {
            int intIndex = Integer.parseInt(words[1]) - 1;
            if (words[1] == null){
                return isMarked;
            }
            if ( intIndex < 0 || intIndex > taskList.size()){
                System.out.println("Index is out of bounds");
                return true;
            }
            taskList.get(Integer.parseInt(words[1]) - 1).markIt();
            String statusIcon = taskList.get(Integer.parseInt(words[1]) - 1).getStatusIcon();
            String description = taskList.get(Integer.parseInt(words[1]) - 1).getDescription();
            System.out.println((Integer.parseInt(words[1])) + ". "+ "[" + statusIcon + "]" + " " + description);
            System.out.println("Done! We have marked task " + words[1] + "!");
            isMarked = true;
        }
        return isMarked;
    }

    // Check if the given task is valid
    public Boolean isValidTask (String type){
        String DONE = "done";
        String DEADLINE = "deadline";
        String TODO = "todo";
        String EVENT = "event";
        String LISTE = "list";

        if (type.equals(DONE) || type.equals(TODO) || type.equals(DEADLINE)
<<<<<<< HEAD
                || type.equals(EVENT) || type.equals(LISTE)){
=======
                || type.equals(EVENT) || type.equals(LISTE) ){
>>>>>>> branch-Level-6
            return true;
        }
        return false;
    }
    //return the string in the formatted version

    public String returnStringFormat(String line, String type) {
        if (type.equals("todo") ){
            String[] arrOfStr = line.split(type);
            String toReturn= arrOfStr[1].trim();
            return toReturn;
        } else {
            String preposition;
            String splitter;
            if (type.equals("deadline")){
                preposition = "by: ";
                splitter = "/by";
            }
            else {
                preposition = "at: ";
                splitter = "/at";
            }
            String[] arrOfStr = line.split(splitter);
            String[] toReturn = new String[2];
            String[] todo = arrOfStr[0].split(type);
            toReturn[0] = (todo[1].substring(1, todo[1].length()));       // task

            toReturn[1] = arrOfStr[1].substring(1, arrOfStr[1].length()); // date
            return toReturn[0] + "(" + preposition + toReturn[1] + ")";
        }
    }


    //Add a task
    public void addTask(String line, ArrayList<Task> taskList) {

        String originalString = line;
        while (!originalString.toUpperCase().equals("BYE")) {
            String[] words = originalString.split(" ");
            String eventType = words[0];
            System.out.println(eventType);
            if (!isValidTask(eventType)) {
                System.out.println("Please specify the task to be done clearly (eg: todo, deadline, list, event)");
            }
            else {
                performTasks(taskList, originalString, eventType);

            }

            Scanner scanner = new Scanner(System.in);
            originalString = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("___________________________________________________________________________\n");
    }

    public void performTasks(ArrayList<Task> taskList, String originalString, String eventType) {
        //method for performing the required tasks
        if (doneCheck(originalString, taskList)) {
            System.out.println("___________________________________________________________________________\n");
        } else if (originalString.toUpperCase().equals("LIST")) {
            System.out.println("___________________________________________________________________________\n");
            this.printTaskList(taskList);
            System.out.println("___________________________________________________________________________\n");
        } else {
            try{
            System.out.println("___________________________________________________________________________\n");
            String todoOrDeadlineOrEvent = returnStringFormat(originalString, eventType);
            if (eventType.equals("event") || eventType.equals("deadline")) {
                if (eventType.equals("event")) {
                    Event t = new Event(todoOrDeadlineOrEvent);
                    taskList.add(t);
                } else {
                    Deadline t = new Deadline(todoOrDeadlineOrEvent);
                    taskList.add(t);
                }
            } else {
                Todo e = new Todo(todoOrDeadlineOrEvent);
                taskList.add(e);
            }

            Task t = taskList.get(taskList.size() - 1);
            String description = t.getDescription();
            String statusIcon = t.getStatusIcon();
            String typeIcon = t.getLetter();
            System.out.println("Got it. I have added this task: \n" + todoOrDeadlineOrEvent);
            System.out.println(typeIcon + " [" + statusIcon + "] " + description + "\n");
            System.out.println("Now you have " + taskList.size() + " items in the list \n");
            System.out.println("___________________________________________________________________________\n");
        }
            catch(IndexOutOfBoundsException e) {
                System.out.println("Your inputs can only be of the following forms: \n 1. todo {task description} \n 2. deadline {task description} \\by {dedline eg. 6 PM} \n 3. event {event description} at {event date\\time eg. 6 PM}\");");
                System.out.println("Please try inputting the task again :(");
                System.out.println("___________________________________________________________________________\n");
            }
            }
<<<<<<< HEAD
    }

    //Get things started
    public void startProgramme(){
        String logo = "╮╭┻┻╮╭┻┻╮╭▕╮╲\n" +
                "▕╯┃╭╮┃┃╭╮┃╰▕╯╭▏\n" +
                "▕╭┻┻┻┛┗┻┻┛ ▕ ╰▏\n" +
                "▕╰━━━┓┈┈┈╭╮▕╭╮▏\n" +
                "▕╭╮╰┳┳┳┳╯╰╯▕╰╯▏\n" +
                "▕╰╯┈┗┛┗┛┈╭╮▕╮┈▏" ;
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("___________________________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("Lets get started on planning your tasks ahead mate! Things are looking good.\n");
        System.out.println("___________________________________________________________________________");
        line = scanner.nextLine();
        addTask(line, taskList);
    }

=======
    }

    //Get things started
    public void startProgramme(){
        String logo = "╮╭┻┻╮╭┻┻╮╭▕╮╲\n" +
                "▕╯┃╭╮┃┃╭╮┃╰▕╯╭▏\n" +
                "▕╭┻┻┻┛┗┻┻┛ ▕ ╰▏\n" +
                "▕╰━━━┓┈┈┈╭╮▕╭╮▏\n" +
                "▕╭╮╰┳┳┳┳╯╰╯▕╰╯▏\n" +
                "▕╰╯┈┗┛┗┛┈╭╮▕╮┈▏" ;
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("___________________________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("Lets get started on planning your tasks ahead mate! Things are looking good.\n");
        System.out.println("___________________________________________________________________________");
        line = scanner.nextLine();
        addTask(line, taskList);
    }

>>>>>>> branch-Level-6
    public static void main(String[] args) {
        Duke bobTheFriendlyChatBot = new Duke();
        bobTheFriendlyChatBot.startProgramme();
    }
}