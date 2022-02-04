package tasks;

import java.util.HashMap;

public class DeadlinesTask extends Task {
    private String dateTime;
    private static final String DATE_TIME_FIELD = "dateTime";

    /**
     * Initializes deadline task with task description and task type and dateTime
     *
     * @param taskDescription
     * @param taskType
     * @param dateTime
     */
    public DeadlinesTask(String taskDescription, String taskType, String dateTime) {
        super(taskDescription, taskType);
        this.dateTime = dateTime;
    }

    /**
     * Initializes deadline task with compressed object
     * @param compressedObject Compressed object that contains all model information for initialize that task
     */
    public DeadlinesTask(HashMap<String, Object> compressedObject) {
        super( compressedObject);
        dateTime = (String) compressedObject.get(DATE_TIME_FIELD);
    }

    /**
     * Sets the dateTime.
     *
     * @param dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the report of the deadline task.
     *
     * @return The report of the task
     */
    @Override
    public String getReport() {
        return String.format("[%s][%s] %s (by: %s)", taskType, markedSign(), taskDescription, dateTime);
    }

    /**
     * Compresses the task objects into a map objects
     * @return A compressed object that can initialize the model task again
     */
    @Override
    public HashMap<String, Object> compress() {
        HashMap<String, Object> compressedObject = super.compress();
        compressedObject.put(DATE_TIME_FIELD, dateTime);
        return compressedObject;
    }
}
