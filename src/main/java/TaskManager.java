import java.util.Optional;

public class TaskManager {
    private static TaskList taskList;

    public TaskManager() {
        taskList = new TaskList();
    }

    public String markTask(int id, boolean isDone) throws DukeException {
        try {
            int index = id - 1;
            taskList.getTask(index).setDone(isDone);
            return Ui.markTaskMsg(taskList.getTask(index), isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.taskIdOutOfBound(id));
        }
    }

    public String addTask(Task task) {
        taskList.addTask(task);
        return Ui.addTaskMsg(task, taskList.getSize());
    }

    public String notAddDuplicate(Task task) {
        return Ui.dupTaskMsg(task, taskList.getSize());
    }

    public String addTodo(String[] taskDescription) {
        Todo todo = new Todo(taskDescription[0]);
        if (isDuplicate(todo).isPresent()) {
            return notAddDuplicate(isDuplicate(todo).get());
        }
        return addTask(todo);
    }

    public String addDeadline(String[] taskDescription) throws DukeException {
        if (!taskDescription[1].equals("/by")) {
            throw new DukeException(Ui.wrongInputFormat());
        }
        if (taskDescription[2].isEmpty()) {
            throw new DukeException(Ui.missingDate());
        }
        Deadline deadline = new Deadline(taskDescription[0], taskDescription[2]);
        if (isDuplicate(deadline).isPresent()) {
            return notAddDuplicate(isDuplicate(deadline).get());
        }
        return addTask(deadline);
    }

    public String addEvent(String[] taskDescription) throws DukeException {
        if (!taskDescription[1].equals("/at")) {
            throw new DukeException(Ui.wrongInputFormat());
        }
        if (taskDescription[2].isEmpty()) {
            throw new DukeException(Ui.missingDate());
        }
        Event event = new Event(taskDescription[0], taskDescription[2]);
        if (isDuplicate(event).isPresent()) {
            return notAddDuplicate(isDuplicate(event).get());
        }
        return addTask(event);
    }

    public String delTask(int id) throws DukeException {
        try {
            int index = id - 1;
            Task task = taskList.getTask(index);
            taskList.delTask(index);
            return Ui.delTaskMsg(task, taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.taskIdOutOfBound(id));
        }
    }

    public String listTask() {
        return taskList.toString();
    }

    public String getList() {
        String list = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            list += taskList.getTask(i).toStringInSaveFormat() + "\n";
        }
        return list;
    }

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
