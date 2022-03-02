package duke.storage;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads input and returns it as a task list
     * @return TaskList from input file
     * @throws IOException If no input file is found
     */
    public ArrayList<Task> readFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File input = new File(filePath);
        if (input.createNewFile()) {
            System.out.println("Create");
        }
        Scanner s = new Scanner(input);
        while (s.hasNext()) {
            tasks.add(Parser.parseToTask(s.nextLine()));
        }
        return tasks;
    }

    /**
     * Saves task list into a ile
     * @param textToAdd text to be written
     * @throws IOException if file not found
     */
    public void writeFile(String textToAdd) throws IOException {
        FileWriter output = new FileWriter(this.filePath);

        output.write(textToAdd);
        output.close();
    }
}
