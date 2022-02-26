package Superhero.wordlist;

/**
 * Represents a Vocab that has to be learnt within a certain period of time
 */
public class Event extends Vocabulary {
    private String eventTime;

    /**
     * Constructor for Event
     * @param word word to add
     * @param eventTime time span where word must be learnt
     */
    public Event(String word, String eventTime) {
        super(word);
        this.eventTime = eventTime;
    }

    /**
     * Create string representation of Event object
     * @return string representation of Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + eventTime + ")";
    }

    /**
     * Create string representation of Event object to be saved in file
     * @return string representation of Event object to be saved in file
     */
    @Override
    public String fileFormatString() {
        String mark;
        if (isDone()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return "E/" + mark + "/" + getWord() + "/" + eventTime;
    }
}