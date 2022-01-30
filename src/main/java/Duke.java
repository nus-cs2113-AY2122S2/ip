import java.util.Scanner;
public class Duke {
    private static String division = "_____________________________________________\n";
    private static Task[] taskList = new Task[100];
    private static int taskIndex = 0;

    /**
     * Helper for processTasks
     * Prints taskList when user types "list" command in program
     * @param taskList
     */
    public static void printTaskObjects(Task[] taskList) {
        int taskInd = 0;
        int taskNumber = 1;
        System.out.println("Here are the tasks in your list:");
        while (taskList[taskInd] != null) {
            Task curTask = taskList[taskInd];
            System.out.println(Integer.toString(taskNumber) + ": " + curTask.toString());
            taskNumber += 1;
            taskInd += 1;
        }
    }

    /**
     * Helper for processTasks
     * Marks a task as done or unmarks a task when user types "mark" or "unmark" commands, respectively
     * @param taskList
     * @param markAction
     * @param taskNumber
     */
    public static void markTask(Task[] taskList, String markAction, int taskNumber) {
        if (taskNumber <= 0 || (taskList[taskNumber - 1] == null)) {
            System.out.println("Oops, there was an error retrieving the specified task.");
        } else {
            Task targetTask = taskList[taskNumber - 1];
            // Mark the task as done
            if (markAction.equals("mark")) {
                if (targetTask.isDone == true) {
                    System.out.println("Task is already marked as done.");
                } else {
                    targetTask.isDone = true;
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(targetTask.toString());
                }
            } else { // Unmark the task
                if (targetTask.isDone == false) {
                    System.out.println("Task is already marked as not done yet.");
                } else {
                    targetTask.isDone = false;
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(targetTask.toString());
                }
            }
        }
    }


    /**
     * Helper for addTask
     * Extracts information about Task object from line of user input
     * Reports information in array of Strings
     * @param inputLine line of user input
     * @return array of Strings (task type, description, additional info)
     */
    public static String[] extractTaskInfo(String inputLine) {
        String type;
        String description;
        String additionalInfo;
        int additionalInfoIndex = inputLine.length(); // Index where description should end

        // Extract the type of task
        type = inputLine.split(" ")[0];

        // Extract the deadline (for Deadline tasks) or time (for Event tasks), if applicable
        if (type.equals("deadline")) {
            additionalInfo = inputLine.substring(inputLine.indexOf("/by") + 4);
            additionalInfoIndex = inputLine.indexOf("/by");
        } else if (type.equals("event")) {
            additionalInfo = inputLine.substring(inputLine.indexOf("/at") + 4);
            additionalInfoIndex = inputLine.indexOf("/at");
        } else {
            additionalInfo = "";
        }

        // Extract the description
        if (type.equals("todo")) {
            description = inputLine.substring(type.length() + 1);
        } else if (type.equals("deadline") || type.equals("event")) {
            description = inputLine.substring(type.length() + 1, additionalInfoIndex - 1);
        } else { // No task type specified; the entire line is the task description
            description = inputLine;
        }

        return new String[] { type, description, additionalInfo };
    }

    /**
     * Helper for processTasks
     * Converts line of user input into relevant Task object
     * Adds Task object to global taskList
     * @param line line of user input
     */
    public static void addTask(String line) {
        String[] taskInfo = extractTaskInfo(line);
        String type = taskInfo[0];
        String description = taskInfo[1];
        String additionalInfo = taskInfo[2];
        Task task = new Task(line);

        if (type.equals("todo")) {
            task = new Todo(description);
        } else if (type.equals("deadline")) {
            task = new Deadline(description, additionalInfo);
        } else if (type.equals("event")) {
            task = new Event(description, additionalInfo);
        }

        taskList[taskIndex] = task;
        taskIndex += 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + Integer.toString(taskIndex) + " tasks on the list.");
    }

    /**
     * Processes user input (addition of tasks to the global taskList) until user types "bye"
     */
    public static void processTasks() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        // While the user has not exited the program
        while (!line.equals("bye")) {
            System.out.println(division);
            // List out the tasks
            if (line.equals("list")) {
                printTaskObjects(taskList);
            // Mark or unmark tasks
            } else if (line.split(" ")[0].equals("mark") || line.split(" ")[0].equals("unmark")) {
                String markAction = line.split(" ")[0];
                int taskNumber = Integer.parseInt(line.split(" ")[1]);
                markTask(taskList, markAction, taskNumber);
            // Add a task
            } else {
                addTask(line);
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

        processTasks();

    }
}
