package duke.helper;

import duke.main.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import static duke.helper.Ui.LINEBREAK;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public void findTasks(String keyword) {
        System.out.println(LINEBREAK);
        String matchedTasks = "Here are the matching tasks: \n";
        boolean hasMatched = false;
        for (int j = 0; j < taskCount; j++) {
            boolean isMatched = list.get(j).getName().contains(keyword);
            if (isMatched){
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

    public void addTodo(Parser parser) throws DukeException {
        parser.parseTodo();
        String taskName = parser.getTaskName();
        list.add(new Todo(taskName, false));
    }

    public void addDeadline(Parser parser) throws DukeException {
       parser.parseDeadline();
       String taskName = parser.getTaskName();
       LocalDate deadlineDate = parser.getEndDate();
       LocalTime deadlineTime = parser.getEndTime();
       list.add(new Deadline(taskName, false, deadlineDate, deadlineTime));
    }

    public void addEvent(Parser parser) throws DukeException {
        parser.parseEvent();
        String taskName = parser.getTaskName();
        LocalDate startDate = parser.getStartDate();
        LocalTime startTime = parser.getStartTime();
        LocalDate endDate = parser.getEndDate();
        LocalTime endTime = parser.getEndTime();
        list.add(new Event(taskName, false, startDate, startTime, endDate, endTime));
    }

    public void addNewTask(Ui ui, Storage storage, Parser parser){
        String taskType = parser.parseTaskType();
        parser.removeCommand(taskType);
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
