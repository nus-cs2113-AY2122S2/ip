public class TaskManager {
    private static TaskList taskList;

    TaskManager() {
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

    public String addTodo(String[] taskDescription) {
        Task task = new Todo(taskDescription[0]);
        return addTask(task);
    }

    public String addDeadline(String[] taskDescription) {
        Task task = new Deadline(taskDescription[0], taskDescription[1]);
        return addTask(task);
    }

    public String addEvent(String[] taskDescription) {
        Task task = new Event(taskDescription[0], taskDescription[1]);
        return addTask(task);
    }

    private String addTask(Task task) {
        taskList.addTask(task);
        return Ui.addTaskMsg(task, taskList.getSize());
    }

    public String listTask() {
        return taskList.toString();
    }
}
