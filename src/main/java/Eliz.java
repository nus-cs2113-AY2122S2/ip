import java.util.Arrays;
import java.util.Scanner;

public class Eliz {
    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            int numToPrint = i + 1;
            System.out.println(numToPrint + ".[" + tasks[i].getTaskType() + "]" + "["+ tasks[i].getStatusIcon() + "] "
                    + tasks[i].description);
        }
    }

    public static void markATask(String line, Task[] tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        tasks[taskNumInt - 1].setAsDone();
        System.out.println("[" + tasks[taskNumInt - 1].getTaskType() + "]" + "[X] "
                + tasks[taskNumInt - 1].description);
    }

    public static void unmarkATask(String line, Task[] tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        tasks[taskNumInt - 1].setAsNotDone();
        System.out.println("[" + tasks[taskNumInt - 1].getTaskType() + "]" + "[ ] "
                + tasks[taskNumInt - 1].description);
    }

    public static Task createTask(String line) {
        Task t;
        String[] splitTwoSections = line.split(" ",2); //0: task type, 1: rest of the words
        String taskType = splitTwoSections[0];
        switch (taskType) {
        case "todo":
            t = createTodo(taskType, splitTwoSections[1]);
            break;
        case "deadline":
            t = createDeadline(taskType, splitTwoSections[1]);
            break;
        case "event":
            t = createEvent(taskType, splitTwoSections[1]);
            break;
        default:
            return null;
        }
        return t;
    }

    public static Task createTodo(String taskType, String line) {
        Todo newTodo = new Todo(line);
        return newTodo;
    }

    public static Task createDeadline(String taskType, String line) {
        String newDescription = line;
        if (line.contains("/")) {
            String[] splitTwoSections = line.split("/", 2);
            newDescription = splitTwoSections[0] + "(" + splitTwoSections[1] + ")";
        }
        Deadline newDeadline = new Deadline(newDescription);
        return newDeadline;
    }

    public static Task createEvent(String taskType, String line) {
        String newDescription = line;
        if (line.contains("/")) {
            String[] splitTwoSections = line.split("/", 2);
            newDescription = splitTwoSections[0] + "(" + splitTwoSections[1] + ")";
        }
        Event newEvent = new Event(newDescription);
        return newEvent;
    }

    public static void botIntroduction() {
        String logo = " ____    __       __     ______ \n"
                + "|  __|  |  |     |  |   |___  /\n"
                + "| |__   |  |     |  |      / /  \n"
                + "| |__|  |  |     |  |     / /  \n"
                + "| |__   |  |__   |  |    / /___\n"
                + "|____|  |_____|  |__|   |______|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eliz");
        System.out.println("What can I do for you?");
    }

    public static void markOrUnmark(String line, Task[] tasks, int taskCounter) {
        if (line.contains("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");
            unmarkATask(line, Arrays.copyOf(tasks, taskCounter));
        } else {
            System.out.println("Nice! I've marked this task as done:");
            markATask(line, Arrays.copyOf(tasks, taskCounter));
        }
    }

    public static void main(String[] args) {
        /** Key Definitions */
        String line;
        String BYE = "bye";
        String LIST = "list";
        Task[] tasks = new Task[100]; //array of Task objects
        botIntroduction(); //calls the introduction of the bot
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int taskCounter = 0;
        while (!line.equals(BYE)) { //while command to end is not entered
            if (line.equals(LIST)) { //check if action is to echo or print tasks
                printTasks(Arrays.copyOf(tasks, taskCounter));
            } else if (line.contains("mark")) { //to check if todos are marked
                Eliz.markOrUnmark(line, tasks, taskCounter);
            } else {
                /** add line to todo, deadline, or event by creating the respective object */
                Task t = createTask(line);
                tasks[taskCounter] = t;
                taskCounter++;
                System.out.println("Got it. I've added this task ");
                System.out.println(t);
                System.out.println("Now you have " + taskCounter + " tasks in the list.");
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
