import java.util.Scanner;
public class Duke {
    private static String division = "_____________________________________________\n";

    // Level-1: Echo
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

    // Level-2: Add, List
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

    // Level 3: Mark as Done
    public static void printTaskObjects(Task[] taskList) {
        int taskIndex = 0;
        int taskNumber = 1;
        while (taskList[taskIndex] != null) {
            Task curTask = taskList[taskIndex];
            System.out.println(Integer.toString(taskNumber) + ": [" + curTask.getStatusIcon() + "]" + " " + curTask.getTaskDescription());
            taskNumber += 1;
            taskIndex += 1;
        }
    }

    public static void manageTasksWithTaskClass() {
        Task[] taskList = new Task[100];
        int taskIndex = 0;
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(division);
            if (line.equals("list")) {
                printTaskObjects(taskList);
            }
            else if (line.split(" ")[0].equals("mark") || line.split(" ")[0].equals("unmark")) {
                String markAction = line.split(" ")[0];
                int taskNumber = Integer.parseInt(line.split(" ")[1]);
                Task targetTask;
                try {
                    targetTask = taskList[taskNumber - 1];
                    // Mark the task as done
                    if (markAction.equals("mark")) {
                        if (targetTask.isDone == true) {
                            System.out.println("Task is already marked as done.");
                        }
                        else {
                            targetTask.isDone = true;
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println("   [X] " + targetTask.getTaskDescription());
                        }
                    }
                    // Unmark the task
                    else {
                        if (targetTask.isDone == false) {
                            System.out.println("Task is already marked as not done yet.");
                        }
                        else {
                            targetTask.isDone = false;
                            System.out.println("OK, I've marked this task as not done yet: ");
                            System.out.println("    [ ] " + targetTask.getTaskDescription());
                        }
                    }
                }
                catch(Exception e) {
                    System.out.println("Oops! The specified task could not be retrieved.");
                    System.out.println(division);
                    line = in.nextLine();
                    System.out.println("[BUG, NEEDS FIXING] Please enter your previous command again.");
                }
            }
            else {
                taskList[taskIndex] = new Task(line);
                taskIndex += 1;
                System.out.println("added: " + line);
            }
            System.out.println(division);
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
        //manageTaskList();

        // Level-3: Mark as Done
        manageTasksWithTaskClass();



    }
}
