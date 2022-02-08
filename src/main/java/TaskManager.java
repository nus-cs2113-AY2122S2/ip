public class TaskManager {
    private static TaskList taskList;
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    TaskManager() {
        taskList = new TaskList();
    }

    private Task createTask(String taskType, String[] taskDescription) {
        Task task;
        if (taskType.equals(DEADLINE)) {
            task = new Deadline(taskDescription[0], taskDescription[1]);
        } else if (taskType.equals(EVENT)) {
            task = new Event(taskDescription[0], taskDescription[1]);
        } else {
            task = new Todo(taskDescription[0]);
        }
        return task;
    }

    public String markTask(String markCommand, String id) {
        int index = Integer.parseInt(id) - 1;
        boolean isDone = markCommand.equals("mark");
        taskList.getTask(index).setDone(isDone);
        return Ui.markTaskMsg(taskList.getTask(index), isDone);
    }

    public String addTask(String taskType, String[] taskDescription) {
        Task task = createTask(taskType, taskDescription);
        taskList.addTask(task);
        return Ui.addTaskMsg(task, taskList.getSize());
    }

    public String listTask() {
        return taskList.toString();
    }
}
