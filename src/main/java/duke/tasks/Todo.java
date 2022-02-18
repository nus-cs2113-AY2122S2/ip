package duke.tasks;

public class Todo extends Task {

    /**
     * Create a Todo with isDone set to false and description of choice
     *
     * @param description Description of the Task to be created
     */
    public Todo(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = 'T';
    }
}
