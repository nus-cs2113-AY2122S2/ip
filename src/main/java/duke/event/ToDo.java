
package duke.event;
import duke.task.Task;
import org.json.simple.JSONObject;

public class ToDo extends Task {

    private final String symbol = "T";
    private String TYPE = "TODO";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        String taskStr = super.getStatus();
        return String.format("[%s]%s", this.symbol, taskStr);
    }

    @Override
    public JSONObject serialize() {
        JSONObject task = super.serialize();
        task.put("type", this.TYPE);
        return task;
    }
}
