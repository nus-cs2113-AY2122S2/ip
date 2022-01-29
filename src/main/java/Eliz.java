import java.util.Arrays;
import java.util.Scanner;

public class Eliz {
    public static void printTasks(Task[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            int numToPrint = i + 1;
            System.out.println(numToPrint + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
    }

    public static void markATask(String line, Task[] tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        tasks[taskNumInt - 1].setAsDone();
        System.out.println("[X] " + tasks[taskNumInt - 1].description);
    }

    public static void unmarkATask(String line, Task[] tasks) {
        char taskNumChar = line.charAt(line.length() - 1);
        int taskNumInt = Character.getNumericValue(taskNumChar);
        tasks[taskNumInt - 1].setAsNotDone();
        System.out.println("[ ] " + tasks[taskNumInt - 1].description);
    }

    public static void main(String[] args) {
        /** Key Definitions */
        String line;
        String BYE = "bye";
        String LIST = "list";
        Task[] tasks = new Task[100]; //array of Task objects
        String logo = " ____    __       __     ______ \n"
                    + "|  __|  |  |     |  |   |___  /\n"
                    + "| |__   |  |     |  |      / /  \n"
                    + "| |__|  |  |     |  |     / /  \n"
                    + "| |__   |  |__   |  |    / /___\n"
                    + "|____|  |_____|  |__|   |______|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eliz");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int taskCounter = 0;
        while (!line.equals(BYE)) { //while command to end is not entered
            if (line.equals(LIST)) { //check if action is to echo or print tasks
                printTasks(Arrays.copyOf(tasks, taskCounter));
            } else if (line.contains("mark")) {
                if (line.contains("unmark")) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    unmarkATask(line, Arrays.copyOf(tasks, taskCounter));
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                    markATask(line, Arrays.copyOf(tasks, taskCounter));
                }
            } else {
                /** add line to tasks by creating a Task object */
                Task t = new Task(line);
                tasks[taskCounter] = t;
                taskCounter++;
                System.out.println("added: " + line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
