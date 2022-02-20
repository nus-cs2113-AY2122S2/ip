package aeon.controller;

import aeon.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    /**
     * Changes the date format from YYYY-MM-DD to MM-DD-YYYY for Deadline and Event tasks
     * @param rawDate The date that the user inputs
     * @return The reformatted date in the form of MM-DD-YYYY
     */
    public static String reformatDate(String rawDate) {
        LocalDate deadline = LocalDate.parse(rawDate);
        String dueDate = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return dueDate;
    }

    /**
     * Reads in each task from a saved list of task and determines how it should be added to the list
     * @param list list of tasks to store the tasks read from the text file
     * @param taskInFileArray list of tasks in the text file
     */
    public static void parseSavedTaskList(ArrayList<Task> list, String[] taskInFileArray) {
        String[] taskType = taskInFileArray[1].split(" ", 2);
        String isDone = taskInFileArray[0];
        switch (taskType[UI.TASK_TYPE]) {
        case UI.TASKTYPE_TODO:
            Storage.readFromFileTodo(list, taskType, isDone);
            break;
        case UI.TASKTYPE_DEADLINE:
            Storage.readFromFileDeadline(list, taskType, isDone);
            break;
        case UI.TASKTYPE_EVENT:
            Storage.readFromFileEvent(list, taskType, isDone);
            break;
        default:
            UI.printOut(UI.UNKNOWN_COMMAND_TEXT_FILE);
        }
    }
}
