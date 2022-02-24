package tasks;

import java.util.HashMap;

public class Task {
    protected String taskDescription;
    protected Boolean marked = false;
    protected String taskType = " ";
    private static final String TASK_DESCRIPTION_FIELD = "taskDescription";
    private static final String MARKED_FIELD = "marked";
    private static final String TASK_TYPE = "taskType";

    /**
     * Initializes task with task description.
     *
     * @param taskDescription The description of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Initializes task with task description and task type.
     *
     * @param taskDescription The description of the task
     * @param taskType The type of the task
     */
    public Task(String taskDescription, String taskType) {
        this.taskDescription = taskDescription;
        this.taskType = taskType;
    }

    /**
     * Constructs a task objects with compressedObject
     * @param compressedObject Compressed object that contains all model information for initialize that task
     */
    public Task(HashMap<String, Object> compressedObject) {
        taskDescription = (String) compressedObject.get(TASK_DESCRIPTION_FIELD);
        marked = (Boolean) compressedObject.get(MARKED_FIELD);
        taskType = (String) compressedObject.get(TASK_TYPE);
    }


    /**
     * Gets the description of the task.
     *
     * @return The description of the task
     */
    public String getTaskDescription() {
        return taskDescription;
    }


    /**
     * Return the marked sign based on the boolean "marked".
     *
     * @return markedSign
     */
    protected String markedSign() {
        if (marked != true) {
            return " ";
        }
        else {
            return "X";
        }
    }


    /**
     * Gets the report of the task.
     *
     * @return The report of the task
     */
    public String getReport() {
        return String.format("[%s][%s] %s", taskType, markedSign(), taskDescription);
    }


    /**
     * Sets the mark of a task.
     * @param isMark Boolean that is to be set
     */
    public void setMark(Boolean isMark) {
        marked = isMark;
    }

    /**
     * Compresses the task objects into a map objects
     * @return A compressed object that can initialize the model task again
     */
    public HashMap<String, Object> compress() {
        HashMap<String, Object> compressedObject = new HashMap<>();
        compressedObject.put(TASK_DESCRIPTION_FIELD, taskDescription);
        compressedObject.put(MARKED_FIELD, marked);
        compressedObject.put(TASK_TYPE, taskType);
        return compressedObject;
    }

}
