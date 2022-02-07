package duke.task;

public class Event extends Task{
        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

    public Event(String trim, boolean equals, String trim1) {
        super();
    }

    public String getTask() {
            return "[E]" + super.getTask() + " (at: " + at + ")";
        }
}

