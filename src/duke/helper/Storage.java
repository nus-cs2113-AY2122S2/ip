package duke.helper;

import duke.main.Duke;
import duke.main.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static duke.helper.TaskList.TODO;
import static duke.helper.TaskList.DEADLINE;
import static duke.helper.TaskList.EVENT;

/**
 * Represents a storage object to store the data created in the application.
 * Allows the user to save and load saved files in the form of .txt
 */
public class Storage {
    public static final String FILEPATH = "data/duke.txt";
    public static final String DIR_PATH = "data";
    public static final int TODO_FIELDS = 3;
    public static final int DEADLINE_FIELDS = 5;
    public static final int EVENT_FIELDS = 7;
    protected static boolean isLoaded = false;

    public boolean isLoaded() {
        return isLoaded;
    }

    /**
     * Adds a task based on the task type to the list
     * @param fields array of strings containing the fields of the task
     * @param tasks TaskList object containing the list of tasks to be added to
     * @throws DukeException if the number of fields of the task in the .txt does not match with what is required
     */
    private void loadTask(String[] fields, TaskList tasks) throws DukeException {
        String taskType = fields[0];
        String marked = fields[1];
        String taskName = fields[2];
        switch(taskType) {
        case TODO:
            tasks.addSavedTask(new Todo(taskName, marked.equals("1")));
            break;
        case DEADLINE:
            if (fields.length != DEADLINE_FIELDS){
                throw new DukeException("Invalid File Format (Wrong number of fields)");
            }
            LocalDate deadlineDate = LocalDate.parse(fields[3]);
            LocalTime deadlineTime = LocalTime.parse(fields[4]);
            tasks.addSavedTask(new Deadline(taskName, marked.equals("1"), deadlineDate, deadlineTime));
            break;
        case EVENT:
            if (fields.length != EVENT_FIELDS){
                throw new DukeException("Invalid File Format (Wrong number of fields)");
            }
            LocalDate startDate = LocalDate.parse(fields[3]);
            LocalTime startTime = LocalTime.parse(fields[4]);
            LocalDate endDate = LocalDate.parse(fields[5]);
            LocalTime endTime = LocalTime.parse(fields[6]);
            tasks.addSavedTask(new Event(taskName, marked.equals("1"), startDate, startTime,
                    endDate, endTime));
            break;
        default:
            throw new DukeException("Invalid file format.");
        }
    }

    /**
     * Get the fields of the task in the line as saved in the .txt file
     * @param txtScanner Scanner object to read the next line in the file
     * @return an array of string containing the fields of the task
     * @throws DukeException if the file format is invalid
     */
    private String[] getFields(Scanner txtScanner) throws DukeException {
        isLoaded = true;
        String line = txtScanner.nextLine();
        String[] fields = line.split(" \\| ");
        if (fields.length < TODO_FIELDS) {
            throw new DukeException("Invalid File Format (Fields missing)");
        }
        return fields;
    }

    /**
     * Loads the saved tasks from the fixed file location to the list of tasks
     * @param ui Ui object to handle communication with the user
     * @param tasks TaskList object to store the saved tasks
     */
    public void loadSavedTasks(Ui ui, TaskList tasks) {
        try {
            File dirPath = new File(DIR_PATH);
            if (!dirPath.exists()){
                dirPath.mkdirs();
            }
            File file = new File(FILEPATH);
            Scanner txtScanner = new Scanner(file);
            while (txtScanner.hasNext()) {
                String[] fields = getFields(txtScanner);
                loadTask(fields, tasks);
            }
        }catch (DukeException | DateTimeParseException e) {
            ui.printExceptionMessage(e.getMessage());
            isLoaded = false;
        }catch (IOException e) {
            isLoaded = false;
        }
    }

    /**
     * Save the tasks in the list into a .txt file
     * @param ui Ui object to handle communication with the user
     * @param tasks TaskList object that contains the list of tasks to be saved
     */
    public void saveTasks(Ui ui, TaskList tasks) {
        try {
            File dirPath = new File(DIR_PATH);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            FileWriter file = new FileWriter(FILEPATH, false);
            String data = "";
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                data += tasks.getTaskDetails(i);
            }
            file.write(data);
            file.close();
        }catch (IOException e) {
            ui.printExceptionMessage(e.getMessage());
        }
    }
}
