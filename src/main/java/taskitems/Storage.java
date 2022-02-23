package taskitems;


import taskitems.task.Deadline;
import taskitems.task.Task;
import taskitems.task.TaskList;
import taskitems.task.Todo;
import taskitems.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String PATH = "data.txt";
    private TaskList tasks;
    private Greet greet;

    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    public void saveData() {
        File data = new File(PATH);
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to save data...");
            }
        }
        try {
            FileWriter dataWrite = new FileWriter(PATH,false);
            for(Task a : tasks.taskList) {
                dataWrite.write(a.saveString() + "\n");
            }
            dataWrite.close();
        } catch (IOException e) {
            System.out.println("Unable to save data...");
        }
    }

    public void loadData() throws FileNotFoundException {
        System.out.println("***Please wait while I fetch past data...***");
        File data = new File(PATH);
        Scanner reader = new Scanner(data);
        int count = 1;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            String[] dataLine = line.split(",");
            boolean hasFailed = false;
            switch (dataLine[0]) {
            case "T":
                tasks.add(new Todo(dataLine[1]),true);
                break;
            case "D":
                tasks.add(new Deadline(dataLine[1],dataLine[3]) ,true);
                break;
            case "E":
                tasks.add(new Event(dataLine[1],dataLine[3]),true);
                break;
            default:
                System.out.println("Line " + count + " is corrupted,");
                hasFailed = true;
                break;
            }
            if (!hasFailed && dataLine[2].equals("1")) {
                tasks.mark(tasks.size);
            }
            count++;
        }
        if (tasks.size == 0) {
            System.out.println("No saved data found");
        } else {
            System.out.println("***" + tasks.size + " out of " + (count-1) +
                    " lines of data are valid and fetched accurately." + "***\n");
        }
    }
}
