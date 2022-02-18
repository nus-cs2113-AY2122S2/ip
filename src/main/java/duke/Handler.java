package duke;

import duke.commands.Command;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Handler {

    private TaskList<Task> taskList;

    /**
     * Initialises a Handler instance with an empty lists of task
     * Functions as a singleton class (without the appropriate code), instantiate only one
     */
    public Handler() {
        this.taskList = new TaskList<Task>();
        loadOnStartup();
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
        directory.mkdir();
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

    // todo: cleanup
    private void loadOnStartup() {
        // load data into taskList
        try {
            File dataFile = new File("./data/duke.txt");
            Scanner dataFileReader = new Scanner(dataFile);
            ArrayList<Task> bufferTaskList = new ArrayList<Task>();
            while (dataFileReader.hasNextLine()) {
                String data = dataFileReader.nextLine();
                String[] splitData = data.split(" \\| ");
                String taskType = splitData[0];
                Task taskToAdd;
                boolean isDone = splitData[1].equals("1") ? true : false;
                switch (taskType) {
                case "T":
                    taskToAdd = new Todo(splitData[2]);
                    taskToAdd.setIsDone(isDone);
                    break;
                case "E":
                    taskToAdd = new Event(splitData[2], splitData[3]);
                    taskToAdd.setIsDone(isDone);
                    break;
                case "D":
                    taskToAdd = new Deadline(splitData[2], splitData[3]);
                    taskToAdd.setIsDone(isDone);
                    break;
                default:
                    taskToAdd = null;
                    break;
                }
                bufferTaskList.add(taskToAdd);
            }
            taskList.addAll(bufferTaskList);
        } catch (FileNotFoundException e) {
            // no action needed
        }
    }
}
