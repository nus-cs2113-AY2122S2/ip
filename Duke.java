import java.util.Scanner;

public class Duke {
    // static variables
    private static Task[] taskList = new Task[100];
    private static int taskCounter = 0;

    static String HORIZONTAL_LINE = "------------------------------------------------------------";

    // class methods
    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String command = line.split(" ")[0];
        while (!command.matches("bye")) {
            switch (command) {
            case "list":
                listTasks();
                break;
            case "mark":
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                markTask(taskList[index]);
                break;
            case "unmark":
                index = Integer.parseInt(line.split(" ")[1]) - 1;
                unmarkTask(taskList[index]);
                break;
            default:
                Task task = new Task(line);
                addTask(task);
                break;
            }
            line = input.nextLine();
            command = line.split(" ")[0];
        }
        exit(); 
    }

    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        
    }

    public static void echo(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(command);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void addTask(Task task) {
        taskList[taskCounter] = task;
        taskCounter += 1;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("added: " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; ++i) {
            Task task = taskList[i];
            System.out.println(i + 1 + ".[" + task.getStatusIcon() + "] " + task.name);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmarkTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }
}