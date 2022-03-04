package duke.storage;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    /**
     * Creates a storage to read and write file from filepath
     *
     * @param filePath path of input/output file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads input and returns it as a task list
     * @return TaskList from input file
     * @throws DukeException If no input file is found
     */
    public ArrayList<Task> readFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File input = new File(filePath);
        try {
            //unused boolean
            boolean hasCreatedFile = input.createNewFile();
            Scanner s = new Scanner(input);
            while (s.hasNext()) {
                tasks.add(Parser.parseToTask(s.nextLine()));
            }
            return tasks;
        }
        catch (IOException e) {
            String message = "Issue at file creation!";
            throw new DukeException(message);
        }

    }

    /**
     * Saves task list into a ile
     * @param textToAdd text to be written
     * @throws DukeException if file not found
     */
    public void writeFile(String textToAdd) throws DukeException {
        try {
            FileWriter output = new FileWriter(this.filePath);
            output.write(textToAdd);
            output.close();
        }
        catch (IOException e) {
            String message = "File writing issue";
            throw new DukeException(message);
        }

    }
}
