package duke;

import java.util.List;
import java.util.Optional;

/**
 * Represents a management system to manage the TaskList in the higher levels.
 */
public class TaskManager {
    private static TaskList taskList;

    public TaskManager() {
        taskList = new TaskList();
    }

    public TaskManager(List<Task> tasks) {
        taskList = new TaskList();
        for (Task task : tasks) {
            taskList.addTask(task);
        }
    }

    /**
     * Adds task to the TaskList.
     *
     * @param task Task to be added.
     * @return UI output message of the added task.
     */
    public String addTask(Task task) {
        taskList.addTask(task);
        return Ui.addTaskMsg(task, taskList.getSize());
    }

    /**
     * Does not add the duplicate task to the TaskList.
     *
     * @param task Duplicate task.
     * @return UI output message of not adding duplicate task.
     */
    public String notAddDuplicate(Task task) {
        return Ui.dupTaskMsg(task, taskList.getSize());
    }

    /**
     * Creates a Todo task and add it to TaskList.
     * Skips adding task if it is a duplicate task.
     *
     * @param taskDescription Details of the task.
     *                        [0] Task Description.
     * @return UI output message of the newly-created Todo.
     */
    public String createTodo(String[] taskDescription) {
        Todo todo = new Todo(taskDescription[0]);
        if (isDuplicate(todo).isPresent()) {
            return notAddDuplicate(isDuplicate(todo).get());
        }
        return addTask(todo);
    }

    /**
     * Creates a Deadline task and add it to TaskList.
     * Skips adding task if it is a duplicate task.
     *
     * @param taskDescription Details of the task.
     *                        [0] Task Description.
     *                        [1] Operation.
     *                        [2] Date/Time.
     * @return UI output message of the newly-created Deadline.
     * @throws DukeException if the input is in the wrong format or there is missing input.
     */
    public String createDeadline(String[] taskDescription) throws DukeException {
        if (!taskDescription[1].equals("/by")) {
            throw new DukeException(Ui.wrongInputFormatError());
        }
        if (taskDescription[2].isEmpty()) {
            throw new DukeException(Ui.missingDateError());
        }
        Deadline deadline = new Deadline(taskDescription[0], taskDescription[2]);
        if (isDuplicate(deadline).isPresent()) {
            return notAddDuplicate(isDuplicate(deadline).get());
        }
        return addTask(deadline);
    }

    /**
     * Creates an Event task and add it to TaskList.
     * Skips adding task if it is a duplicate task.
     *
     * @param taskDescription Details of the task.
     *                        [0] Task Description.
     *                        [1] Operation.
     *                        [2] Date/Time.
     * @return UI output message of the newly-created Event.
     * @throws DukeException if the input is in the wrong format or there is missing input.
     */
    public String createEvent(String[] taskDescription) throws DukeException {
        if (!taskDescription[1].equals("/at")) {
            throw new DukeException(Ui.wrongInputFormatError());
        }
        if (taskDescription[2].isEmpty()) {
            throw new DukeException(Ui.missingDateError());
        }
        Event event = new Event(taskDescription[0], taskDescription[2]);
        if (isDuplicate(event).isPresent()) {
            return notAddDuplicate(isDuplicate(event).get());
        }
        return addTask(event);
    }

    /**
     * Marks the task as Done/NotDone.
     *
     * @param id Task ID.
     * @param isDone Parameter representing whether the task is done.
     * @return UI output message of the marked task.
     * @throws DukeException if there not exists a task with the given task ID.
     */
    public String markTask(int id, boolean isDone) throws DukeException {
        try {
            int index = id - 1;
            taskList.getTask(index).setDone(isDone);
            return Ui.markTaskMsg(taskList.getTask(index), isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.taskIdOutOfBoundError(id));
        }
    }

    /**
     * Finds all the tasks that contain the given words.
     *
     * @param matchWord Words to be matched.
     * @return UI output message of the list of matching tasks.
     */
    public String findTask(String matchWord) {
        TaskList matchedTaskList = new TaskList();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.containsWord(matchWord)) {
                matchedTaskList.addTask(task);
            }
        }
        return Ui.listMatchedTaskMsg(matchedTaskList);
    }

    /**
     * Deletes the task with the given task ID.
     *
     * @param id Task ID.
     * @return UI output message of the deleted task.
     */
    public String delTask(int id) throws DukeException {
        try {
            int index = id - 1;
            Task task = taskList.getTask(index);
            taskList.delTask(index);
            return Ui.delTaskMsg(task, taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.taskIdOutOfBoundError(id));
        }
    }

    /**
     * Lists all the tasks in the TaskList.
     * @return UI output message of the list of tasks.
     */
    public String listTask() {
        return Ui.listTaskMsg(taskList);
    }

    /**
     * Generates a list of all the tasks in a specific format for data saving.
     * @return String of the list.
     */
    public String getList() {
        String list = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            list += taskList.getTask(i).toStringInSaveFormat() + "\n";
        }
        return list;
    }

    /**
     * Checks if a task is a duplicate of an existing task.
     * Returns the task if there exists a duplicate task.
     *
     * Task A is a duplicate of Task B if and only if
     * 1. Task A and B are of the same type, and
     * 2. Task A and B have the same task description.
     *
     * @return Existing duplicate task.
     */
    private Optional<Task> isDuplicate(Task newTask) {
        Optional<Task> duplicate = Optional.ofNullable(null);
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.equals(newTask)) {
                duplicate = Optional.ofNullable(task);
            }
        }
        return duplicate;
    }
}
