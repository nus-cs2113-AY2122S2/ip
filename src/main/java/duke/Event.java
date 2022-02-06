package duke;

public class Event extends Todo {
    public String taskKind = "[E]";
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString(){
        String indicator;
        if (this.isDone){
            indicator = "[X]";
        } else indicator = "[ ]";
        String message = "[D]" + indicator + description
                + " (at: " + at + ")";
        return message;
    }
    public void setAt(String at) {
        this.at = at;
    }
    public String getAt() {
        return at;
    }
}
