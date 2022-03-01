import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Storage {

    private static final String FILE_NAME = "tasks.txt";

    /**
     * Writes to a file the current representation of tasks in the same format as `list` prompt.
     *
     * @param taskList, an ArrayList of current tasks.
     */
    public static void writeToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            for (Task task : taskList) {
                fileWriter.write(task.toString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }
    }

    /**
     * Reads in a file "tasks.txt" line by line and returns each line as a String in an ArrayList for further processing
     *
     * @return ArrayList<String>
     */
    public static ArrayList<String> readFromFile() {
        ArrayList<String> oldList = new ArrayList<>();
        try {
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (!line.equals("")) {
                    oldList.add(line);
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found!");
            return oldList;
        }
        return oldList;
    }

    static void loadPastTasks(Parser parser, Storage storage, TaskList taskList) {
        ArrayList<String> oldTaskStrings = storage.readFromFile();
        for (String taskString : oldTaskStrings) {

            ArrayList<String> splitString = parser.parseSavedString(taskString);
            if (splitString.size() == 0) {
                continue;
            }
            switch (splitString.get(0)) {
            case "T":
                taskList.addToDo(splitString.get(2), Boolean.parseBoolean(splitString.get(1)));
                break;
            case "D":
                taskList.addDeadline(splitString.get(2), Boolean.parseBoolean(splitString.get(1)), splitString.get(3));
                break;
            case "E":
                taskList.addEvent(splitString.get(2), Boolean.parseBoolean(splitString.get(1)), splitString.get(3));
                break;
            default:
                System.out.println("not an acceptable task string!" + System.lineSeparator() + taskString);
            }
        }
    }
}
