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

/**
 * Class for the Storage object. It contains methods to read from a data file, and to write onto
 * it after Duke has ended.
 * 
 * @throws IOException If an input or output exception occured.
 */ 

public class Storage {

    private static String DATA_DIRECTORY = System.getProperty("user.dir") + "/data";
    private static String DATA_FILE = DATA_DIRECTORY + "/duke.txt";
    private static Path dataDirectoryPath = Paths.get(DATA_DIRECTORY);

    /**
     * Method used to initiate read from data/duke.txt, and to retrieve when Duke is started.
     * 
     * @throws IOException If an input or output exception occured.
     * @throws DukeException If data file is corrupted or contains unexpected information.
     */
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
            readData(s.nextLine());
        }
        Duke.taskCounter = Duke.tasks.size();
    }

    /**
     * Method that reads lines in duke.txt and adds them to the task list.
     * 
     * @param data Input line from duke.txt.
     * @throws DukeException If task type is of an unexpected value.
     */
    private static void readData(String data) throws DukeException {
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

    /**
     * Method that is used to initiate the writing to data/duke.txt before closing Duke.
     * 
     * @throws IOException If an input or output exception occured.
     */
    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE, false);
        int taskNum = 0;
        for (Task task : Duke.tasks) {
            taskNum++;
            fw.write(convertTask(taskNum, task));
        }
        fw.close();
    }

    /**
     * Method that converts a task into a String that can be readable for future use.
     * 
     * @param taskNum The number of the task on the list.
     * @param task The task that is being converted.
     * @return A string which is written onto data/duke.txt.
     */
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