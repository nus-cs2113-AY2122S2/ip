import java.io.*;
import java.util.Scanner;

public class Storage {

    public static final String DATA_FILE_PATH = "Duke.txt";
    public static final String SAVED_DEADLINE_SEPARATOR = "(by";
    public static final String SAVED_EVENT_SEPARATOR = "(at";

    /**
     * Saves the list of task in a readable format as .txt
     *
     * If IOException is caught, the save process has failed.
     * It will print a corresponding error message to the exception caught.
     */
    public static void saveData() {
        System.out.println(Ui.DIVIDER);
        try {
            File dataFile = new File(DATA_FILE_PATH);
            dataFile.createNewFile();
            FileOutputStream oFile = new FileOutputStream(dataFile, false);
            FileWriter dataWriter =  new FileWriter(dataFile);
            for (Task task : TaskManager.tasks) {
                dataWriter.write(task.toString() + System.lineSeparator());
            }
            dataWriter.close();
            System.out.println(Ui.SAVE_DATA_MESSAGE);
        } catch (IOException e) {
            System.out.println(Ui.SAVE_FAILED_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Load the task status stored to the task list.
     *
     * @param taskIndex the index of a task
     * @param status the status of a task such as done or not done
     */
    public static void loadDataStatus(int taskIndex, char status) {
        if (status == 'X') {
            TaskManager.tasks.get(taskIndex).markAsDone();
        }
    }

    /**
     * Load task data into the task list.
     *
     * If the format of the data is wrong, DukeException will be thrown.
     * If RuntimeException is caught, InitFailedMessage will be shown.
     * It will print a corresponding error message to the exception caught.
     * FileNotFoundException will be ignored.
     */
    public static void loadData() {
        try {
            File dataFile = new File(DATA_FILE_PATH);
            Scanner dataReader = new Scanner(dataFile);
            while (dataReader.hasNextLine()) {
                String data = dataReader.nextLine();
                String taskType = Parser.parseSavedTaskType(data);
                char taskStatus = data.charAt(4);
                String taskDescription;
                String dueDate;
                switch (taskType) {
                case "T":
                    taskDescription = Parser.parseSavedTaskDescription(data, null, taskType);
                    TaskManager.tasks.add(new ToDo(taskDescription));
                    break;
                case "D":
                    taskDescription = Parser.parseSavedTaskDescription(data, SAVED_DEADLINE_SEPARATOR, taskType);
                    dueDate = Parser.parseSavedTaskDate(data, SAVED_DEADLINE_SEPARATOR);
                    TaskManager.tasks.add(new Deadline(taskDescription, dueDate));
                    break;
                case "E":
                    taskDescription = Parser.parseSavedTaskDescription(data, SAVED_EVENT_SEPARATOR, taskType);
                    dueDate = Parser.parseSavedTaskDate(data, SAVED_EVENT_SEPARATOR);
                    TaskManager.tasks.add(new Event(taskDescription, dueDate));
                    break;
                default:
                    throw new DukeException(Ui.LOAD_FAILED_MESSAGE);
                }
                loadDataStatus(TaskManager.tasksCount, taskStatus);
                TaskManager.tasksCount++;
            }
            TaskManager.listTasks();
        } catch (FileNotFoundException e) {
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            Ui.showInitFailedMessage();
        }
    }
}
