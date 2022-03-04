import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * Represents a <code>Storage</code> object that contains support for
 * saving tasks/loading tasks from storage (ensures persistent storage
 * of tasks)
 */

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Helper for convertTaskStringToTask.
     * "Cleans" a string representation of a task (gets rid of extraneous characters like brackets and parens).
     * Converts clean string representation into an ArrayList.
     * @param taskString The string representation of the task.
     * @return ArrayList containing the information of the taskString.
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
     * Helper for convertTaskStringToTask.
     * Given an ArrayList of strings, a starting index, and an ending index, concatenates the strings in the
     * ArrayList within the boundaries of the indices into a single string.
     * @param list The given ArrayList.
     * @param startIndex The index to start concatenating from.
     * @param endIndex The index to stop concatenating at (not inclusive).
     * @return The concatenated string.
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
     * Helper for loadTasksFromDisk.
     * Converts a string representation of a Task to a Task object.
     * @param taskString the string representation of the task.
     * @return the Task object.
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
     * Helper for processTasks.
     * Loads tasks from storage (file of saved tasks).
     * @return ArrayList of Task objects loaded from storage.
     * @throws DukeException If the specified file to load the Task objects from is not found.
     */
    public ArrayList<Task> loadTasksFromDisk() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File savedTaskFile = new File(this.filePath);
            ArrayList<String> taskLines = new ArrayList<>();
            Scanner s = new Scanner(savedTaskFile);
            while (s.hasNext()) {
                taskLines.add(s.nextLine());
            }
            for (String taskString : taskLines) {
                Task task = convertTaskStringToTask(taskString);
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("The specified file was not found!");
        }
    }

    /**
     * Helper for saveTasksToFile.
     * Separates this.filePath into directory and file name).
     * Assumption: this.filePath is in format "directory/fileName."
     * @return array of Strings (directory, file name)
     * @throws DukeException if the filepath is not in the correct "directory/fileName" format.
     */
    public String[] separateFilePath() throws DukeException {
        int slashIndex = filePath.indexOf("/");
        if (slashIndex != -1) {
            String directory = filePath.substring(0, slashIndex);
            String fileName = filePath.substring(slashIndex + 1);
            return new String[]{directory, fileName};
        } else {
            throw new DukeException("Filepath is not in the correct format! " +
                    "The tasks you input during this iteration of Duke will not be saved. " +
                    "You will be unable to view the tasks the next time you run the application.");
        }
    }


    /**
     * Helper for processTasks.
     * Saves the list of tasks to the filepath of the Storage object.
     * @param taskListString string representation of list of tasks to write to file.
     * @throws IOException If there is an error saving the list of tasks to the file.
     */
    public void saveTasksToFile(String taskListString) throws IOException {
        try {
            String[] separatedFilePath = separateFilePath();
            String directory = separatedFilePath[0];
            String fileName = separatedFilePath[1];
            // If data directory doesn't exist, write to it
            File dataDirectory = new File(directory);
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }
            // If duke.txt file doesn't exist, create it
            File dataFile = new File(dataDirectory, fileName);
            dataFile.createNewFile();

            FileWriter fw = new FileWriter(filePath);
            fw.write(taskListString);
            fw.close();
        } catch (DukeException e) {
            // If the filePath to save tasks to is not valid
            System.out.println(e);

        }
    }


}
