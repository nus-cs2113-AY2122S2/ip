package duke.storage;
import duke.exceptions.DukeException;
import duke.messages.Messages;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import duke.taskList.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

public class Storage {
    private final Ui ui = new Ui();
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<Task>();

        try {
            File f = new File(filePath);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                Parser parser = new Parser(input);
                Task task = parser.getTaskFromLocalFile();
                //System.out.println(task);
                taskList.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            File f = new File(filePath);
            f.createNewFile();
        }
        Ui.printFileContents(filePath);
        return taskList;
    }

    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter("duke.txt");
            for (int i = 0; i < taskList.getSize(); i++) {
                fw.write(taskList.getTask(i).toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {

        }
    }


}