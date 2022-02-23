package duke.helper;

import duke.main.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import static duke.helper.Ui.LINEBREAK;
import java.util.ArrayList;

public class TaskList {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static ArrayList<Task> list = new ArrayList<>();
    protected static int taskCount = 0;

    public int getTaskCount() {
        return taskCount;
    }

    public void addSavedTask(Task task) {
        list.add(task);
        taskCount++;
    }

    public String getTaskDetails(int index) {
        return list.get(index).getTaskDetails();
    }

    public void listTasks() {
        System.out.println(LINEBREAK);
        for (int j = 0; j < taskCount; j++) {
            System.out.println(Integer.toString(j + 1) + list.get(j));
        }
        System.out.println(LINEBREAK);
    }

    public void deleteTask(int index, Ui ui, Storage storage) {
        taskCount--;
        ui.printDeleteMessage(list.get(index).toString(), taskCount);

        list.remove((index));
        storage.saveTasks(ui, this);
    }

    public void updateMarkTask(int index, boolean mark, Ui ui, Storage storage) {
        ui.printMarkedMessage(mark, list.get(index).getName());
        list.get(index).setMarked(mark);
        storage.saveTasks(ui, this);
    }

    public void addTodo(String line) throws DukeException {
        if (line.equals("")) {
            throw new DukeException("Argument of todo should not be empty.");
        }
        list.add(new Todo(line, false));
    }

    public void addDeadline(String line) throws DukeException {
        String[] taskNameAndDeadline = line.split(" /by ");
        if (taskNameAndDeadline.length < 2) {
            throw new DukeException("A Deadline Task should have the deadline.");
        }
        list.add(new Deadline(taskNameAndDeadline[0], false, taskNameAndDeadline[1]));
    }

    public void addEvent(String line) throws DukeException {
        String[] taskNameAndTiming = line.split(" /at ");
        if (taskNameAndTiming.length < 2) {
            throw new DukeException("An Event Task should have the event timing.");
        }
        list.add(new Event(taskNameAndTiming[0], false, taskNameAndTiming[1]));
    }

    public void addNewTask(String line, Ui ui, Storage storage){
        String taskType = line.split(" ")[0];
        if (line.length() > taskType.length()) {
            line = line.substring(taskType.length() + 1);
        }else {
            line = "";
        }
        try {
            switch (taskType) {
            case TODO:
                addTodo(line);
                break;
            case DEADLINE:
                addDeadline(line);
                break;
            case EVENT:
                addEvent(line);
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
