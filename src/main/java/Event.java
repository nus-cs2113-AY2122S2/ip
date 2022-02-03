class Event extends Task {
    
    protected String time;

    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    protected String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}