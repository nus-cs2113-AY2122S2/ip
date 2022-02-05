
package duke.event;
import duke.task.Task;

public class ToDo extends Task {

    private String symbol = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        String taskStr = super.getStatus();
        return String.format("[%s]%s", this.symbol, taskStr);
    }
}
