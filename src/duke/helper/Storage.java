package duke.helper;

import duke.main.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.helper.TaskList.TODO;
import static duke.helper.TaskList.DEADLINE;
import static duke.helper.TaskList.EVENT;

public class Storage {
    public static final String FILEPATH = "data/duke.txt";
    public static final String DIRPATH = "data";
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
                String taskType = fields[0];
                String marked = fields[1];
                String taskName = fields[2];
                String timeField = "";
                if (fields.length == 4) {
                    timeField = fields[3];
                }
                switch(taskType) {
                case TODO:
                    tasks.addSavedTask(new Todo(taskName, marked.equals("1")));
                    break;
                case DEADLINE:
                    tasks.addSavedTask(new Deadline(taskName, marked.equals("1"), timeField));
                    break;
                case EVENT:
                    tasks.addSavedTask(new Event(taskName, marked.equals("1"), timeField));
                    break;
                default:
                    throw new DukeException("Invalid file format.");
                }
            }
        }catch (IOException | DukeException e) {
            ui.printExceptionMessage(e.getMessage());
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
