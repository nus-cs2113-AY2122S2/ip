import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents a person's list of Tasks that they must complete.
 * This class contains support for maintaining and modifying a collection of Tasks.
 */

public class TaskList {
    private static String division = "_____________________________________________\n";
    protected ArrayList<Task> taskList;
    protected Parser parser;
    protected Storage storage;


    public TaskList(Storage store, Ui ui) {
        parser = new Parser();
        storage = store;
        ui.showGreeting();
        try {
            taskList = storage.loadTasksFromDisk();
        } catch (DukeException e) {
            // If unable to load the tasks form the specified file, initialize an empty taskList
            ui.showLoadingError();
            taskList = new ArrayList<>();
        }
    }


    /**
     * Helper for processTasks.
     * Prints string representation of Task objects in TaskList.
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
     * Helper for saveTasksToFile.
     * Formats taskList into a string to be written into a file.
     * @return The string representation of the list of tasks.
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
     * Helper for processTasks.
     * Marks a task as done or unmarks a task when user types "mark" or "unmark" commands, respectively.
     * @param line The line of user input.
     * @throws DukeException If the user asks to mark/unmark a Task at an out-of-bounds index in the TaskList.
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
     * Helper for processTasks.
     * Converts line of user input into relevant Task object (Todo, Deadline, or Event).
     * Adds Task object to global taskList.
     * @param line Line of user input.
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
     * Helper for processTasks.
     * Filters relevant tasks with the specified keyword.
     * @param keywords The relevant keywords that the user wants to search the taskList for.
     * @returns ArrayList of tasks representing taskList filtered by the specified keywords.
     */
    public ArrayList<Task> findTasks(String keywords) {
        List<Task> filteredTasksList = taskList
                .stream()
                .filter(task -> task.description.contains(keywords))
                .collect(Collectors.toList());
        ArrayList<Task> filteredTasksArrayList = new ArrayList<>();
        filteredTasksArrayList.addAll(filteredTasksList);
        return filteredTasksArrayList;
    }

    /**
     * Helper for processTasks.
     * Deletes user-specified task from the taskList.
     * @param line Line of user input.
     * @throws DukeException If the user asks to delete a Task at an out-of-bounds index in the taskList.
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
     * Processes user input (addition of tasks to the global taskList) until user types "bye".
     */
    public void processTasks() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        // While the user has not exited the program
        while (!line.equals("bye")) {
            try {
                System.out.println(division);
                String keyWord = line.split("\\s")[0].trim();
                if (line.equals("list")) {
                    printTaskObjects();
                } else if (keyWord.equals("mark") || keyWord.equals("unmark")) {
                    markTask(line);
                    storage.saveTasksToFile(formatTaskListToString());
                } else if (keyWord.equals("delete")) {
                    deleteTask(line);
                    storage.saveTasksToFile(formatTaskListToString());
                } else if (keyWord.equals("find")) {
                    String taskKeyWords = line.substring(keyWord.length()).trim();
                    ArrayList<Task> relevantTasks = findTasks(taskKeyWords);
                    for (Task task : relevantTasks) {
                        System.out.println(task.toString());
                    }
                } else { // We can have a catch-all else condition because addTask handles invalid tasks
                    addTask(line);
                    storage.saveTasksToFile(formatTaskListToString());
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
