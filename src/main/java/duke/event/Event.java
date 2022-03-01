package duke.event;
import duke.task.Task;
import org.json.simple.JSONObject;

public class Event extends Task {
    private String time;
    private String symbol = "E";
    private String TYPE = "EVENT";

    public Event(String description, String time) {
        super(description);
//        System.out.println(time);
        if (time == null || time.length() < 2){
            this.time = "";
        } else {
            this.time = time.substring(2);
        }

    }


    @Override
    public String getStatus() {
        String taskStr = super.getStatus();
        return String.format("[%s]%s (at: %s)", this.symbol, taskStr, this.time);
    }
    @Override
    public JSONObject serialize() {
        JSONObject task = super.serialize();
        task.put("time", this.time);
        task.put("type", this.TYPE);
        return task;
    }
}
