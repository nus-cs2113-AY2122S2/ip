import java.util.Arrays;
import java.util.Scanner;

public class Eliz {
    public static void printTasks(String[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            int numToPrint = i + 1;
            System.out.println(numToPrint + ". " + tasks[i]);
        }
    }

    public static void main(String[] args) {
        /** Key Definitions */
        String line;
        String bye = "bye";
        String list = "list";
        String[] tasks = new String[100]; //to store tasks
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
        while (!line.equals(bye)) { //while command to end is not entered
            if (line.equals(list)) { //check if action is to echo or print tasks
                printTasks(Arrays.copyOf(tasks, taskCounter));
            } else {
                /** add line to tasks */
                tasks[taskCounter] = line;
                taskCounter++;
                System.out.println("added: " + line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
