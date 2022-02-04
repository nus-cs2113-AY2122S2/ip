package tasks;

import java.util.HashMap;
import java.util.Map;

public class EventTask extends Task {
    private String dateTime;
    private static final String DATE_TIME_FIELD = "dateTime";

    public EventTask(String taskDescription, String taskType) {
        super(taskDescription, taskType);
    }


    /**
     * Initializes task with compressed object
     * @param compressedObject Compressed object that contains all model information for initialize that task
     */
    public EventTask(HashMap<String, Object> compressedObject) {
        super( compressedObject);
        dateTime = (String) compressedObject.get(DATE_TIME_FIELD);
    }

    /**
     * Initializes task with task description, taskType, dateTime.
     *
     * @param taskDescription The description of the task
     * @param taskType The type of the task
     * @param dateTime The time of the task
     */
    public EventTask(String taskDescription, String taskType, String dateTime) {
        super(taskDescription, taskType);
        this.dateTime = dateTime;
    }


    /**
     * Sets dateTime.
     *
     * @param dateTime The time of the task
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    /**
     * Gets the report of the event task.
     *
     * @return The report of the event task
     */
    @Override
    public String getReport() {
        return String.format("[%s][%s] %s (at: %s)", taskType, markedSign(), taskDescription, dateTime);
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
