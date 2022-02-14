import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    static List<Task> tasks = new ArrayList<>();
    static int numTasks = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm KaiKai.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();


        while (!str.equals("bye")) {
            switch (str) {
            case "list":
                printTasks();
                break;
            case "mark":
                markTask();
                break;
            case "unmark":
                unmarkTask();

                break;
            case "add":
                System.out.println("Okie, what type of task is this?");
                System.out.println("1.Todo");
                System.out.println("2.Deadline");
                System.out.println("3.Event");
                str = sc.nextLine();
                switch (str) {
                case "1":
                    addTodo();
                    break;
                case "2":
                    addDeadline();
                    break;
                case "3":
                    addEvent();
                    break;
                default:
                    System.out.println("Uh oh, please enter a valid number!");
                }
                System.out.println("______________________________________");
                break;
            case "delete":
                deleteTask();
                break;
            default:
                System.out.println("Sorry, I don't recognise that command. Please try again!");
                System.out.println("______________________________________");
                break;
            }
            str = sc.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");
    }

    public static void printTasks() {
        if (numTasks == 0){
            System.out.println("Looks like you don't have any tasks for now!");
            System.out.println("______________________________________");
            return;
        }
        for (int i = 0; i < numTasks; i++) {
            System.out.print((i + 1) + ". ");
            tasks.get(i).printTask();
        }
    }

    public static void addTodo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Okie, what should I call the task?");
        String str = sc.nextLine();
        if (!str.isEmpty()){
            Todo t = new Todo(str);
            tasks.add(t);
            numTasks++;
            System.out.println("Added!");
        }
        else{
            System.out.println("Oops! The description of a Todo cannot be empty.");
        }
    }

    public static void addDeadline() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Okie, what should I call the task?");
        String str = sc.nextLine();
        if (str.isEmpty()){
            System.out.println("Oops! The description of a Deadline cannot be empty.");
            return;
        }
        System.out.println("Okie, when is this due by?");
        String by = sc.nextLine();
        if (by.isEmpty()){
            System.out.println("Oops! The due date of a Deadline cannot be empty.");
            return;
        }
        Deadline d = new Deadline(str, by);
        tasks.add(d);
        numTasks++;
        System.out.println("Added!");
    }

    public static void addEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Okie, what should I call the task?");
        String str = sc.nextLine();
        if (str.isEmpty()){
            System.out.println("Oops! The description of a Deadline cannot be empty.");
            return;
        }
        System.out.println("Okie, when does the event start?");
        String start = sc.nextLine();
        if (start.isEmpty()){
            System.out.println("Oops! The start date of a Deadline cannot be empty.");
            return;
        }
        System.out.println("Okie, when does the event end?");
        String by = sc.nextLine();
        if (by.isEmpty()){
            System.out.println("Oops! The due date of a Deadline cannot be empty.");
            return;
        }
        Event e = new Event(str, by, start);
        tasks.add(e);
        numTasks++;
        System.out.println("Added!");
    }

    public static void markTask(){
        Scanner sc = new Scanner(System.in);
        printTasks();
        System.out.println("Which task would you like to mark as completed?");
        String taskNum = sc.nextLine();
        if (!isInt(taskNum)){
            System.out.println("Uh oh! Please enter a valid input!");
            return;
        }
        int num = Integer.parseInt(taskNum);
        if (num > numTasks || num <= 0) {
            System.out.println("Uh oh! It seems like that task doesn't exist!");
        } else {
            tasks.get(num - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            tasks.get(num - 1).printTask();
        }
    }
    public static void unmarkTask(){
        Scanner sc = new Scanner(System.in);
        printTasks();
        System.out.println("Which task would you like to mark as incomplete?");
        String taskNum = sc.nextLine();
        if (!isInt(taskNum)){
            System.out.println("Uh oh! Please enter a valid input!");
            return;
        }
        int num = Integer.parseInt(taskNum);
        if (num > numTasks || num <= 0) {
            System.out.println("Uh oh! It seems like that task doesn't exist!");
        } else {
            tasks.get(num - 1).setDone(false);
            System.out.println("Okie, I've marked this task as not done yet:");
            tasks.get(num - 1).printTask();
        }

    }

    public static void deleteTask(){
        Scanner sc = new Scanner(System.in);
        printTasks();
        System.out.println("Which task would you like to remove?");
        String taskNum = sc.nextLine();
        if (!isInt(taskNum)){
            System.out.println("Uh oh! Please enter a valid input!");
            return;
        }
        int num = Integer.parseInt(taskNum);
        if (num > numTasks || num <= 0) {
            System.out.println("Uh oh! It seems like that task doesn't exist!");
        } else {
            System.out.println("Okay! I have removed this task: ");
            tasks.get(num - 1).printTask();
            tasks.remove(num-1);
            numTasks--;
        }
    }

    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }


}
