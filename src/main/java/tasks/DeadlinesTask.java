package tasks;

import exceptions.DukeException;
import times.DukeTime;

import java.util.HashMap;

public class DeadlinesTask extends Task {
    private DukeTime dateTime;
    private static final String DATE_TIME_FIELD = "dateTime";

    /**
     * Initializes deadline task with task description and task type and dateTime
     *
     * @param taskDescription
     * @param taskType
     * @param dateTime
     */
    public DeadlinesTask(String taskDescription, String taskType, String dateTime) throws DukeException {
        super(taskDescription, taskType);
        try{
            this.dateTime = new DukeTime(dateTime);
        } catch (DukeException e) {
            throw e;
        }

    }

    /**
     * Initializes deadline task with compressed object
     * @param compressedObject Compressed object that contains all model information for initialize that task
     */
    public DeadlinesTask(HashMap<String, Object> compressedObject) {
        super( compressedObject);
        dateTime = (DukeTime) compressedObject.get(DATE_TIME_FIELD);
    }

    /**
     * Sets the dateTime.
     *
     * @param dateTime
     */
    public void setDateTime(String dateTime) throws DukeException {
        try {
            this.dateTime = new DukeTime(dateTime);
        } catch (DukeException e) {
            throw e;
        }

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
