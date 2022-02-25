import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static String division = "_____________________________________________\n";
    public ArrayList<Task> taskList;
    public Parser parser;

    public TaskList(ArrayList<Task> ... loadedTasks) {
        parser = new Parser();
        if (loadedTasks.length > 0) {
            taskList = loadedTasks[0];
        } else {
            taskList = new ArrayList<>();
        }
    }

    /**
     * Helper for processTasks
     * Prints string representation of Task objects in TaskList
     */
    public void printTaskObjects() {
        System.out.println("Here are the tasks in your list: ");
        int taskNumber = 1;
        for (Task task : taskList) {
            System.out.println(Integer.toString(taskNumber) + ": " + task.toString());
            taskNumber += 1;
        }
    }

    /**
     * Helper for saveTasksToFile
     * Formats taskList into a string to be written into a file
     * @return the string representation of the list of tasks
     */
    public String formatTaskListToString() {
        String taskListString = "";
        for (Task task : taskList) {
            taskListString += task.toString();
            taskListString += "\n";
        }
        return taskListString;
    }

    /**
     * Helper for processTasks
     * Saves the list of tasks to "data/duke.txt" (assumes hard-coded location to save the tasks)
     * @param taskListString string representation of list of tasks to write to file
     * THIS SHOULD PROBABLY BE IN STORAGE BUT I CAN'T REALLY FIGURE IT OUT RN...
     */
    public static void saveTasksToFile(String taskListString) throws IOException {
        // If data directory doesn't exist, write to it
        // SHOULDN'T HARD-CODE THE FILEPATH - ONCE WE CAN PASS STORAGE INSTANCE FROM DUKE TO TASKLIST WE CAN GRAB THE FILEPATH
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        // If duke.txt file doesn't exist, create it
        File dataFile = new File(dataDirectory, "duke.txt");
        dataFile.createNewFile();

        FileWriter fw = new FileWriter("data/duke.txt");
        fw.write(taskListString);
        fw.close();
    }

    /**
     * Helper for processTasks
     * Marks a task as done or unmarks a task when user types "mark" or "unmark" commands, respectively
     * @param line the line of user input
     */
    public void markTask(String line) throws DukeException {
        try {
            String[] taskInfo = line.split("\\s");
            int taskNumber = Integer.parseInt(taskInfo[1].trim());
            String markAction = taskInfo[0].trim();
            Task targetTask = taskList.get(taskNumber - 1);
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
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops, there was an error retrieving the specified task.");
        }
    }

    /**
     * Helper for processTasks
     * Converts line of user input into relevant Task object
     * Adds Task object to global taskList
     * @param line line of user input
     */
    public void addTask(String line) {
        String[] taskInfo = {"", "", ""};
        try {
            taskInfo = parser.extractTaskInfo(line);
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
            taskList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + Integer.toString(taskList.size()) + " tasks on the list.");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Helper for processTasks
     * Deletes user-specified task
     * @param line line of user input
     */
    public void deleteTask(String line) throws DukeException {
        String[] lineWords = line.split("\\s");
        if (lineWords.length != 2) {
            throw new DukeException("Invalid syntax for deleting a task.");
        }
        int deleteIndex = Integer.parseInt(lineWords[1]) - 1;
        try {
            String taskToDelete = taskList.get(deleteIndex).toString();
            taskList.remove(deleteIndex);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(taskToDelete);
            System.out.println("Now you have " + taskList.size() + " tasks on the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Could not remove this task from the list of tasks.");
        }
    }

    /**
     * Processes user input (addition of tasks to the global taskList) until user types "bye"
     */
    public void processTasks() {
        //loadTasksFromDisk();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        // While the user has not exited the program
        while (!line.equals("bye")) {
            try {
                System.out.println(division);
                String keyWord = line.split("\\s")[0].trim();
                // List out the tasks
                if (line.equals("list")) {
                    printTaskObjects();
                    // Mark or unmark a task
                } else if (keyWord.equals("mark") || keyWord.equals("unmark")) {
                    markTask(line);
                    saveTasksToFile(formatTaskListToString());
                    // Delete a task
                } else if (keyWord.equals("delete")) {
                    deleteTask(line);
                    saveTasksToFile(formatTaskListToString());
                    // Add a task
                } else {
                    addTask(line);
                    saveTasksToFile(formatTaskListToString());
                }
                System.out.println(division);
                line = in.nextLine();
            } catch (DukeException e) {
                System.out.println(e);
                System.out.println(division);
                line = in.nextLine();
            } catch (IOException e) {
                System.out.println(e);
                System.out.println(division);
                line = in.nextLine();
            }
        }
        System.out.println(division);
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(division);
    }
}
