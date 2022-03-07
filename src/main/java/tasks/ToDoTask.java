package tasks;

import java.util.HashMap;

import exceptions.TodoDukeException;


public class ToDoTask extends Task {


    /**
     * Initializes a todo task with task description and task type.
     *
     * @param taskDescription The description of the task
     * @param taskType The task type
     */
    public ToDoTask(String taskDescription, String taskType) throws TodoDukeException {
        super(taskDescription, taskType);
        if (taskDescription.length() == 0) {
            throw new TodoDukeException();
        }
    }

    /**
     * Initializes a todo task with compressed object
     *
     * @param compressedObject Compressed object that contains all model information for initialize that task
     */
    public ToDoTask(HashMap<String, Object> compressedObject) {
        super(compressedObject);
    }


}
