package alexis.storage;

import alexis.exceptions.AlexisException;
import alexis.task.Deadline;
import alexis.task.Event;
import alexis.task.Task;
import alexis.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    protected String dataPath;
    protected File dataFile;

    public Storage(String filepath) {
        dataPath = filepath;
        File dataFile = new File(filepath);
        this.dataFile = dataFile;

        // creates new data/ directory
        try {
            Files.createDirectories(Path.of("data/"));
        } catch (IOException e) {
            System.out.println("directory not created");
        }

        //creates new file task.txt
        if (dataFile.exists()){
            System.out.println(dataFile.getName() + " file exists");
        } else {
            try {
                Files.createFile(Path.of(dataPath));
                //dataFile.createNewFile();
                System.out.println("File created: " + dataFile.getName());
            } catch (IOException e) {
                System.out.println("An error occurred, file still doesnt exist");
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> readFile() throws AlexisException, IOException {
        if (!dataFile.exists()) {
            throw new AlexisException();
        }
        if (dataFile.length() == 0) {
            System.out.println(dataFile.getName() + " file is empty");
        } else {
            System.out.println("data loaded from " + dataFile.getName());
        }
        return (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
    }

    public ArrayList<Task> load() throws AlexisException{
        ArrayList<Task> taskList = null;
        try {
            ArrayList<String> dataItems = readFile();
            taskList = parse(dataItems);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taskList;
    }

    private ArrayList<Task> parse(ArrayList<String> encodedList) {
        ArrayList<Task> decodedList = new ArrayList<>();
        int taskCounter = 0;
        for (String encodedTask : encodedList) {
            char taskType = getTaskType(encodedTask);
            String taskDescription = getTaskDescription(encodedTask);
            switch(taskType) {
            case 'T':
                decodedList.add(new Todo(taskDescription));
                taskCounter += 1;
                break;
            case 'D':
                String[] deadlineDescriptionSplitArr = taskDescription.split(" /by ");
                decodedList.add(new Deadline(deadlineDescriptionSplitArr[0], deadlineDescriptionSplitArr[1]));
                taskCounter += 1;
                break;
            case 'E':
                String[] eventDescriptionSplitArr = taskDescription.split(" /at ");
                decodedList.add(new Event(eventDescriptionSplitArr[0], eventDescriptionSplitArr[1]));
                taskCounter += 1;
                break;
            default:
                System.out.println("Unknown task encountered. Skipping");
                break;
            }

            if (getTaskStatus(encodedTask) == 'X') {
                decodedList.get(taskCounter - 1).setIsDone();
            }
        }
        return decodedList;
    }

    private String getTaskDescription(String line) {
        return line.substring(8);
    }

    private char getTaskType(String line) {
        return line.charAt(0);
    }

    private char getTaskStatus(String line) {
        return line.charAt(4);
    }

    public void save(int numOfTasks, ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.dataPath);
            for (int i = 0; i < numOfTasks; i++) {
                switch (tasks.get(i).typeOfTask()) {
                case 'T':
                    fw.write(tasks.get(i).typeOfTask() + " | " + tasks.get(i).getStatusIcon() + " | "
                            + tasks.get(i).getFullDescription() + System.lineSeparator());
                    break;
                case 'D':
                    fw.write(tasks.get(i).typeOfTask() + " | " + tasks.get(i).getStatusIcon() + " | "
                            + tasks.get(i).getDescription() + " /by " + tasks.get(i).getTiming()
                            + System.lineSeparator());
                    break;
                case 'E':
                    fw.write(tasks.get(i).typeOfTask() + " | " + tasks.get(i).getStatusIcon() + " | "
                            + tasks.get(i).getDescription() + " /at " + tasks.get(i).getTiming()
                            + System.lineSeparator());
                    break;
                default:
                    System.out.println("Error in saving task..");
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to save tasks");
        }
    }
}
