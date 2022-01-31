package tasks;

<<<<<<< HEAD
=======
import exceptions.DukeException;
import exceptions.TodoDukeException;
import ui.ChatBox;

>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
public class TaskFactory {
    private String taskDescription;
    private Boolean marked = false;
    private String taskType = "";

    private static final String TODO_TASK = "todo";
    private static final String DEADLINE_TASK = "deadline";
    private static final String EVENT_TASK = "event";




    /**
<<<<<<< HEAD
     * Initializes the task factory
=======
     * Initializes the task factory.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param taskType
     * @param taskDecription
     */
    public TaskFactory(String taskType, String taskDecription){
        this.taskDescription = taskDecription;
        this.taskType = taskType;
    }

    public TaskFactory(){
    }

    /**
<<<<<<< HEAD
     * Makes certain task according to the taskType
     * @return Certain task
     */
    public Task makeTask() {
        switch (taskType) {
        case TODO_TASK:
            return new ToDoTask(taskDescription,"T");
=======
     * Makes certain task according to the taskType.
     *
     * @return Certain task
     */
    public Task makeTask() throws TodoDukeException {
        switch (taskType) {
        case TODO_TASK:
            try {
                return new ToDoTask(taskDescription, "T");
            } catch (TodoDukeException e1)
            {
                throw e1;

            }
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
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
<<<<<<< HEAD
     * Sets the taskDescription of TaskFactory
=======
     * Sets the taskDescription of TaskFactory.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param taskDescription
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
<<<<<<< HEAD
     * Sets the taskType of TaskFactory
=======
     * Sets the taskType of TaskFactory.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param taskType The task type
     */
    public void setTaskType(String taskType){
        this.taskType = taskType;
    }
}
