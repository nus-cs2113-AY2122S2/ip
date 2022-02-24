package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final ArrayList<Task> tasks;

    public Storage(String filePath) {
        tasks = new ArrayList<>();
        try {
            loadSaveFile(filePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads tasks saved in a save file, currently hardcoded to data/duke.txt.
     * @throws FileNotFoundException if save file not found.
     * @throws DukeException if a Duke error occurs.
     */
    private void loadSaveFile(String filePath) throws IOException, DukeException {
        File saveFile = new File(filePath);
        saveFile.getParentFile().mkdirs();
        saveFile.createNewFile();
        Scanner fileScan = new Scanner(saveFile);
        while (fileScan.hasNextLine()) {
            tasks.add(loadTask(fileScan.nextLine()));
        }
    }

    /**
     * Reads a single line from the save file and returns a Task representing it.
     * @param nextLine The line to be read from the save file.
     * @return A task representing a line from the save file.
     * @throws DukeException if a Duke error occurs.
     */
    private static Task loadTask(String nextLine) throws DukeException {
        String[] pieces = nextLine.split("\\|");
        String taskType = pieces[0];
        ArrayList<String> task = new ArrayList<>();
        task.add(loadTaskType(taskType));
        Boolean isDone = loadCompletionStatus(pieces[1]);
        Task loadedTask;
        switch (taskType) {
        case "T":
            loadedTask = loadToDoDetails(task, pieces);
            break;
        case "D":
            loadedTask = loadDeadlineDetails(task, pieces);
            break;
        case "E":
            loadedTask = loadEventDetails(task, pieces);
            break;
        default:
            loadedTask = null;
        }
        if (isDone) {
            assert loadedTask != null;
            loadedTask.doTask();
        }
        return loadedTask;
    }

    /**
     * converts the shorthand Task char from a line in the save file to a class name.
     * @param taskType a char representing the task type.
     * @return a String representing the task type.
     */
    private static String loadTaskType(String taskType) {
        switch (taskType) {
        case "T":
            return Parser.COMMAND_TODO;
        case "D":
            return Parser.COMMAND_DEADLINE;
        case "E":
            return Parser.COMMAND_EVENT;
        }
        return null;
    }

    /**
     * Returns true if the task is marked complete in the save file and false if otherwise.
     * @param isDone A char from the save file: 1 if the task is done, 0 if not.
     * @return true if the task is done, false if not.
     */
    private static Boolean loadCompletionStatus(String isDone) {
        return isDone.equals("1");
    }

    /**
     * Replaces the save file with the current list of tasks.
     * @throws IOException if the save file cannot be written to.
     */
    public void updateSaveFile() throws IOException {
        FileWriter fileToWriteTo = new FileWriter("data/duke.txt");
        StringBuilder saveFile = new StringBuilder();
        for (Task task: this.tasks) {
            saveFile.append(taskToString(task)).append(System.lineSeparator());
        }
        fileToWriteTo.write(saveFile.toString());
        fileToWriteTo.close();
    }

    /**
     * Fills the ToDo task component list with its description.
     * @param task An arraylist that will be built into a task.
     * @param pieces An array containing the '|' delimited chunks from a line of the save file.
     * @return a Task
     * @throws DukeException if a Duke error occurs.
     */
    private static Task loadToDoDetails(ArrayList<String> task, String[] pieces) throws DukeException {
        task.add(pieces[2]);
        return TaskList.buildTask(task);
    }

    /**
     * Fills the Deadline task component list with its description.
     * @param task An arraylist that will be built into a task.
     * @param pieces An array containing the '|' delimited chunks from a line of the save file.
     * @return a Task.
     * @throws DukeException if a Duke error occurs.
     */
    private static Task loadDeadlineDetails(ArrayList<String> task, String[] pieces) throws DukeException {
        // description of task
        task.add(pieces[2]);
        // deadline of task
        task.add(pieces[3]);
        return TaskList.buildTask(task);
    }

    /**
     * Turns a ToDo into save file String format.
     * @param task the ToDo to be converted.
     * @return a String in save file format.
     */
    private String taskToString(ToDo task){
        String ret = "T|";
        if (task.getStatusIcon().equals("X")) {
            ret += "1|";
        } else {
            ret += "0|";
        }
        ret += task.getDescription();
        return ret;
    }

    /**
     *
     * Turns a Deadline into save file String format.
     * @param task the Deadline to be converted.
     * @return a String in save file format.
     */
    private String taskToString(Deadline task) {
        String ret = "D|";
        if (task.getStatusIcon().equals("X")) {
            ret += "1|";
        } else {
            ret += "0|";
        }
        ret += (task.getDescription() + "|");
        ret += (task.getTiming() + "|");
        return ret;
    }

    /**
     * Fills the Event task component list with its description.
     * @param task An arraylist that will be built into a task.
     * @param pieces An array containing the '|' delimited chunks from a line of the save file.
     * @return a Task.
     * @throws DukeException if a Duke error occurs.
     */
    private static Task loadEventDetails(ArrayList<String> task, String[] pieces) throws DukeException {
        // description of task
        task.add(pieces[2]);
        // timing of event
        task.add(pieces[3]);
        return TaskList.buildTask(task);
    }

    /**
     *
     * Turns an Event into save file String format.
     * @param task the Event to be converted.
     * @return a String in save file format.
     */
    private String taskToString(Event task) {
        String ret = "E|";
        if (task.getStatusIcon().equals("X")) {
            ret += "1|";
        } else {
            ret += "0|";
        }
        ret += (task.getDescription() + "|");
        ret += (task.getTiming() + "|");
        return ret;
    }

    /**
     * Turns a task into a String that can be written to the save file.
     * @param task The task to be converted.
     * @return A String in save file format.
     */
    private String taskToString(Task task) {
        if (task instanceof ToDo) {
            return taskToString((ToDo) task);
        }
        if (task instanceof Deadline) {
            return taskToString((Deadline) task);
        }
        if (task instanceof Event) {
            return taskToString((Event) task);
        }
        return null;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
