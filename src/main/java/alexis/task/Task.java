package alexis.task;

import alexis.main.Main;

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

    public String getFullDescription() {
        return description;
    }

    public void addNewTask() {
        System.out.println(Main.BORDER_LINE);
        System.out.println(Main.ADD_NEW_TASK_MESSAGE);
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + getFullDescription());
        System.out.println(Main.BORDER_LINE);
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        addNewTask();
    }

    public Task(String description, String timing) {
        this.description = description;
        this.timing = timing;
        this.isDone = false;
        addNewTask();
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(Main.BORDER_LINE);
        System.out.println(Main.MARK_AS_DONE_MESSAGE);
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + getFullDescription());
        System.out.println(Main.BORDER_LINE);

    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println(Main.BORDER_LINE);
        System.out.println(Main.MARK_AS_UNDONE_MESSAGE);
        System.out.println("  [" + typeOfTask() + "][" + getStatusIcon() + "] " + getFullDescription());
        System.out.println(Main.BORDER_LINE);
    }

}
