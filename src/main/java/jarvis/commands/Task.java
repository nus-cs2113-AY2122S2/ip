package jarvis.commands;

import jarvis.display.DisplayMessages;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String getTypeIcon() { return "T"; }

    public boolean markAsDone() {
        boolean isTaskDone = this.isDone == true;
        if (isTaskDone) {
            DisplayMessages.unmarkError();
            return false;
        }
        this.isDone = true;
        return true;
    }

    public boolean markAsUndone() {
        boolean isTaskUndone = this.isDone == false;
        if (isTaskUndone) {
            DisplayMessages.markError();
            return false;
        }
        this.isDone = false;
        return true;
    }

    public String getFullTask() {
        return "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
