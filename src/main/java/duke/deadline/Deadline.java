package duke.deadline;

import duke.task.Task;
import org.json.simple.JSONObject;

public class Deadline extends Task {
    private final String time;
    private final String symbol = "D";
    private String TYPE = "DEADLINE";

    public Deadline(String description, String time) {
        super(description);
        this.time = time.substring(2);
    }


    @Override
    public String getStatus() {
        String taskStr = super.getStatus();
        return String.format("[%s]%s (by: %s)", this.symbol, taskStr, this.time);
    }

    @Override
    public JSONObject serialize() {
        JSONObject task = super.serialize();
        task.put("time", this.time);
        task.put("type", this.TYPE);
        return task;
    }
}
