package duke;

import duke.task.DynamicTask;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private Ui ui = new Ui();

    public void createDirectory() {
        String directory = "\\data";
        String directoryPath = Duke.PROJECT_PATH + directory;
        File dir = new File(directoryPath);
        boolean isMade = dir.mkdir();
        if (!isMade) {
            ui.printMkDirError();
        }
    }

    public void createFile() {
        File dir = new File("data");
        // attempt to create directory if it does not exist.
        while (!dir.exists()) {
            createDirectory();
        }
        File f = new File(Duke.PROJECT_PATH + "\\" + Duke.DATAFILE_RELATIVE_PATH);

        try {
            f.createNewFile();
        } catch (IOException e) {
            ui.printMkFileError();
        }
    }

    public String getTaskInFileFormat(TaskList taskList, int listIndex) {
        Task task = taskList.get(listIndex);
        if (task.getTaskTypeSymbol() == "T") {
            return task.getTaskTypeSymbol() + "/" + task.isDone() + "/" + task.getDescription();
        }
        DynamicTask dTask = (DynamicTask) task;
        return dTask.getTaskTypeSymbol() + "/" + dTask.isDone() + "/" + dTask.getDescription()
                + "/" + dTask.getTime();
    }

    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fWrite = new FileWriter(Duke.DATAFILE_RELATIVE_PATH);
        if (taskList.getTaskCount() == 0) {
            fWrite.write("");
            fWrite.close();
        } else {
            fWrite.write(getTaskInFileFormat(taskList,0) + "\n");
            fWrite.close();
        }
        fWrite = new FileWriter(Duke.DATAFILE_RELATIVE_PATH, true);
        for (int i = 1; i < taskList.getTaskCount(); i++) {
            fWrite.write(getTaskInFileFormat(taskList, i) + "\n");
        }
        fWrite.close();
    }

    public void saveData(TaskList taskList) {
        File f = new File(Duke.DATAFILE_RELATIVE_PATH);
        // attempt to create file if it does not exist.
        while (!f.exists()) {
            createFile();
        }
        try {
            writeToFile(taskList);
        } catch (IOException e) {
            ui.printWrFileError();
        }
    }

    public void transferDataFromFileToList(TaskList taskList) throws FileNotFoundException {
        File f = new File(Duke.DATAFILE_RELATIVE_PATH);
        Scanner fileReader = new Scanner(f);
        while (fileReader.hasNext()) {
            String fileRow = fileReader.nextLine();
            String[] taskArguments = fileRow.split("/", 5);
            if (taskArguments[0].equals("T")) {
                Duke.addTodoToList(taskArguments[2], false);
            } else if (taskArguments[0].equals("D")) {
                Duke.addDeadlineToList(taskArguments[2], taskArguments[3], false);
            } else if (taskArguments[0].equals("E")) {
                Duke.addEventToList(taskArguments[2], taskArguments[3], false);
            } else {
                ui.printCorruptedFileError();
                continue;
            }

            // if task being loaded is done, mark as done.
            if (taskArguments[1].equals("true")) {
                taskList.get(taskList.getTaskCount() - 1).markAsDone();
            }
        }
        ui.printNumOfLoadedTasks(taskList);
    }

    public void loadData(TaskList taskList) {
        File f = new File(Duke.DATAFILE_RELATIVE_PATH);
        try {
            transferDataFromFileToList(taskList);
        } catch (FileNotFoundException e) {
            // attempt to create file till it is created.
            while (!f.exists()) {
                createFile();
            }
        }
    }
}
