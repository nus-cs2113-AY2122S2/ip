package tasks;

import exceptions.DukeException;
import exceptions.TodoDukeException;
import ui.ChatBox;

public class TaskFactory {
    private String taskDescription;
    private Boolean marked = false;
    private String taskType = "";

    private static final String TODO_TASK = "todo";
    private static final String DEADLINE_TASK = "deadline";
    private static final String EVENT_TASK = "event";


    /**
     * Initializes the task factory.
     *
     * @param taskType
     * @param taskDecription
     */
    public TaskFactory(String taskType, String taskDecription) {
        this.taskDescription = taskDecription;
        this.taskType = taskType;
    }

    public TaskFactory() {
    }

    /**
     * Makes certain task according to the taskType.
     *
     * @return Certain task
     */
    public Task makeTask() throws TodoDukeException {
        switch (taskType) {
        case TODO_TASK:
            try {
                return new ToDoTask(taskDescription, "T");
            } catch (TodoDukeException e1) {
                throw e1;

            }
        case DEADLINE_TASK:
            String[] taskInfomation = taskDescription.split("/by");
            taskDescription = taskInfomation[0].trim();
            String dateTime = taskInfomation[1].trim();
            return new DeadlinesTask(taskDescription, "D", dateTime);
        case EVENT_TASK:
            taskInfomation = taskDescription.split("/at");
            taskDescription = taskInfomation[0].trim();
            dateTime = taskInfomation[1].trim();
            return new EventTask(taskDescription, "E", dateTime);
        default:
            return new Task(taskDescription);
        }
    }

    /**
     * Sets the taskDescription of TaskFactory.
     *
     * @param taskDescription
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Sets the taskType of TaskFactory.
     *
     * @param taskType The task type
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
