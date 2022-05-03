package duke.task;

import org.json.simple.JSONObject;

public class Task {

    protected String description;
    protected boolean isDone;
    private String TYPE = "TASK";
    private final String symbol = "-";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }


    public JSONObject serialize() {
        JSONObject task = new JSONObject();
        task.put("type", this.TYPE);
        task.put("description",this.description);
        task.put("isDone",this.isDone);
        return task;
    }
}