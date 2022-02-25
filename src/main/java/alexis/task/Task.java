package alexis.task;

import static alexis.ui.Ui.ADD_NEW_TASK_MESSAGE;
import static alexis.ui.Ui.MARK_AS_DONE_MESSAGE;
import static alexis.ui.Ui.MARK_AS_UNDONE_MESSAGE;

public class Task {
    protected String description;
    protected String timing;
    protected boolean isDone;

    public char typeOfTask() {
        return '@';
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String getTiming() {
        return timing;
    }

    public String getFullDescription() {
        return description;
    }

    public void addNewTaskMessage() {
        System.out.println(ADD_NEW_TASK_MESSAGE);
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + getFullDescription());
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String timing) {
        this.description = description;
        this.timing = timing;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(MARK_AS_DONE_MESSAGE);
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + getFullDescription());

    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println(MARK_AS_UNDONE_MESSAGE);
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + getFullDescription());
    }

    public void setIsDone() {
        this.isDone = true;
    }
}
