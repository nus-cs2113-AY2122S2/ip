package duke;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Represents the storage component of the program.
 * The storage object handles all the storage and retrieval operations to and from a file for the program.
 */
public class Storage {
    private String filePath;
    private Ui ui;
    private final String TASK_DONE = "1";

    public Storage(String filePath, Ui ui) {
        setFilePath(filePath);
        setUi(ui);
    }

    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private void setUi(Ui ui) {
        this.ui = ui;
    }

    private void createFile(File f) throws DukeException {
        File directory = f.getParentFile();
        boolean isDirectoryCreated;
        if (!directory.exists()) {
            isDirectoryCreated = directory.mkdirs();
            if (!isDirectoryCreated) {
                throw new DukeException(DukeExceptionCause.FolderCreationFail);
            }
        }
        try {
            f.createNewFile();
        } catch (IOException io) {
            throw new DukeException(DukeExceptionCause.FileCreationFail);
        }
    }

    private Event extractEventFromFile(StringTokenizer st) {
        Event newEventTask;
        boolean isDone;
        String taskName;
        String time;
        if (st.nextToken().equals(TASK_DONE)) {
            isDone = true;
        } else {
            isDone = false;
        }
        taskName = st.nextToken();
        time = st.nextToken();
        newEventTask = new Event(taskName, time);
        newEventTask.setDone(isDone);
        return newEventTask;
    }

    private Todo extractToDoFromFile(StringTokenizer st) {
        Todo newToDoTask;
        boolean isDone;
        String taskName;
        if (st.nextToken().equals(TASK_DONE)) {
            isDone = true;
        } else {
            isDone = false;
        }
        taskName = st.nextToken();
        newToDoTask = new Todo(taskName);
        newToDoTask.setDone(isDone);
        return newToDoTask;
    }

    private Deadline extractDeadlineFromFile(StringTokenizer st) {
        Deadline newDeadlineTask;
        boolean isDone;
        String taskName;
        String by;
        if (st.nextToken().equals(TASK_DONE)) {
            isDone = true;
        } else {
            isDone = false;
        }
        taskName = st.nextToken();
        by = st.nextToken();
        newDeadlineTask = new Deadline(taskName, by);
        newDeadlineTask.setDone(isDone);
        return newDeadlineTask;
    }

    /**
     * Returns an ArrayList of Tasks whose contents were loaded from a file containing tasks.
     *
     * @return An ArrayList of Tasks.
     * @throws DukeException If the file to load the ArrayList from is not found and
     *                       the program is unable to create the file or the folder the file is supposed to reside in.
     */
    public ArrayList<Task> loadArrayListFromFile() throws DukeException {
        File f = new File(filePath);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String fileData = s.nextLine();
                StringTokenizer st = new StringTokenizer(fileData, "|");
                String taskType = st.nextToken();
                Task newTask;
                switch (taskType) {
                case "E":
                    newTask = extractEventFromFile(st);
                    break;
                case "T":
                    newTask = extractToDoFromFile(st);
                    break;
                case "D":
                    newTask = extractDeadlineFromFile(st);
                    break;
                default:
                    ui.showInvalidTaskTypeMessage();
                    continue;
                }
                listOfTasks.add(newTask);
            }
        } catch (FileNotFoundException fe) {
            try {
                createFile(f);
            } catch (DukeException DE) {
                throw DE;
            }
        }
        return listOfTasks;
    }

    private void clearFileContents() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.close();
    }

    /**
     * Writes the contents of the ArrayList of Tasks into a file.
     *
     * @param listOfTasks An ArrayList containing the list of tasks which would be written to the file.
     * @param ui          The UserInterface object which outputs error messages to the user.
     * @throws IOException If the program is unable to write to the file.
     */
    public void writeArrayListToFile(ArrayList<Task> listOfTasks, Ui ui) throws IOException {
        clearFileContents();
        FileWriter fw;
        fw = new FileWriter(filePath, true);
        String taskDetails;
        for (int i = 0; i < listOfTasks.size(); i++) {
            Character isDoneSymbol;
            if (listOfTasks.get(i).getDone()) {
                isDoneSymbol = '1';
            } else {
                isDoneSymbol = '0';
            }
            if (listOfTasks.get(i) instanceof Event) {
                Event event = (Event) listOfTasks.get(i);
                taskDetails = "E|" + isDoneSymbol + "|" + event.getTaskName() + "|" + event.getTime();
            } else if (listOfTasks.get(i) instanceof Deadline) {
                Deadline deadline = (Deadline) listOfTasks.get(i);
                taskDetails = "D|" + isDoneSymbol + "|" + deadline.getTaskName() + "|" + deadline.getBy();
            } else if (listOfTasks.get(i) instanceof Todo) {
                Todo todoTask = (Todo) listOfTasks.get(i);
                taskDetails = "T|" + isDoneSymbol + "|" + todoTask.getTaskName();
            } else {
                ui.showInvalidTaskTypeMessage();
                continue;
            }
            if (i != listOfTasks.size() - 1) {
                fw.write(taskDetails + System.lineSeparator());
            } else {
                fw.write(taskDetails);
            }
        }
        fw.close();
    }


}
