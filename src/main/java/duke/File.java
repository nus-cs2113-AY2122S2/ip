package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class File {
    //File
    public static final String FILE_PATH = "data/duke.txt";
    public static final String FILE_FLAG = " \\| ";
    public static final String FILE_TODO = "T";
    public static final String FILE_DEADLINE = "D";
    public static final String FILE_DONE = "1";

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
