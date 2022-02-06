package duke;

public class Deadline extends Todo {
    public String taskKind = "[D]";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        String indicator;
        if (this.isDone){
            indicator = "[X]";
        } else indicator = "[ ]";
        String message = "[D]" + indicator + description
                + " (by: " + by + ")";
        return message;
    }
    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }
}
