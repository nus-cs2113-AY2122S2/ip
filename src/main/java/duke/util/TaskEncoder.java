package duke.util;

import duke.tasks.*;

/**
 * Encodes the {@code taskList} object into a data file for storage.
 */

public class TaskEncoder {

    /**
     * Encodes all the {@code Task} in the {@code taskList} into a readable string.
     */
    public static String encodeTasks(TaskList taskList){
        String taskListString = "";
        for (Task task:taskList.getFullList()){
            taskListString = taskListString + encodeTaskToString(task);
        }
        return taskListString;
    }

    /**
     * Encodes {@code Task} into a readable string.
     */
    
    public static String encodeTaskToString(Task task){
        String taskString = "";
        if (task instanceof Todo){
            taskString = task.getCategory() + "|" + task.getStatusNumber() + "|" +
                    task.getDescription() + System.lineSeparator();
        }
        else if (task instanceof Event) {
            taskString = task.getCategory() + "|" + task.getStatusNumber() + "|" +
                    task.getDescription() + "|" +((Event) task).getStartEndTime() + System.lineSeparator();
        }
        else if (task instanceof Deadline) {
            taskString = task.getCategory() + "|" + task.getStatusNumber() + "|" +
                    task.getDescription() + "|" + ((Deadline) task).getBy() + System.lineSeparator();
        }
        else {
            taskString = "";
        }
        return taskString;
    }
}
