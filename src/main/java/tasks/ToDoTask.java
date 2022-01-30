package tasks;

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
        if (taskDescription.length()==0){
            throw new TodoDukeException();
        }
    }


}
