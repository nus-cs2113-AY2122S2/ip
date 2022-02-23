package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    //File
    public static final String FILE_PATH = "data/duke.txt";
    public static final String FILE_FLAG = " \\| ";
    public static final String FILE_TODO = "T";
    public static final String FILE_DEADLINE = "D";
    public static final String FILE_EVENT = "E";
    public static final String FILE_DONE = "1";
    public static final char FILE_DONE_CHAR = '1';
    public static final String FILE_NOT_DONE = "0";
    public static final int FILE_STATUS_INDEX = 4;

    /**
     * Loads the saved tasks from the user's local file to Duke's list of tasks.
     * Parses the content of the file line by line and adds the task to the list of tasks.
     *
     * @throws FileNotFoundException If file is not found
     */
    public static void loadFileContents() throws FileNotFoundException {
        java.io.File f = new java.io.File(FILE_PATH);
        Scanner s = new Scanner(f);
        String[] contentArray;
        while (s.hasNext()) {
            String input = s.nextLine();
            contentArray = input.split(FILE_FLAG);
            if (contentArray[0].equals(FILE_TODO)) {
                addToDo(contentArray[1], contentArray[2]);
            } else if (contentArray[0].equals(FILE_DEADLINE)) {
                addDeadline(contentArray[1], contentArray[2], contentArray[3]);
            } else {
                addEvent(contentArray[1], contentArray[2], contentArray[3]);
            }
        }
    }

    /**
     * Updates the file by overwriting each task line by line from the list of tasks to the local file.
     *
     * @throws IOException
     */
    public static void updateFile() throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = new ArrayList<>();
        for (Task task: Ui.taskList) {
            lines.add(Parser.getFormattedString(task));
        }
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    /**
     * Adds a Todo task to the list of tasks.
     *
     * @param status status of the task
     * @param description description of the task
     */
    public static void addToDo(String status, String description) {
        Todo task = new Todo(description);
        Ui.taskList.add(task);
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }

    /**
     * Adds a Deadline task to the list of tasks.
     *
     * @param status status of task
     * @param description description of task
     * @param deadline deadline of task
     */
    public static void addDeadline(String status, String description, String deadline) {
        Deadline task = new Deadline(description, deadline);
        Ui.taskList.add(task);
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }

    /**
     * Adds an Event task to the list of tasks.
     *
     * @param status status of task
     * @param description description of task
     * @param at time period of task
     */
    public static void addEvent(String status, String description, String at) {
        Event task = new Event(description, at);
        Ui.taskList.add(task);
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }
}
