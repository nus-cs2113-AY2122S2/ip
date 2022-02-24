package duke.helper;

import duke.main.DukeException;
import duke.task.Deadline;
import duke.task.Event;
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

public class Storage {
    public static final String FILEPATH = "data/duke.txt";
    public static final String DIRPATH = "data";
    public static final int TODOFIELDS = 3;
    public static final int DEADLINEFIELDS = 5;
    public static final int EVENTFIELDS = 7;
    protected static boolean isLoaded = false;

    public boolean isLoaded() {
        return isLoaded;
    }

    public void loadSavedTasks(Ui ui, TaskList tasks) {
        try {
            File dirPath = new File(DIRPATH);
            if (!dirPath.exists()){
                dirPath.mkdirs();
            }
            File file = new File(FILEPATH);
            Scanner txtScanner = new Scanner(file);
            while (txtScanner.hasNext()) {
                isLoaded = true;
                String line = txtScanner.nextLine();
                String[] fields = line.split(" \\| ");
                if (fields.length < TODOFIELDS) {
                    throw new DukeException("Invalid File Format (Fields missing)");
                }
                String taskType = fields[0];
                String marked = fields[1];
                String taskName = fields[2];
                switch(taskType) {
                case TODO:
                    tasks.addSavedTask(new Todo(taskName, marked.equals("1")));
                    break;
                case DEADLINE:
                    if (fields.length != DEADLINEFIELDS){
                        throw new DukeException("Invalid File Format (Wrong number of fields)");
                    }
                    LocalDate deadlineDate = LocalDate.parse(fields[3]);
                    LocalTime deadlineTime = LocalTime.parse(fields[4]);
                    tasks.addSavedTask(new Deadline(taskName, marked.equals("1"), deadlineDate, deadlineTime));
                    break;
                case EVENT:
                    if (fields.length != EVENTFIELDS){
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
        }catch (DukeException | DateTimeParseException e) {
            ui.printExceptionMessage(e.getMessage());
            isLoaded = false;
        }catch (IOException e){
            isLoaded = false;
        }
    }
    public void saveTasks(Ui ui, TaskList tasks) {
        try {
            File dirPath = new File(DIRPATH);
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
