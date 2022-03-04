package tasks;

import exceptions.*;

import java.util.HashMap;

public class TaskFactory {
    private String taskDescription;
    private Boolean marked = false;
    private String taskType = "";

    private static final String TODO_TASK = "T";
    private static final String DEADLINE_TASK = "D";
    private static final String EVENT_TASK = "E";
    private static final String TASK_TYPE = "taskType";


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
    public Task makeTask() throws DukeException {
        try {
            switch (taskType) {
            case TODO_TASK:
                return new ToDoTask(taskDescription, TODO_TASK);
            case DEADLINE_TASK:
                String[] taskInfomation = taskDescription.split("/by");
                taskDescription = taskInfomation[0].trim();
                String dateTime = taskInfomation[1].trim();
                return new DeadlinesTask(taskDescription, DEADLINE_TASK, dateTime);
            case EVENT_TASK:
                taskInfomation = taskDescription.split("/at");
                taskDescription = taskInfomation[0].trim();
                dateTime = taskInfomation[1].trim();
                return new EventTask(taskDescription, EVENT_TASK, dateTime);
            default:
                return new Task(taskDescription);
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    public Task decompressTask(HashMap<String, Object> compressedTask) throws DukeException {
        try {
            String taskType = (String) compressedTask.get(TASK_TYPE);
            switch (taskType) {
            case TODO_TASK:
                return new ToDoTask(compressedTask);
            case DEADLINE_TASK:
                return new DeadlinesTask(compressedTask);
            case EVENT_TASK:
                return new EventTask(compressedTask);
            default:
                throw new TaskDecompressionDukeException();
            }
        } catch (DukeException e) {
            throw e;
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
