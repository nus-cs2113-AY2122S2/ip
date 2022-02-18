package duke;

import duke.commands.Command;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Handler {

    private TaskList<Task> taskList;

    /**
     * Initialises a Handler instance with an empty lists of task
     * Functions as a singleton class (without the appropriate code), instantiate only one
     */
    public Handler() {
        this.taskList = new TaskList<Task>();
    }

    /**
     * Execute a command based on user input. Passes off control to relevant functions
     *
     * @param userCommand the user command to run
     */
    public void executeGivenCommand(Command userCommand) {
        userCommand.setTaskList(this.taskList);
        userCommand.execute();
        if (taskList.getListHasChanged()) {
            boolean successfulWrite = writeListToFile();
            boolean isListDifferent = !successfulWrite;
            taskList.setListHasChanged(isListDifferent);
        }
    }

    private boolean writeListToFile() {
        //Create the directory
        File directory = new File("./data");
        if (!directory.mkdir()) {
            return false;
            //should throw exception here maybe??
        }
        File fileToWriteTo = new File("./data/duke.txt");
        try {
            fileToWriteTo.createNewFile();
        } catch (IOException e) {
            System.out.println("IO Error occured! No changes were made to duke.txt");
            System.out.println(e.getMessage());
            return false;
        }
        try {
            //overwrite data
            FileWriter writer = new FileWriter(fileToWriteTo, false);
            for (int i = 0; i<this.taskList.size(); i++) {
                Queue<String> infoToWrite = new LinkedList<String>();
                Task task = this.taskList.get(i);
                task.getFileWriterFormatString(infoToWrite);
                String stringToWrite = infoToWrite.poll();
                // no information to write to begin with
                if (stringToWrite == null) {
                    continue;
                }
                while (infoToWrite.peek() != null) {
                    //delimiter
                    stringToWrite += " | ";
                    String infoBit= infoToWrite.poll();
                    stringToWrite += infoBit;
                }
                writer.write(stringToWrite+"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IO Error occured! Incomplete changes/no changes may have been made to duke.txt");
            return false;
        }
        return true;
    }
}
