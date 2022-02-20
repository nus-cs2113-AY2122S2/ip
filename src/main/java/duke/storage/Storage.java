package duke.storage;

import duke.TaskList;
import duke.command.Command;
import duke.exception.AdditionalException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException, AdditionalException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        readFromFile(listOfTasks);
        return listOfTasks;
    }

    private void readFromFile(ArrayList<Task> listOfTasks) throws FileNotFoundException, AdditionalException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            Command c = Parser.parseFromFile(nextLine);
            c.executeFromFile(listOfTasks);
            executeIfMarked(nextLine, listOfTasks);
        }
    }

    private void executeIfMarked(String nextLine, ArrayList<Task> listOfTasks) throws AdditionalException {
        String[] words = nextLine.split("\\|");
        String toMark = words[1].toLowerCase();
        if (toMark.equalsIgnoreCase("true")) {
            String fullCommand = "mark " + Integer.toString(listOfTasks.size());
            Command c = Parser.parse(fullCommand);
            c.executeFromFile(listOfTasks);
        }
    }

    public void save(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        writeDataToFile(task, task.getTypeOfTask(), fileWriter);
        fileWriter.close();
    }

    public void saveAll(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        saveAllTasksToFile(fileWriter, tasks);
        fileWriter.close();
    }

    private void saveAllTasksToFile(FileWriter fileWriter, TaskList tasks) throws IOException {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            writeDataToFile(task, task.getTypeOfTask(), fileWriter);
        }
    }

    private void writeDataToFile(Task task, String typeOfTask, FileWriter fileWriter) throws IOException {
        switch(typeOfTask) {
        case "todo":
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() + "\n");
            break;
        case "deadline":
            Deadline deadline = (Deadline) task;
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() +
                    "|/by " + deadline.getDate() + "\n");
            break;
        case "event":
            Event event = (Event) task;
            fileWriter.write(typeOfTask + "|" + task.isDone() + "|" + task.getDescription() +
                    "|/at " + event.getLocation() + "|/on " + event.getDate() + "\n");
            break;
        default:
            System.out.println("Error writing data to file");
        }
    }

}
