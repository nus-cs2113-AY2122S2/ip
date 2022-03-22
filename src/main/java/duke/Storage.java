package duke;

import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String EXCEPTION_ERROR_WRITE = "Error writing to file.";
    private final String folderPath;
    private final String fileName;

    public Storage(String filePath) {
        this.folderPath = filePath.split("/")[0];
        this.fileName=  filePath.split("/")[1];
    }

    /**
     * Returns an ArrayList containing the list of Task that have been loaded from the filepath
     *
     * @return An ArrayList of Task
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            File f = new File(folderPath + "/" + fileName);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String userInput = s.nextLine();
                String[] userInputArr = userInput.split(" \\| ");
                String taskType = userInputArr[0];
                boolean isDone = userInputArr[1].equals("1");

                LocalDate dateInput;

                switch (taskType) {
                case Todo.TASK_SHORTFORM:
                    tasks.add(new Todo(isDone, userInputArr[2]));
                    break;
                case Deadline.TASK_SHORTFORM:
                    dateInput = LocalDate.parse(userInputArr[3]);
                    tasks.add(new Deadline(isDone, userInputArr[2], dateInput));
                    break;
                case Event.TASK_SHORTFORM:
                    dateInput = LocalDate.parse(userInputArr[3]);
                    tasks.add(new Event(isDone, userInputArr[2], dateInput));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            File directory = new File(folderPath);
            /** Create directory if not found */
            if (!directory.exists()) {
                directory.mkdir();
            }

            File f = new File(folderPath + "/" + fileName);
            /** Create file if not found. If IOError, print error message */
            try {
                f.createNewFile();
            } catch (IOException err) {
                throw new DukeException("Error loading from the filepath.");
            }
        }

        return tasks;
    }

    /**
     * Writes each Task found in tasks to the filepath specified earlier by the user
     *
     * @param tasks TaskList containing current list of Task
     */
    public void writeTasksToStorage(TaskList tasks) throws DukeException {
        try {
            String output = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                output += tasks.getTask(i).saveString() + System.lineSeparator();
            }
            FileWriter fw = new FileWriter(folderPath + "/" + fileName);
            fw.write(output);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(EXCEPTION_ERROR_WRITE);
        }
    }
}
