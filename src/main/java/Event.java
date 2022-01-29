public class Event extends Task{
    public static final String EVENT_LOGO = "[E]";
    public static final String AT_FIRST_HALF = " (at: ";
    public static final String AT_SECOND_HALF = ")";
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return EVENT_LOGO + super.toString() + AT_FIRST_HALF + at + AT_SECOND_HALF;
    }
}
