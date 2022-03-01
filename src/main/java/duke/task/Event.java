package main.java.duke.task;

public class Event extends Task {
    
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public String getType() {
        return "E";
    }

    public String getDate() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}