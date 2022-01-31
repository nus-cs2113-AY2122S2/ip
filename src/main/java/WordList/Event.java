package WordList;

public class Event extends Vocabulary {
    private String eventTime;

    public Event(String word, String eventTime) {
    super(word);
    this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + eventTime + ")";
    }
}