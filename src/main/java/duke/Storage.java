package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String folderPath;
    String fileName;

    public Storage(String filePath) {
        this.folderPath = filePath.split("/")[0];
        this.fileName=  filePath.split("/")[1];
    }

    public ArrayList<Task> load() throws DukeException {
        // Create new TaskList
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            File f = new File(folderPath + "/" + fileName);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String userInput = s.nextLine();
                String[] userInputArr = userInput.split(" \\| ");
                String taskType = userInputArr[0];
                boolean isDone = userInputArr[1].equals("1");

                switch (taskType) {
                case "T":
                    tasks.add(new Todo(isDone, userInputArr[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(isDone, userInputArr[2], userInputArr[3]));
                    break;
                case "E":
                    tasks.add(new Event(isDone, userInputArr[2], userInputArr[3]));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            File directory = new File(folderPath);
            // Create directory if not found
            if (!directory.exists()) {
                directory.mkdir();
            }

            File f = new File(folderPath + "/" + fileName);
            // Create file if not found. If IOError, print error message
            try {
                f.createNewFile();
            } catch (IOException err) {
                System.out.println(err);
            }
        }

        return tasks;
    }

    // Write tasks to file
    public void writeTasksToStorage(TaskList tasks) {
        try {
            String output = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                output += tasks.getTask(i).saveString() + System.lineSeparator();
            }
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(output);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}
