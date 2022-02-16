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

public class File {
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

    public static void updateFile() throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = new ArrayList<>();
        for (Task task: Duke.taskList) {
            lines.add(getFormattedString(task));
        }
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static String getFormattedString(Task task) {
        if (task instanceof Todo) {
            return (FILE_TODO + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription());
        } else if (task instanceof Deadline) {
            return (FILE_DEADLINE + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription() + " | " + ((Deadline) task).getDeadline());
        } else {
            return (FILE_EVENT + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription() + " | " + ((Event) task).getAt());
        }
    }

    public static String convertStatusToString(boolean value) {
        if (value) {
            return FILE_DONE;
        }
        return FILE_NOT_DONE;
    }

    public static void addToDo(String status, String description) {
        Todo task = new Todo(description);
        Duke.taskList.add(task);
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }

    public static void addDeadline(String status, String description, String deadline) {
        Deadline task = new Deadline(description, deadline);
        Duke.taskList.add(task);
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }

    public static void addEvent(String status, String description, String deadline) {
        Event task = new Event(description, deadline);
        Duke.taskList.add(task);
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }
}
