package duke.task;
/**
 * Represents a event task
 */
public class Event extends Task{
        protected String at;
    /**
     * Constructor for Event
     *
     * @param description
     *            the description of the event
     * @param at
     *            the location of the event
     */
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

