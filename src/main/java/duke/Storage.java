package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Deals with loading tasks from the local file and saving
 * tasks in the file
 */
public class Storage {

    private static File dataFile;
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_TODO = "todo";
    public static final char TYPE_EVENT = 'E';
    public static final char TYPE_DEADLINE = 'D';
    public static final char TYPE_TODO = 'T';
    private static String[] datesArray;
    private static String[] eventsArray;
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static Ui ui;
    private static Parser parser;
    private static int itemNumber;

    /**
     * Gets the number of items loaded from the file.
     *
     * @return The number of items in the list loaded from the file
     */
    public static int getItemNumber() {
        return itemNumber;
    }

    /**
     * Creates a storage object to handle reading of the file,
     * loading of tasks from the file and saving the tasks to the file.
     *
     * @param fileName The path of the local file
     */
    public Storage(String fileName) {
        ui = new Ui();
        parser = new Parser();
        dataFile = new File(fileName);
    }

    /**
     * Checks if the directory and file exists, else create one.
     *
     * @throws IOException If there is an error creating the file
     */
    public void createFile() throws IOException {
        File directory = new File("data");
        try {
            if (!directory.exists()) {
                directory.mkdir();
                ui.printCreateDirectoryMessage();
            }
            if (dataFile.createNewFile()) {
                ui.newFileCreatedMessage();
            } else {
                ui.printFileExistsMessage();
            }
        } catch (IOException e) {
            ui.printCreateFileError();
        }
    }

    /**
     * Reads the tasks from the file and returns the list
     * of tasks.
     * @return The list of tasks read from the file
     * @throws IOException If the file is not found
     */
    private ArrayList readFile () throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException();
        }
        if (dataFile.length() == 0) {
            ui.printFileEmptyMessage();
        }
        ArrayList<String> dataItems = (ArrayList) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
        return dataItems;
    }

    /**
     * Loads the data from the read file into the task list.
     *
     * @return The task list loaded from the file
     * @throws IOException If the file is not found
     * @throws DukeException If there is an error parsing the file contents
     */
    public ArrayList<Task> loadData() throws IOException, DukeException{
        ArrayList<Task> taskList = null;
        try {
            ArrayList<String> dataItems = readFile();
            taskList = parse(dataItems);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Loads individual task into the task list by checking the
     * task type.
     *
     * @param inCommand The line read from the file
     * @throws DukeException If there is an error adding a task
     */
    private static void loadTask(String inCommand) throws DukeException {
        String taskType = parser.parseCommandFromString(inCommand);
        String[] taskDescription = parser.parseTaskDescriptionFromString(inCommand);
        switch (taskType) {
        case COMMAND_TODO:
            loadToDoEntry(taskDescription);
            break;
        case COMMAND_DEADLINE:
            loadDeadLineEntry(taskDescription);
            break;
        case COMMAND_EVENT:
            loadEventEntry(taskDescription);
            break;
        default:
            ui.printUnknownCommand();
            break;
        }

    }

    /**
     * Loads the event task type into the task list.
     * @param taskDescription The event task description from the file line
     */
    private static void loadEventEntry(String[] taskDescription) {
        try {
            eventsArray = parser.parseEventsActionFromDescription(taskDescription);
            if (isEventFormatInvalid(eventsArray)) {
                throw new DukeException();
            }
            allTasks.add(new Event(eventsArray[0], eventsArray[1]));
            itemNumber++;
        } catch (ArrayIndexOutOfBoundsException deadlineEmpty) {
            ui.printEventEmptyException();
        } catch (DukeException invalidEventInput) {
            ui.printInvalidEventException();
        }
    }

    /**
     * Checks if the action parameter or the event date parameter is empty.
     * @param eventsArray the event description
     * @return True if any parameter is empty, false otherwise
     */
    private static boolean isEventFormatInvalid(String[] eventsArray) {
        return eventsArray[1].length() == 0 || eventsArray[0].length() == 0;
    }

    /**
     * Loads the deadline task type into the task list.
     * @param taskDescription The deadline task description from the file line
     */
    private static void loadDeadLineEntry(String[] taskDescription) {
        LocalDate deadLineDate;
        try {
            datesArray = parser.parseDeadLineActionFromDescription(taskDescription);
            deadLineDate = parser.parseDateFormatFromString(datesArray[1]);
            if (isDateFormatInvalid(datesArray)) {
                throw new DukeException();
            }
            allTasks.add(new Deadline(datesArray[0], deadLineDate));
            itemNumber++;
        } catch (ArrayIndexOutOfBoundsException deadLineEmpty) {
            ui.printDeadLineEmptyException();
        } catch (DukeException invalidDateInput) {
            ui.printInvalidDeadLineException();
        }
    }

    /**
     * Checks if the action parameter or the due date parameter is empty
     * @param datesArray the deadline description
     * @return True if any parameter is empty, false otherwise
     */
    private static boolean isDateFormatInvalid(String[] datesArray) {
        return datesArray[1].length() == 0 || datesArray[0].length() == 0;
    }

    /**
     * Loads the todo task type into the task list.
     * @param taskDescription The todo task description from the file line
     */
    private static void loadToDoEntry(String[] taskDescription) {
        try {
            allTasks.add(new ToDo(taskDescription[1]));
            itemNumber++;
        } catch (IndexOutOfBoundsException todoEmpty) {
            ui.printTodoEmptyException();
        }
    }

    /**
     * Checks if the task has been marked done in the file.
     *
     * @param checkDone The string of the done component in the line
     * @return True if task was already marked done in the file, false otherwise
     */
    private static boolean checkIfDone(String checkDone) {

        if (checkDone.equals("true")) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Takes in all the lines from the file and parse the information
     * to distinguish each task type and its description.
     * @param dataItems The list of lines in the file
     * @return The task list consisting of all the task saved in the file
     * @throws DukeException If there is an error adding a task
     */
    private ArrayList<Task> parse(ArrayList<String> dataItems) throws DukeException {

        String[] fileLineArray;
        char inputTask;
        for (String line : dataItems) {
            fileLineArray = parser.parseLineFromFile(line);
            inputTask = fileLineArray[0].charAt(0);
            switch (inputTask) {
            case TYPE_TODO:
                loadTask("todo " + fileLineArray[2]);
                break;
            case TYPE_DEADLINE:
                loadTask("deadline " + fileLineArray[2]);
                break;
            case TYPE_EVENT:
                loadTask("event " + fileLineArray[2]);
            }

            allTasks.get(itemNumber - 1).setDone(checkIfDone(fileLineArray[1]));
        }
        return allTasks;
    }

    /**
     * Saves the current state of the list to the file.
     * @throws IOException If there is error opening the file
     */
    public static void saveTaskList() throws IOException {
        FileWriter fw = new FileWriter(dataFile);
        ArrayList<Task> copyOfTasks = TaskList.getTaskList();
        for (Task a : copyOfTasks) {
            fw.write(String.valueOf(a.getTaskType()) + "-" + String.valueOf(a.isDone()) + "-"
                    + a.saveDescription() + a.saveAdditionalInfo() + System.lineSeparator());
        }
        fw.close();
    }
}
