package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;

import java.io.*;
import java.util.Scanner;

public class Storage {
    private static final String TASKLIST_FILE_PATH = "data.txt";


    public static void printLine() {
        System.out.println("-----------------------------------------");
    }

    /**
     * Create a file in local if it does not exist.
     * Read the data stored in the file.
     *
     * @param taskList the list to store all the tasks.
     * @throws IOException           If input and/or output file has an error.
     * @throws FileNotFoundException If new file cannot be created.
     */
    public static void checkFileExists(TaskList taskList) throws IOException {
        try {
            File f = new File(TASKLIST_FILE_PATH);
            if (!f.exists()) {
                FileOutputStream newFile = new FileOutputStream(TASKLIST_FILE_PATH);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create a new file.");
            printLine();
        }
        readFile(TASKLIST_FILE_PATH, taskList);
    }

    /**
     * Populate all the task details in the local file when the program start to
     * restore the data saved in the file.
     * Iterate each line. Convert the data in file to correct format,
     * and add to the taskList.
     *
     * @param filePath path of the local file.
     * @param taskList the list to store all the tasks
     * @throws FileNotFoundException If file does not exist.
     */
    public static void readFile(String filePath, TaskList taskList) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int separatorIndex;
        while (s.hasNextLine()) {
            String[] taskInfo = s.nextLine().split(",");
            String taskDetail = null;
            switch (taskInfo[0]) {
            case "T":
                taskDetail = taskInfo[2];
                taskList.readTask(new Todo(taskDetail));
                break;
            case "D":
                taskDetail = taskInfo[2] + " /by " + taskInfo[3];
                separatorIndex = taskDetail.indexOf("/by");
                taskList.readTask(new Deadline(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4)));
                break;
            case "E":
                taskDetail = taskInfo[2] + " /at " + taskInfo[3];
                separatorIndex = taskDetail.indexOf("/at");
                taskList.readTask(new Event(taskDetail.substring(0, separatorIndex - 1), taskDetail.substring(separatorIndex + 4)));
                break;
            }
            if (taskInfo[1].equals("1")) {
                taskList.readStatus(taskList.getSize());
            }
        }
    }

    /**
     * Convert the task to correct format in order the save in the file.
     *
     * @param task path of the local file.
     * @return the text in correct format to save in the file.
     */
    public static String formatTask(Task task) {
        String taskType = task.getType();
        int status = task.getStatus() ? 1 : 0;
        String taskDescription = task.getDescription();
        String taskDate = task.getDate();
        String textToWrite = null;
        switch (taskType) {
        case "T":
            textToWrite = "T," + status + "," + taskDescription;
            break;
        case "D":
            textToWrite = "D," + status + "," + taskDescription + "," + taskDate;
            break;
        case "E":
            textToWrite = "E," + status + "," + taskDescription + "," + taskDate;
            break;
        }
        return textToWrite;
    }

    /**
     * Write tasks to the file after a bye command is inputted.
     *
     * @param taskList the list to store all the tasks.
     */
    public static void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(TASKLIST_FILE_PATH);

            for (int i = 0; i < taskList.getSize(); i++) {
                fw.write(formatTask(taskList.getTask(i)) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Cannot find the file.");
        }
    }


}
