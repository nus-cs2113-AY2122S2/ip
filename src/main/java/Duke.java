import java.util.Scanner;
public class Duke {
    private static String division = "_____________________________________________\n";

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(division);
            System.out.println(line);
            System.out.println(division);
            line = in.nextLine();
        }
        System.out.println(division);
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(division);
    }


    public static void printTaskList(String[] taskList) {
        int taskIndex = 0;
        int taskNumber = 1;
        System.out.print(division);
        while (taskList[taskIndex] != null) {
            System.out.println(Integer.toString(taskNumber) + ": " + taskList[taskIndex]);
            taskNumber += 1;
            taskIndex += 1;
        }
        System.out.println(division);
    }

    public static void manageTaskList() {
        String[] taskList = new String[100];
        int taskIndex = 0;
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printTaskList(taskList);
            }
            else {
                taskList[taskIndex] = line;
                taskIndex += 1;
                System.out.println(division);
                System.out.println("added: " + line);
                System.out.println(division);
            }
            line = in.nextLine();
        }
        System.out.println(division);
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(division);
    }


    public static void main(String[] args) {
        // Greeting
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hello! I'm Duke, the greatest virtual assistant in the world :) \nWhat can I do for you?\n";
        System.out.println(division);
        System.out.println(greeting);
        System.out.println(division);

        // Level-1: Greet, Echo, Exit
        //echo();

        // Level-2: Add, List
        manageTaskList();


    }
}
