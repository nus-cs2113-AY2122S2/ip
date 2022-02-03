import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        dukeIntro();
        ArrayList<Task> taskArray = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String userInput;
        do {
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                displayList(taskArray);
            } else if (userInput.contains("unmark")) {
                unmarkTask(taskArray, userInput);
                displayUnmarkedTask(taskArray, userInput);
            } else if (userInput.contains("mark")){
                markTask(taskArray, userInput);
                displayMarkedTask(taskArray, userInput);
            } else if (userInput.contains("deadline")){
                addDeadline(taskArray, userInput);
                displayTask(taskArray);
            } else if (userInput.contains("event")){
                addEvent(taskArray, userInput);
                displayTask(taskArray);
            } else {
                addTodo(taskArray, userInput);
                displayTask(taskArray);
            }
        }while (!userInput.equals("bye"));
        dukeExit();

    }

    public static void dukeIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("=============================");
        System.out.println("Ayo my name's Duke!");
        System.out.println("What's up?");
        System.out.println("=============================");
    }

    public static void displayList(ArrayList<Task> taskArray){
        System.out.println("=============================");
        System.out.println("Here's what you got to do! Seize the day!");
        for (int j = 0; j < taskArray.size(); j++) {
            System.out.println(j + 1 + ". " + taskArray.get(j).toString());
        }
        System.out.println("=============================");
    }

    public static void unmarkTask(ArrayList<Task> taskArray, String userInput){
        String[] temp = new String[100];
        temp = userInput.split(" ");
        int num = Integer.parseInt(temp[1]);
        taskArray.get(num-1).markAsUndone();
    }
    public static void markTask(ArrayList<Task> taskArray, String userInput){
        int num = getTaskIndex(taskArray, userInput);
        taskArray.get(num-1).markAsDone();
    }
    public static int getTaskIndex(ArrayList<Task> taskArray, String userInput){
        String[] temp = new String[100];
        temp = userInput.split(" ");
        int num = Integer.parseInt(temp[1]);
        return num;
    }
    public static void displayUnmarkedTask(ArrayList<Task> taskArray, String userInput){
        System.out.println("=============================");
        System.out.println("What are you waiting for? Just do it!");
        System.out.println(taskArray.get(getTaskIndex(taskArray, userInput)-1).toString());
    }
    public static void displayMarkedTask(ArrayList<Task> taskArray, String userInput){
        System.out.println("=============================");
        System.out.println("Go get it king, well done!");
        System.out.println(taskArray.get(getTaskIndex(taskArray, userInput)-1).toString());
    }
    public static void addTask(ArrayList<Task> taskArray, Task t){
        taskArray.add(t);
    }
    public static void dukeExit(){
        System.out.println("=============================");
        System.out.println("Cheers! See you!");
        System.out.println("=============================");
    }
    public static void addTodo(ArrayList<Task> taskArray, String userInput){
        String[] temp = new String[100];
        temp = userInput.split(" ");
        String description = temp[1];
        Todo todo = new Todo(description);
        addTask(taskArray,todo);
    }
    public static void displayTask(ArrayList<Task> taskArray){
        System.out.println("Gotcha. Here's what you have to do");
        System.out.println(taskArray.get(taskArray.size()-1).toString());
        System.out.println("There are " + taskArray.size() + " tasks in the list");
    }
    public static void addDeadline(ArrayList<Task> taskArray, String userInput){
        String[] temp = new String[100];
        temp = userInput.split("/by");
        String description = temp[0].split("deadline")[1].trim();
        String by = temp[1].trim();
        Deadline deadline = new Deadline(description, by);
        addTask(taskArray,deadline);
    }
    public static void addEvent(ArrayList<Task> taskArray, String userInput){
        String[] temp = new String[100];
        temp = userInput.split("/at");
        String description = temp[0].split("event")[1].trim();
        String at = temp[1].trim();
        Event event = new Event(description, at);
        addTask(taskArray,event);
    }

}
