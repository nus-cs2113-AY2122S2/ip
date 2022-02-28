package util.DukeClasses;

import util.miscellaneous.Chatbot;
import util.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static util.DukeClasses.DukeUI.loadAndRun;

public class DukeStorage implements Chatbot {
    public static void checkFilePath() throws IOException {
        File file = new File(FILEPATH);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    public static void saveToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(FILEPATH);
        for (int i = 0; i < tasks.size(); i++) {
            writer.write((tasks.get(i).toString()));
            writer.write(System.lineSeparator());
        }
        writer.close();
    }

    public static void saveData(ArrayList<Task> tasks) {
        try {
            checkFilePath();
            saveToFile(tasks);
        } catch (IOException e) {
            DukePrinter.echo(IO_ERROR_MSG);
        }
    }

    public static void loadData(DukeTaskList tasks) {
        try {
            File file = new File(FILEPATH);
            Scanner input1 = new Scanner(file);
            String load;

            while (input1.hasNext()) {
                load = input1.nextLine();
                boolean needUpdateTaskStatus = false;
                boolean isLoadingData = true;

                if(load.indexOf(DONE_STATUS) == TASK_STATUS_INDEX) {
                    needUpdateTaskStatus = true;
                }

                if (load.startsWith(DEADLINE_TYPE)) {
                    String time = load.substring(load.lastIndexOf("(by: "));
                    load = ADD_DEADLINE_CMD + load.substring(TASK_DETAIL, load.indexOf("by ")) + DEADLINE_OF_TASK_CMD + " " + time;
                } else if (load.startsWith(EVENT_TYPE)) {
                    String time = load.substring(load.lastIndexOf("(by: "));
                    load = ADD_EVENT_CMD + load.substring(TASK_DETAIL, load.indexOf("by ")) + DURATION_OF_EVENT_CMD + " " + time;
                } else {
                    load = ADD_TODO_CMD + load.substring(TASK_DETAIL);
                }

                loadAndRun(tasks, load, isLoadingData, needUpdateTaskStatus);
            }
        } catch (FileNotFoundException e) {
            DukePrinter.echo(NO_PREVIOUS_RECORD);
        }
    }
}
