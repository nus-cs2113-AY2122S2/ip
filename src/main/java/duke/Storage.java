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

    public static int getItemNum() {
        return itemNumber;
    }

    private static int itemNumber;

    public Storage(String fileName) {
        ui = new Ui();
        parser = new Parser();
        dataFile = new File(fileName);
    }

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

    public ArrayList<Task> loadData() {
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

    private static void loadEventEntry(String[] taskDescription) {
        try {
            eventsArray = parser.parseEventsActionFromDesciption(taskDescription);
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

    private static boolean isEventFormatInvalid(String[] eventsArray) {
        return eventsArray[1].length() == 0 || eventsArray[0].length() == 0;
    }

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

    private static boolean isDateFormatInvalid(String[] datesArray) {
        return datesArray[1].length() == 0 || datesArray[0].length() == 0;
    }

    private static void loadToDoEntry(String[] sentenceArr) {
        try {
            allTasks.add(new ToDo(sentenceArr[1]));
            itemNumber++;
        } catch (IndexOutOfBoundsException todoEmpty) {
            ui.printTodoEmptyException();
        }
    }

    private static boolean checkIfDone(String checkDone) {

        if (checkDone.equals("true")) {
            return true;
        }
        else {
            return false;
        }
    }

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
