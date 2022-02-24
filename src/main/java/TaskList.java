import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static String division = "_____________________________________________\n";
    public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> ... loadedTasks) {
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
     * Helper for convertTaskStringToTask
     * "Cleans" a string representation of a task (gets rid of extraneous characters like brackets and parens)
     * Converts clean string representation into an ArrayList
     * @param taskString the string representation of the task
     * @return ArrayList containing the information of the taskString
     */
    public static ArrayList<String> cleanTaskListString(String taskString) {
        taskString = taskString.replace("[", "").replace("]", "").replace("(", "").replace(")", "");
        String[] splitInformation = taskString.split("\\s");
        ArrayList<String> taskInformation = new ArrayList<>();
        for (int i = 0; i < splitInformation.length; i++) {
            if (splitInformation[i].trim().length() > 0) {
                taskInformation.add(splitInformation[i]);
            }
        }
        return taskInformation;
    }

    /**
     * Helper for convertTaskStringToTask
     * Given an ArrayList of strings, a starting index, and an ending index, concatenates the strings in the
     * ArrayList within the boundaries of the indices into a single string
     * @param list the given ArrayList
     * @param startIndex the index to start concatenating from
     * @param endIndex the index to stop concatenating at (not inclusive)
     * @return the concatenated string
     */
    public static String concatenateStringsInArrayList(ArrayList<String> list, int startIndex, int endIndex) {
        String string = "";
        for (int i = startIndex; i < endIndex; i++) {
            string += list.get(i);
            string += " ";
        }
        return string.trim();
    }


    /**
     * Helper for loadTasksFromDisk
     * Converts a string representation of a Task to a Task object
     * @param taskString the string representation of the task
     * @return the Task object
     */
    public static Task convertTaskStringToTask(String taskString) {
        Task task = new Task("");
        ArrayList<String> taskInformation = cleanTaskListString(taskString);
        char taskType = taskInformation.get(0).charAt(0);
        boolean isTaskDone = false;
        if (taskInformation.get(0).length() > 1) {
            isTaskDone = true;
        }
        String description;
        if (taskType == 'T') {
            description = concatenateStringsInArrayList(taskInformation, 1, taskInformation.size());
            task = new Todo(description);
        } else if (taskType == 'D') {
            description = concatenateStringsInArrayList(taskInformation, 1, taskInformation.indexOf("by:"));
            String deadline = concatenateStringsInArrayList(taskInformation,taskInformation.indexOf("by:") + 1, taskInformation.size());
            task = new Deadline(description, deadline);
        } else if (taskType == 'E') {
            description = concatenateStringsInArrayList(taskInformation, 1, taskInformation.indexOf("at:"));
            String time = concatenateStringsInArrayList(taskInformation,taskInformation.indexOf("at:") + 1, taskInformation.size());
            task = new Event(description, time);
        }
        task.isDone = isTaskDone;
        return task;
    }

    /**
     * Helper for processTasks
     * Loads tasks from duke.txt (file of saved tasks)
     * Assumes hard-coded location of saved tasks ("data/duke.txt")
     */
    public void loadTasksFromDisk() {
        File savedTaskFile = new File("data/duke.txt");
        try {
            ArrayList<String> taskLines = new ArrayList<>();
            Scanner s = new Scanner(savedTaskFile);
            while (s.hasNext()) {
                taskLines.add(s.nextLine());
            }
            for (String taskString : taskLines) {
                Task task = convertTaskStringToTask(taskString);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            //throw new DukeException("No tasks found saved on disk, starting with a fresh slate!");
            System.out.println("No tasks found saved on disk, starting with a fresh slate!");
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
     */
    public static void saveTasksToFile(String taskListString) throws IOException {
        // If data directory doesn't exist, write to it
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
     * Helper for addTask
     * Extracts information about Task object from line of user input
     * Reports information in array of Strings
     * @param inputLine line of user input
     * @return array of Strings (task type, description, additional info)
     */
    public static String[] extractTaskInfo(String inputLine) throws DukeException {
        String type;
        String description = "";
        String additionalInfo;
        int additionalInfoIndex = inputLine.length(); // Index where description should end

        // Extract the type of task
        type = inputLine.split("\\s")[0].trim();
        // If the task type is not a todo, deadline, or event, throw a DukeException
        if ((!type.equals("todo")) && (!type.equals("deadline")) && (!type.equals("event"))) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        // Extract the deadline (for Deadline tasks) or time (for Event tasks), if applicable
        if (type.equals("deadline")) {
            // If the task doesn't have a deadline, throw a DukeException
            if (!inputLine.contains("/by") || inputLine.endsWith("/by")) {
                throw new DukeException("OOPS!!! Task of type deadline must have a deadline specified.");
            }
            additionalInfo = inputLine.substring(inputLine.indexOf("/by") + 4);
            // If the task has an empty deadline, throw a DukeException
            if (additionalInfo.trim().isEmpty()) {
                throw new DukeException("OOPS!!! Type of task deadline must not have an empty deadline.");
            }
            additionalInfoIndex = inputLine.indexOf("/by");
        } else if (type.equals("event")) {
            // If the task is an event and doesn't have a time, throw a DukeException
            if (!inputLine.contains("/at") || inputLine.endsWith("/at")) {
                throw new DukeException("OOPS!!! Task of type event must have a time specified.");
            }
            additionalInfo = inputLine.substring(inputLine.indexOf("/at") + 4);
            // If the task has an empty time, throw a DukeException
            if (additionalInfo.trim().isEmpty()) {
                throw new DukeException("OOPS! Task of type event must not have an empty time.");
            }
            additionalInfoIndex = inputLine.indexOf("/at");
        } else {
            additionalInfo = "";
        }

        // Extract the description
        // If the description is empty, throw a DukeException
        try {
            description = inputLine.substring(type.length(), additionalInfoIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
        }
        if (description.trim().isEmpty()) {
            throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
        }

        return new String[] { type.trim(), description.trim(), additionalInfo.trim() };
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
            taskInfo = extractTaskInfo(line);
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
