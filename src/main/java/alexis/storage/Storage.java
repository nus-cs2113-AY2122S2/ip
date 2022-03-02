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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import static alexis.parser.Parser.parseTiming;

/**
 * Loads task.txt file into program's task list and saves program's task list into task.txt file.
 */
public class Storage {
    protected String dataPath;
    protected File dataFile;

    /**
     * Creates a new data/ directory and creates a new file tasks.txt in it.
     *
     * @param filepath ./data/tasks.txt
     */
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

        // creates new file task.txt
        if (dataFile.exists()){
            System.out.println(dataFile.getName() + " file exists");
        } else {
            try {
                Files.createFile(Path.of(dataPath));
                System.out.println("File created: " + dataFile.getName());
            } catch (IOException e) {
                System.out.println("An error occurred, file still doesnt exist");
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads the dataFile to create an ArrayList of strings corresponding to the lines in the dataFile.
     *
     * @return ArrayList of strings from the dataFile
     * @throws AlexisException If dataFile does not exist in user's pc
     * @throws IOException If program is interrupted unexpectedly
     */
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

    /**
     * When the Alexis program is first started, if ./data/tasks.txt file is present,
     * reads from the file and stores info into a new task list.
     * If ./data/tasks.txt file is not present, throws an exception.
     *
     * @return Filled up taskList
     * @throws AlexisException If dataFile does not exist in user's pc
     * @throws IOException If program is interrupted unexpectedly
     */
    public ArrayList<Task> load() throws AlexisException, IOException {
        ArrayList<Task> taskList = new ArrayList<>(100);
        try {
            ArrayList<String> dataItems = readFile();
            taskList = parse(dataItems);
        } catch (IOException | DateTimeException | AlexisException e) {
            System.out.println("file is corrupted.. deleting all content and creating new file..");
            new FileWriter("./data/tasks.txt", false).close();  // deletes all content in file
        }
        return taskList;
    }

    /**
     * Parses the ArrayList of strings containing the encoded tasks based on the type of tasks they are.
     * Returns the decoded list.
     *
     * @param encodedList ArrayList of strings
     * @return Decoded list which is an ArrayList of Tasks
     * @throws DateTimeException If saved date is not an instance of Date
     */
    private ArrayList<Task> parse(ArrayList<String> encodedList) throws DateTimeException {
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
                decodedList.add(new Deadline(deadlineDescriptionSplitArr[0],
                        parseTiming(LocalDate.parse(deadlineDescriptionSplitArr[1]))));
                decodedList.get(taskCounter).addDate(LocalDate.parse(deadlineDescriptionSplitArr[1]));
                taskCounter += 1;
                break;
            case 'E':
                String[] eventDescriptionSplitArr = taskDescription.split(" /at ");
                decodedList.add(new Event(eventDescriptionSplitArr[0],
                        parseTiming(LocalDate.parse(eventDescriptionSplitArr[1]))));
                decodedList.get(taskCounter).addDate(LocalDate.parse(eventDescriptionSplitArr[1]));
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

    /**
     * Parses the task list and saves it into the ./data/tasks.txt file in an appropriate format.
     *
     * @param numOfTasks Number of tasks in task list
     * @param tasks ArrayList of the tasks
     */
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
                            + tasks.get(i).getDescription() + " /by " + tasks.get(i).getDate()
                            + System.lineSeparator());
                    break;
                case 'E':
                    fw.write(tasks.get(i).typeOfTask() + " | " + tasks.get(i).getStatusIcon() + " | "
                            + tasks.get(i).getDescription() + " /at " + tasks.get(i).getDate()
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
