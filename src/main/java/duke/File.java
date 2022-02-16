package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class File {
    //File
    public static final String FILE_PATH = "data/duke.txt";
    public static final String FILE_FLAG = " \\| ";
    public static final String FILE_TODO = "T";
    public static final String FILE_DEADLINE = "D";
    public static final String FILE_EVENT = "E";
    public static final String FILE_DONE = "1";
    public static final String FILE_NOT_DONE = "0";

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

    public static void appendToFile(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH, true);
        if (task instanceof Todo) {
            fileWriter.write(FILE_TODO + FILE_FLAG + convertStatusToString(task.getIsDone()) + FILE_FLAG
                    + task.getDescription());
        } else if (task instanceof Deadline) {
            fileWriter.write(FILE_DEADLINE + FILE_FLAG + convertStatusToString(task.getIsDone()) + FILE_FLAG
                    + task.getDescription() + FILE_FLAG + ((Deadline) task).getDeadline());
        } else {
            fileWriter.write(FILE_EVENT + FILE_FLAG + convertStatusToString(task.getIsDone()) + FILE_FLAG
                    + task.getDescription() + FILE_FLAG + ((Event) task).getAt());
        }
        fileWriter.close();
    }

    public static String convertStatusToString(boolean value) {
        if (value) {
            return FILE_DONE;
        }
        return FILE_NOT_DONE;
    }

    public static void addToDo(String status, String description) {
        Todo task = new Todo(description);
        Duke.tasks[Duke.numTasks] = task;
        Duke.numTasks += 1;
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }

    public static void addDeadline(String status, String description, String deadline) {
        Deadline task = new Deadline(description, deadline);
        Duke.tasks[Duke.numTasks] = task;
        Duke.numTasks += 1;
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }

    public static void addEvent(String status, String description, String deadline) {
        Event task = new Event(description, deadline);
        Duke.tasks[Duke.numTasks] = task;
        Duke.numTasks += 1;
        if (status.equals(FILE_DONE)) {
            task.markAsDone();
        }
    }
}
