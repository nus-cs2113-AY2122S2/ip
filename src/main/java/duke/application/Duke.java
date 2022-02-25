package duke.application;

//java imports
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;


//entity classes imports
import duke.entity.Deadline;
import duke.entity.Event;
import duke.entity.Task;
import duke.entity.Todo;

//exception classes imports
import duke.exception.DukeException;
import duke.exception.IllegalTodoException;
import duke.exception.IllegalEventException;
import duke.exception.IllegalDeadlineException;


public class Duke {
    public static void main(String[] args) {
        dukeIntro();
        File f = loadFile();
        ArrayList<Task> taskArray = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String userInput = null;
        do {
            try {
                userInput = sc.nextLine();
                checkInputValidity(userInput);
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    displayList(taskArray);
                } else if (userInput.contains("unmark")) {
                    unmarkTask(taskArray, userInput);
                    displayUnmarkedTask(taskArray, userInput);
                } else if (userInput.contains("mark")) {
                    markTask(taskArray, userInput);
                    displayMarkedTask(taskArray, userInput);
                } else if (userInput.contains("deadline")) {
                    try {
                        addDeadline(taskArray, userInput);
                        displayTask(taskArray);
                    }
                    catch (IllegalDeadlineException e){
                        displayDeadLineErrorMessage();
                    }
                } else if (userInput.contains("event")) {
                    try {
                        addEvent(taskArray, userInput);
                        displayTask(taskArray);
                    }
                    catch (IllegalEventException e){
                        displayEventErrorMessage();
                    }
                } else if (userInput.contains("todo")){
                    try {
                        addTodo(taskArray, userInput);
                        displayTask(taskArray);
                    }
                    catch (IllegalTodoException e){
                        displayTodoErrorMessage();
                    }
                } else if (userInput.contains("delete")){
                    displayDeletedTask(taskArray, userInput);
                } else if (userInput.contains("find")){
                    displayFoundTasks(taskArray, userInput);
                }
                try {
                    saveFile(f, taskArray);
                }catch (IOException e){
                    displayFileErrorMessage();
                }
            }
            catch (DukeException e){
                displayMainErrorMessage();
            }
        }while (!userInput.equals("bye"));
        dukeExit();

    }

    //main duke methods

    public static void dukeIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";
        System.out.println("Hey! What's up\n" + logo);
        System.out.println("=============================");
        System.out.println("Ayo my name's duke!");
        System.out.println("What's up?");
        System.out.println("=============================");
    }

    public static void checkInputValidity(String userInput) throws DukeException {
        String[] validInputs = {"list","unmark","mark","deadline","event","todo","delete","find"};
        if (userInput.length() == 0){
            throw new DukeException();
        }
        boolean isValidCommand = Arrays.stream(validInputs).anyMatch(userInput::contains);
        if (!isValidCommand){
            throw new DukeException();
        }
    }

    public static void displayMainErrorMessage(){
        System.out.println("Hey man you got to give me something to work with!");
        System.out.println("Start your command with todo, event, deadline, mark, unmark, list, delete or bye! Bye Bye!");
    }

    public static void dukeExit(){
        System.out.println("=============================");
        System.out.println("Cheers! See you!");
        System.out.println("=============================");
    }

    //file methods

    public static File loadFile(){
        return new File("src/main/java/duke.txt");
    }

    public static void saveFile(File f, ArrayList<Task> taskArray) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task t : taskArray) {
            fw.write(t.toString() + "\n");
        }
        fw.close();
    }

    public static void displayFileErrorMessage(){
        System.out.println("Oopsies! Something went wrong while saving");
    }

    //list methods

    public static void displayList(ArrayList<Task> taskArray){
        System.out.println("=============================");
        System.out.println("Here's what you got to do! Seize the day!");
        for (int j = 0; j < taskArray.size(); j++) {
            System.out.println(j + 1 + ". " + taskArray.get(j).toString());
        }
        System.out.println("=============================");
    }

    //task methods

    public static void addTask(ArrayList<Task> taskArray, Task t){
        taskArray.add(t);
    }

    public static void displayTask(ArrayList<Task> taskArray){
        System.out.println("Gotcha. Here's what you have to do");
        System.out.println(taskArray.get(taskArray.size()-1).toString());
        System.out.println("There are " + taskArray.size() + " tasks in the list");
    }

    public static int getTaskIndex(String userInput){
        String[] temp = new String[100];
        temp = userInput.split(" ");
        int num = Integer.parseInt(temp[1]);
        return num;
    }

    public static String findTask(String userInput){
        String temp = userInput.split(" ")[1];
        return temp;
    }

    public static void displayFoundTasks(ArrayList<Task> taskArray, String userInput){
        String temp = findTask(userInput);
        int count = 0;
        System.out.println("Here are the matching tasks found!");
        for (Task t : taskArray){
            if (t.getDescription().contains(temp)){
                System.out.println(++count + "." + t.toString());
            }
        }
    }

    //delete methods

    public static Task deleteTask(ArrayList<Task> taskArray, String userInput){
        int num = getTaskIndex(userInput);
        Task t = taskArray.get(num-1);
        taskArray.remove(num-1);
        return t;
    }

    public static void displayDeletedTask(ArrayList<Task> taskArray, String userInput){
        System.out.println("=============================");
        System.out.println("Hey, I've removed this task for you. Focus on the others ahead!");
        System.out.println(deleteTask(taskArray, userInput).toString());
        System.out.println("There are still " + taskArray.size() + " tasks in the list.");
    }

    //unmark methods

    public static void unmarkTask(ArrayList<Task> taskArray, String userInput){
        int num = getTaskIndex(userInput);
        taskArray.get(num-1).markAsUndone();
    }

    public static void displayUnmarkedTask(ArrayList<Task> taskArray, String userInput){
        System.out.println("=============================");
        System.out.println("What are you waiting for? Just do it!");
        System.out.println(taskArray.get(getTaskIndex(userInput)-1).toString());
    }

    //mark methods

    public static void markTask(ArrayList<Task> taskArray, String userInput){
        int num = getTaskIndex(userInput);
        taskArray.get(num-1).markAsDone();
    }

    public static void displayMarkedTask(ArrayList<Task> taskArray, String userInput){
        System.out.println("=============================");
        System.out.println("Go get it king, well done!");
        System.out.println(taskArray.get(getTaskIndex(userInput)-1).toString());
    }

    //deadline methods

    public static void addDeadline(ArrayList<Task> taskArray, String userInput) throws IllegalDeadlineException {
        if (userInput.length() <= 8 ||!userInput.contains("/by")){
            throw new IllegalDeadlineException();
        }
        String[] temp = new String[100];
        temp = userInput.split("/by");
        if (temp.length <= 1){
            throw new IllegalDeadlineException();
        }
        String description = temp[0].split("deadline")[1].trim();
        String by = temp[1].trim();
        if (description.length() == 0){
            throw new IllegalDeadlineException();
        }
        Deadline deadline = new Deadline(description, by);
        addTask(taskArray,deadline);
    }

    public static void displayDeadLineErrorMessage(){
        System.out.println("Hey! What do you have to do before your deadline? Enter the valid task and date");
    }

    //event methods

    public static void addEvent(ArrayList<Task> taskArray, String userInput) throws IllegalEventException {
        if (userInput.length() <= 5 ||!userInput.contains("/at")){
            throw new IllegalEventException();
        }
        String[] temp = new String[100];
        temp = userInput.split("/at");
        if (temp.length <= 1){
            throw new IllegalEventException();
        }
        String description = temp[0].split("event")[1].trim();
        String at = temp[1].trim();
        if (description.length() == 0){
            throw new IllegalEventException();
        }
        Event event = new Event(description, at);
        addTask(taskArray,event);
    }

    public static void displayEventErrorMessage(){
        System.out.println("Hey! What event do you have coming up? Enter the valid task and date");
    }

    //todo methods

    public static void addTodo(ArrayList<Task> taskArray, String userInput) throws IllegalTodoException {
        if (userInput.length() <= 4){
            throw new IllegalTodoException();
        }
        String[] temp = new String[100];
        temp = userInput.split("todo");
        String description = temp[1].trim();
        Todo todo = new Todo(description);
        addTask(taskArray,todo);
    }

    public static void displayTodoErrorMessage(){
        System.out.println("Hey! What do you have to do? Enter a valid task");
    }

}