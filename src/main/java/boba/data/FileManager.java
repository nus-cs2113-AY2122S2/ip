package boba.data;

import boba.response.BobaResponse;
import boba.task.Deadline;
import boba.task.Event;
import boba.task.Task;
import boba.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    private String filePath;

    public FileManager(String path) {
        filePath = path;
    }

    public ArrayList<Task> readFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(filePath));
            String line = input.readLine();
            while (line != null) {
                taskList.add(parseLine(line));
                line = input.readLine();
            }
            input.close();
        } catch (IOException e) {
            BobaResponse.printThis("Oh no! Looks like your save file was corrupted");
        }
        return taskList;
    }

    private Task parseLine(String line) {
        String[] tokens = line.split(":");
        Task task;
        if (tokens[0].equals("T")) {
            task = new Todo(tokens[2]);
        } else if (tokens[0].equals("D")) {
            task = new Deadline(tokens[2], tokens[3]);
        } else if (tokens[0].equals("E")) {
            task = new Event(tokens[2], tokens[3]);
        } else {
            task = new Task(tokens[2]);
        }
        if (tokens[1].equals("X")) {
            task.markAsDone();
        }
        return task;
    }

    public void writeFile(ArrayList<Task> taskList) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                String description = task.getDescription();
                String mark = (task.isDone()) ? "X" : "O";
                if (task instanceof Todo) {
                    output.write("T:" + mark + ":" + description);
                } else if (task instanceof Deadline) {
                    String by = ((Deadline) task).getBy();
                    output.write("D:" + mark + ":" + description + ":" + by);
                } else {
                    String at = ((Event) task).getAt();
                    output.write("E:" + mark + ":" + description + ":" + at);
                }
                output.write("\n");
            }
            output.close();
        } catch (IOException e) {
            BobaResponse.printThis("Yikes! Something went wrong while saving the file");
        }
    }

}
