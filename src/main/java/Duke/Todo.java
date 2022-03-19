package Duke;

public class Todo extends Task {
    protected String icon;

    public Todo(String description) {
        super(description);
        this.icon = "T";
    }

    public String getIcon() {
        return icon;
    }
}
