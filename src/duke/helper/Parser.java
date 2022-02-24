package duke.helper;

import duke.main.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    protected static Scanner in = new Scanner(System.in);
    protected static String line;
    protected static String taskName;
    protected static LocalDate endDate;
    protected static LocalTime endTime;
    protected static LocalDate startDate;
    protected static LocalTime startTime;
    public static final int INVALID = -1;
    public static final String FINDCMD = "find";

    public String getTaskName() {
        return taskName;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void getNextLine() {
        line = in.nextLine();
    }

    public boolean isMarkCommand() {
        return line.split(" ")[0].equals("mark");
    }

    public boolean isUnmarkCommand() {
        return line.split(" ")[0].equals("unmark");
    }

    public boolean isDeleteCommand() {
        return line.split(" ")[0].equals("delete");
    }

    public boolean isExitCommand() {
        return line.equals("bye");
    }

    public boolean isListCommand() {
        return line.equals("list");
    }

    public boolean isFindCommand() {
        return line.split(" ")[0].equals(FINDCMD);
    }

    public String getLine() {
        return line;
    }

    public int parseIndex(Ui ui, int taskCount) {
        int index;
        try {
            index = Integer.parseInt(line.split(" ")[1]) - 1;
            if (index >= taskCount || index < 0) {
                throw new DukeException("Task Index is out of bounds.");
            }
        }catch (NumberFormatException | DukeException e) {
            ui.printExceptionMessage(e.getMessage());
            return INVALID;
        }
        return index;
    }

    public void waitForInput(Ui ui,Storage storage, TaskList tasks) {
        while (true) {
            getNextLine();
            if (isExitCommand()) {
                break;
            }else if (isListCommand()) {
                tasks.listTasks();
            }else if (isMarkCommand()) {
                int index = parseIndex(ui, tasks.getTaskCount());
                tasks.updateMarkTask(index, true, ui, storage);
            }else if (isUnmarkCommand()) {
                int index = parseIndex(ui, tasks.getTaskCount());
                tasks.updateMarkTask(index, false, ui, storage);
            }else if (isDeleteCommand()) {
                int index = parseIndex(ui, tasks.getTaskCount());
                tasks.deleteTask(index, ui, storage);
            }else if (isFindCommand()) {
                removeCommand(FINDCMD);
                String keyword = getLine();
                tasks.findTasks(keyword);
            }else {
                tasks.addNewTask(ui, storage, this);
            }
        }
    }

    public String parseTaskType() {
        return line.split(" ")[0];
    }

    public void removeCommand(String taskType) {
        if (line.length() > taskType.length()) {
            line = line.substring(taskType.length() + 1);
        }else {
            line = "";
        }
    }

    public void parseTodo() throws DukeException {
        if (line.equals("")) {
            throw new DukeException("Argument of todo should not be empty.");
        }
        taskName = line;
    }

    public void parseDeadline() throws DukeException {
        String[] taskNameAndDeadline = line.split(" /by ");
        taskName = taskNameAndDeadline[0];
        if (taskNameAndDeadline.length < 2) {
            throw new DukeException("A Deadline Task should have the deadline.");
        }
        String[] deadlineDateAndTime = taskNameAndDeadline[1].split(" ");
        if (deadlineDateAndTime.length != 2){
            throw new DukeException("Invalid Date/Time format");
        }
        try {
            endDate = LocalDate.parse(deadlineDateAndTime[0]);
            endTime = LocalTime.parse(deadlineDateAndTime[1], DateTimeFormatter.ofPattern("HH:mm"));
        }catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date/Time format");
        }
    }

    public void parseEvent() throws DukeException {
        String[] taskNameAndTiming = line.split(" /at ");
        taskName = taskNameAndTiming[0];
        if (taskNameAndTiming.length < 2) {
            throw new DukeException("An Event Task should have the event timing.");
        }
        String[] eventDateAndTime = taskNameAndTiming[1].split(" to ");
        if (eventDateAndTime.length != 2){
            throw new DukeException("An Event Task should have both start and end timings.");
        }
        String[] startDateAndTime = eventDateAndTime[0].split(" ");
        if (startDateAndTime.length != 2){
            throw new DukeException("Start Timing should have both Date and Time, separated with a space");
        }
        String[] endDateAndTime = eventDateAndTime[1].split(" ");
        if (endDateAndTime.length != 2){
            throw new DukeException("End Timing should have both Date and Time, separated with a space");
        }
        try {
            startDate = LocalDate.parse(startDateAndTime[0]);
            startTime = LocalTime.parse(startDateAndTime[1], DateTimeFormatter.ofPattern("HH:mm"));
            endDate = LocalDate.parse(endDateAndTime[0]);
            endTime = LocalTime.parse(endDateAndTime[1], DateTimeFormatter.ofPattern("HH:mm"));
        }catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date/Time format");
        }
    }
}
