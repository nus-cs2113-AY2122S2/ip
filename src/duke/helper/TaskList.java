package duke.helper;

import duke.main.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import static duke.helper.Parser.INVALID;
import static duke.helper.Ui.LINEBREAK;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Task List object, containing the list of tasks
 * and the number of tasks currently present.
 * Handles all methods to do with the list of tasks
 */
public class TaskList {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static ArrayList<Task> list = new ArrayList<>();
    protected static int taskCount = 0;

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Adds an existing saved task from storage to the list
     * @param task the Task object of the saved task
     */
    public void addSavedTask(Task task) {
        list.add(task);
        taskCount++;
    }

    /**
     * Returns the string details of a task in the saving format
     * @param index index of the task in the list to be saved
     * @return the details of the task in the saving format
     */
    public String getTaskDetails(int index) {
        return list.get(index).getTaskDetails();
    }

    /**
     * Prints the list of tasks in standard output
     */
    public void listTasks() {
        System.out.println(LINEBREAK);
        for (int j = 0; j < taskCount; j++) {
            System.out.println(Integer.toString(j + 1) + list.get(j));
        }
        System.out.println(LINEBREAK);
    }

    /**
     * Search for a task based on a keyword
     * @param keyword the keyword that the user would like to search for the task
     */
    public void findTasks(String keyword) {
        System.out.println(LINEBREAK);
        String matchedTasks = "Here are the matching tasks: \n";
        boolean hasMatched = false;
        for (int j = 0; j < taskCount; j++) {
            boolean isMatched = list.get(j).getName().contains(keyword);
            if (isMatched) {
                hasMatched = true;
                matchedTasks += Integer.toString(j + 1) + list.get(j) + "\n";
            }
        }
        if (hasMatched) {
            System.out.println(matchedTasks);
        }else {
            System.out.println("There are no matching tasks.");
        }
        System.out.println(LINEBREAK);
    }

    /**
     * Deletes a task from the list of tasks
     * @param index index in the list the task to be deleted
     * @param ui Ui object to handle communication with the user
     * @param storage Storage object to handle saving and loading tasks
     */
    public void deleteTask(int index, Ui ui, Storage storage) {
        if (index == INVALID) {
            return;
        }
        taskCount--;
        ui.printDeleteMessage(list.get(index).toString(), taskCount);

        list.remove((index));
        storage.saveTasks(ui, this);
    }

    /**
     * Marks/Unmarks the status of the task
     * @param index index in the list the task to be marked/unmarked
     * @param mark boolean to show if the task should be marked or not
     * @param ui Ui object to handle communication with the user
     * @param storage Storage object to handle saving and loading tasks
     */
    public void updateMarkTask(int index, boolean mark, Ui ui, Storage storage) {
        if (index == INVALID) {
            return;
        }
        ui.printMarkedMessage(mark, list.get(index).getName());
        list.get(index).setMarked(mark);
        storage.saveTasks(ui, this);
    }

    /**
     * Adds a Todo task to the end of the list
     * @param parser Parser object to parse the user's input
     * @throws DukeException if unable to parse the user's input correctly
     */
    public void addTodo(Parser parser) throws DukeException {
        parser.parseTodo();
        String taskName = parser.getTaskName();
        list.add(new Todo(taskName, false));
    }

    /**
     * Adds a Deadline task to the end of the list
     * @param parser Parser object to parse the user's input
     * @throws DukeException if unable to parse the user's input correctly
     */
    public void addDeadline(Parser parser) throws DukeException {
       parser.parseDeadline();
       String taskName = parser.getTaskName();
       LocalDate deadlineDate = parser.getEndDate();
       LocalTime deadlineTime = parser.getEndTime();
       list.add(new Deadline(taskName, false, deadlineDate, deadlineTime));
    }

    /**
     * Adds an Event task to the end of the list
     * @param parser Parser object to parse the user's input
     * @throws DukeException if unable to parse the user's input correctly
     */
    public void addEvent(Parser parser) throws DukeException {
        parser.parseEvent();
        String taskName = parser.getTaskName();
        LocalDate startDate = parser.getStartDate();
        LocalTime startTime = parser.getStartTime();
        LocalDate endDate = parser.getEndDate();
        LocalTime endTime = parser.getEndTime();
        list.add(new Event(taskName, false, startDate, startTime, endDate, endTime));
    }

    /**
     * Add a task to the list based on the type that the user gave
     * @param ui Ui object to handle communication with the user
     * @param storage Storage object to handle saving and loading tasks
     * @param parser Parser object to parse the user's input
     */
    public void addNewTask(Ui ui, Storage storage, Parser parser){
        String taskType = parser.parseTaskType();
        parser.removeTaskType(taskType);
        try {
            switch (taskType) {
            case TODO:
                addTodo(parser);
                break;
            case DEADLINE:
                addDeadline(parser);
                break;
            case EVENT:
                addEvent(parser);
                break;
            default:
                throw new DukeException("Command not recognised.");
            }
        }catch (DukeException e) {
            ui.printExceptionMessage(e.getMessage());
            return;
        }
        ui.printAddTaskMessage(list.get(taskCount).toString(), taskCount + 1);
        taskCount++;
        storage.saveTasks(ui, this);
    }
}
