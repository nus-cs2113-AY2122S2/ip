package duke;

//@@author quitejasper-reused
//Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/TaskFileManager.java
//with minor modifications

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskFileManager {
    public void loadTaskList(String fileName, ArrayList<Task> taskList) throws IOException {
        File file = getFile(fileName);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split("\\|");
            boolean isCompleted = splitData[1].trim().equals("1");
            Task task = null;
            switch (splitData[0].trim()) {
            case "T":
                task = new Todo(splitData[2].trim());
                break;
            case "E":
                task = new Event(splitData[2].trim(), splitData[3].trim());
                break;
            case "D":
                task = new Deadline(splitData[2].trim(), splitData[3].trim());
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
    }

    private File getFile(String fileName) throws IOException {
        File file = new File("data" + File.separator + fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

    public void saveTaskList(String fileName, ArrayList<Task> taskList) throws IOException {
        File file = getFile(fileName);
        FileWriter fileWriter = new FileWriter("data/" + fileName);
        for (Task a : taskList) {
            fileWriter.write(a.toFileString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
//@@author
