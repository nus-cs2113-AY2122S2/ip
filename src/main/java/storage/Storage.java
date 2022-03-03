package storage;

import duke.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/Duke.java
    //with minor modifications
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();

        File file = new File(filePath);

        Scanner scFile = new Scanner(file);

        while (scFile.hasNext()) {
            String fileLine = scFile.nextLine();
            String[] splittedFileLine = fileLine.split("\\|");
            boolean isCompleted = splittedFileLine[1].trim().equals("1");

            Task task = null;

            switch (splittedFileLine[0].trim()) {
            case "T":
                task = new Todo(splittedFileLine[2].trim());
                break;
            case "E":
                task = new Event(splittedFileLine[2].trim(), splittedFileLine[3].trim());
                break;
            case "D":
                task = new Deadline(splittedFileLine[2].trim(), splittedFileLine[3].trim());
                break;
            }

            if (task != null) {
                if (isCompleted) {
                    task.markAsDone();
                } //no need for else statement, as tasks are not done by default
                taskList.add(task);
            } else {
                System.out.println("--not valid--");
            }
        }
        return taskList;
    }

    public void updateAndSave(ArrayList<Task> taskList) {
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            for (Task a : taskList) {
                fileWriter.write(a.toFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }
    //@@author
}
