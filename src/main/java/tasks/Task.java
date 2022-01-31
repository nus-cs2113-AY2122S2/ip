package tasks;

public class Task {
    protected  String taskDescription;
    protected  Boolean marked = false;
    protected String taskType = " ";

    /**
<<<<<<< HEAD
     * Initializes task with task description
=======
     * Initializes task with task description.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param taskDescription The description of the task
     */
    public Task(String taskDescription){
        this.taskDescription =  taskDescription;
    }

    /**
<<<<<<< HEAD
     * Initializes task with task description and task type
=======
     * Initializes task with task description and task type.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param taskDescription The description of the task
     * @param taskType The type of the task
     */
    public Task(String taskDescription, String taskType){
        this.taskDescription = taskDescription;
        this.taskType =  taskType;
    }

    public Task() {

    }

<<<<<<< HEAD
    /**
     * Gets the description of the task
=======

    /**
     * Gets the description of the task.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @return The description of the task
     */
    public String getTaskDescription(){
        return taskDescription;
    }

<<<<<<< HEAD
=======

    /**
     * Return the marked sign based on the boolean "marked".
     *
     * @return markedSign
     */
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
    protected String markedSign(){
        if(marked!=true){
            return " ";
        }
        else{
            return "X";
        }
    }

<<<<<<< HEAD
    /**
     * Gets the report of the task
=======

    /**
     * Gets the report of the task.
     *
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @return The report of the task
     */
    public String getReport(){
        return String.format("[%s][%s] %s", taskType, markedSign(), taskDescription);
    }


    /**
<<<<<<< HEAD
     * Sets the mark of a task
=======
     * Sets the mark of a task.
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param isMark Boolean that is to be set
     */
    public void setMark(Boolean isMark) {
       marked = isMark;
    }

}
