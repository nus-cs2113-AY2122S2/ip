package main.java.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;
import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.Duke;
import main.java.duke.exception.DukeException;

public class Storage {

    private static String DATA_DIRECTORY = System.getProperty("user.dir") + "/data";
    private static String DATA_FILE = DATA_DIRECTORY + "/duke.txt";
    private static Path dataDirectoryPath = Paths.get(DATA_DIRECTORY);

    public static void load() throws IOException, DukeException {
        boolean directoryExists = new File(DATA_DIRECTORY).exists();
        boolean fileExists = new File(DATA_FILE).exists();
        File dataFile = new File(DATA_FILE);
        if (!directoryExists) {
            Files.createDirectory(dataDirectoryPath);
        }
        if (!fileExists) {
            dataFile.createNewFile();
        }
        Scanner s = new Scanner(dataFile);
        while (s.hasNext()) {
            parseData(s.nextLine());
        }
        Duke.taskCounter = Duke.tasks.size();
    }

    private static void parseData(String data) throws DukeException {
        String[] dataArr = data.split("\\|");
        Task newTask;
        if (dataArr[1].equals("T")) {
            newTask = new ToDo(dataArr[3]);
            if (Integer.parseInt(dataArr[2]) == 1) {
                newTask.setDone(true);
            }
        } else if (dataArr[1].equals("D")) {
            newTask = new Deadline(dataArr[3], dataArr[4], dataArr[5]);
            if (Integer.parseInt(dataArr[2]) == 1) {
                newTask.setDone(true);
            }
        } else if (dataArr[1].equals("E")) {
            newTask = new Event(dataArr[3], dataArr[4], dataArr[5], dataArr[6], dataArr[7]);
            if (Integer.parseInt(dataArr[2]) == 1) {
                newTask.setDone(true);
            }
        } else {
            throw new DukeException("ERROR IN DATA FILE");
        }
        Duke.tasks.add(newTask);
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE, false);
        int taskNum = 0;
        for (Task task : Duke.tasks) {
            taskNum++;
            fw.write(convertTask(taskNum, task));
        }
        fw.close();
    }

    // num|type|T/F|description|(date)
    private static String convertTask(int taskNum, Task task) {
        String line = String.valueOf(taskNum) + "|" + task.getType() + "|";
        if (task.isDone()) {
            line += "1|";
        } else {
            line += "0|";
        }
        line += task.getDescription();
        if (task.toString().contains("(")) {
            line += "|" + task.getDateTime();
        }
        line += System.lineSeparator();
        return line;
    }
}