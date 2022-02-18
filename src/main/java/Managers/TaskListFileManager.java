package Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Components.Deadline;
import Components.Event;
import Components.Task;
import Components.Todo;

public class TaskListFileManager {
    private static final String DIR_PATH = "./data";
    private static final String FILE_PATH = "./data/tasklist.txt";

    private static Task taskStringToTask(String taskString) {
        String[] taskStringSplit = taskString.split("] ", 2);
        char taskType = taskStringSplit[0].charAt(1);
        boolean isDone = taskStringSplit[0].charAt(4) == 'X';

        switch (taskType) {
        case 'T':
            Todo todo = new Todo(taskStringSplit[1]);
            todo.setIsDone(isDone);
            return todo;
        case 'E':
            String[] eventDescSplit = taskStringSplit[1].split(" \\(at: ");
            eventDescSplit[1] = eventDescSplit[1].substring(0,eventDescSplit[1].length()-1);
            Event event = new Event(eventDescSplit[0], eventDescSplit[1]);
            event.setIsDone(isDone);
            return event;
        case 'D':
            String[] deadlineDescSplit = taskStringSplit[1].split(" \\(by: ");
            deadlineDescSplit[1] = deadlineDescSplit[1].substring(0,deadlineDescSplit[1].length()-1);
            Deadline deadline = new Deadline(deadlineDescSplit[0], deadlineDescSplit[1]);
            deadline.setIsDone(isDone);
            return deadline;
        default:
            return null;
        }
    }

    static int loadTasklist(ArrayList<Task> tasks) throws IOException {
        try {
            File dataDir = new File(DIR_PATH);
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }

            File tasklistFile = new File(FILE_PATH);
            if (!tasklistFile.exists()) {
                tasklistFile.createNewFile();
            }

            Scanner s = new Scanner(tasklistFile);

            while (s.hasNext()) {
                String taskString = s.nextLine();
                tasks.add(taskStringToTask(taskString));
            }

            return tasks.size();
        } catch (IOException e) {
            throw e;
        }
    }

    static void saveTasklist(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write("");
            fw.close();
            fw = new FileWriter(FILE_PATH,true);

            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }
}
