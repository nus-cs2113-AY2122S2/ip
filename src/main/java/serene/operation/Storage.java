package serene.operation;

import serene.global.Constant;
import serene.global.Ui;
import serene.task.Deadline;
import serene.task.Event;
import serene.task.Task;
import serene.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /**
     * Reads data from the save file into the supplied task list.
     *
     * @param save The file to read from
     * @param tasks The list to write to
     * @throws FileNotFoundException If Scanner cannot be constructed
     */
    public static void readSavedContents(File save, TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(save);
        while (s.hasNext()) {
            recoverTask(s.nextLine(), tasks);
            tasks.incrementTaskCount();
        }
    }

    /**
     * Restores the task read from save file to the supplied task list.
     *
     * @param savedTask The task read from save file
     * @param tasks The list to write to
     */
    private static void recoverTask(String savedTask, TaskList tasks) {
        // Extract task type
        String taskType = savedTask.substring(Constant.SAVED_INDEX_TYPE, Constant.SAVED_INDEX_TYPE + 1);
        // Extract isDone
        String marker = savedTask.substring(Constant.SAVED_INDEX_IS_DONE, Constant.SAVED_INDEX_IS_DONE + 1);
        // Extract description
        String descriptionAndTime = savedTask.substring(Constant.SAVED_INDEX_DESCRIPTION);
        int timeIndex;
        String description;
        switch (taskType) {
        case "T":
            ToDo todo = new ToDo(descriptionAndTime);
            if (marker.equals("X")) {
                todo.markDone();
            }
            tasks.add(todo);
            break;
        case "D":
            timeIndex = descriptionAndTime.indexOf(" (by: ");
            description = descriptionAndTime.substring(0, timeIndex);
            String by = descriptionAndTime.substring(timeIndex + Constant.TIME_OFFSET, descriptionAndTime.length() - 1);
            Deadline deadline = new Deadline(description, by);
            if (marker.equals("X")) {
                deadline.markDone();
            }
            tasks.add(deadline);
            break;
        case "E":
            timeIndex = descriptionAndTime.indexOf(" (at: ");
            description = descriptionAndTime.substring(0, timeIndex);
            String at = descriptionAndTime.substring(timeIndex + Constant.TIME_OFFSET, descriptionAndTime.length() - 1);
            Event event = new Event(description, at);
            if (marker.equals("X")) {
                event.markDone();
            }
            tasks.add(event);
            break;
        }
    }

    /**
     * Rewrites the save file with the tasks currently present in the supplied task list.
     *
     * @param filePath Path of the save file
     * @param taskList The task list containing the user's tasks
     */
    public static void rewriteSaveFile(String filePath, ArrayList<Task> taskList) {
        try {
            // Clear contents of file
            new FileWriter(filePath, false).close();
            // Rewrite all tasks
            for (Task task : taskList) {
                appendSave(task.toString(), filePath);
            }
        } catch (IOException e) {
            Ui.printWithPartition(Ui.IO_FAIL_MESSAGE);
        }
    }

    /**
     * Appends the provided task to the save file.
     *
     * @param inputTask The task to append
     * @param filePath Path of the save file
     * @throws IOException If FileWriter cannot be constructed
     */
    public static void appendSave(String inputTask, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(inputTask + System.lineSeparator());
        fw.close();
    }
}
