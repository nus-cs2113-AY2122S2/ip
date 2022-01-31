package tasks;

<<<<<<< HEAD
public class ToDoTask extends Task{


    /**
     * Initializes a todo task with task description and task type
     * @param taskDescription The description of the task
     * @param taskType The task type
     */
    public ToDoTask(String taskDescription, String taskType) {
        super(taskDescription, taskType);
=======
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
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    }


}
