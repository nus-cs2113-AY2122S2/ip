package superhero.wordlist;

public class Event extends Vocabulary {
    private String eventTime;

    public Event(String word, String eventTime) {
        super(word);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + eventTime + ")";
    }
}