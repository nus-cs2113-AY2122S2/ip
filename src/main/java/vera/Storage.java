package vera;

import vera.exception.InputEmptyException;
import vera.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static vera.constant.Messages.ERROR_IO_FAILURE_MESSAGE;
import static vera.constant.Messages.ERROR_CORRUPT_SAVED_FILE_MESSAGE;
import static vera.constant.Messages.ERROR_FILE_NOT_FOUND_MESSAGE;

public class Storage {
    private String saveFilePath;
    

    public Storage(String filePath) throws IOException {
        saveFilePath = filePath;
        System.out.println("Booting up...");
        File saveDirectory = new File("data");
        if (saveDirectory.mkdir()) {
            System.out.println("Creating save directory...");
        }
        File saveState = new File(filePath);
        if (saveState.createNewFile()) {
            System.out.println("Creating new save state...");
        }
    }

    private void wipeSavedData() {
        try {
            // Create new FileWriter to overwrite existing file. But
            // no new data is written to overwrite file content, so content remains empty, i.e. clears file content
            FileWriter fw = new FileWriter(saveFilePath);
            fw.close();
        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        }
    }

    public void appendToFile(String newTaskDescription,
                             String newTaskDate, String taskStatus,
                             String taskType) {
        try {
            FileWriter fw = new FileWriter(saveFilePath, true);
            String textToAppend = taskType + " | " + taskStatus + " | "
                    + newTaskDescription;
            if (!taskType.equals("T")) {
                textToAppend += " | " + newTaskDate;
            }

            fw.write(textToAppend + System.lineSeparator());
            fw.close();

        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
        }
    }

    public void rewriteSavedState(TaskList taskList) {
        wipeSavedData();
        ArrayList<Task> replicatedTasks = taskList.getTasks();
        String taskStatus = "0";
        for (Task task : replicatedTasks) {
            if (task.isDone()) {
                taskStatus = "1";
            }
            appendToFile(task.getDescription(), task.getDate(),
                    taskStatus, task.getType());
            taskStatus = "0";
        }
    }

    private ArrayList<Task> readSavedData() throws
            FileNotFoundException, ArrayIndexOutOfBoundsException, InputEmptyException{
        ArrayList<Task> decodedTasks = new ArrayList<>();
        File f = new File(saveFilePath);
        Scanner s = new Scanner(f);
        String[] taskRawData;
        Task taskParsedData;
        while (s.hasNext()) {
            taskRawData = s.nextLine().split(" \\| ");
            taskParsedData = Parser.parseSavedData(taskRawData);
            decodedTasks.add(taskParsedData);
        }
        return decodedTasks;
    }

    public ArrayList<Task> load() {
        try {
            return readSavedData();
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_FILE_NOT_FOUND_MESSAGE);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException | InputEmptyException e) {
            System.out.println(ERROR_CORRUPT_SAVED_FILE_MESSAGE);
            wipeSavedData();
        }
        return new ArrayList<>();
    }

}
