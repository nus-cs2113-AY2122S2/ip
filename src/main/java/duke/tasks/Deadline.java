package duke.tasks;
import java.util.ArrayList;

public class Deadline extends ToDo {

    protected String doBy;

    /**
     * Constructor for Deadline object
     *
     * @param description Description of the deadline
     * @param doBy The due date of the deadline
     * @returns The deadline object
     */
    public Deadline(String description, String doBy) {
        super(description);
        this.doBy = doBy;
    }

    @Override
    public String getDescription() {
        String completeDescription = description + "(by: " + doBy + ")";
        return completeDescription;
    }

    @Override
    public String getStatusIcon() {
        String status = (isDone ? "X" : " ");
        String finalString = "[D][" + status + "]";
        return finalString;
    }
}
