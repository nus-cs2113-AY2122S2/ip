import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * Represents a <code>Storage</code> object that contains support for
 * saving tasks/loading tasks from storage (ensures persistent storage
 * of tasks)
 */

public class Storage {
    public String filePath;
    public Storage(String filePath) {

        this.filePath = filePath;
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
     * Returns ArrayList of Task objects
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

    // TODO: ADD SAVETASKBEHAVIOR HERE AS WELL (ONCE WE KNOW HOW TO PASS INSTANCE OF STORAGE TO TASKLIST CLASS - BECAUSE THAT ALLOWS US TO KNOW WHICH FILEPATH TO SAVE THE TASKS IN TASKLISTCLASS TO)


}
