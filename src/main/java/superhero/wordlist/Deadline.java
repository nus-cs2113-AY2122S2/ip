package Superhero.wordlist;

/**
 * Represents a Vocab that has to be learnt by a certain date
 */
public class Deadline extends Vocabulary {
    private String date;

    /**
     * Constructor for Deadline
     * @param word word to add
     * @param date date to learn by
     */
    public Deadline(String word, String date) {
        super(word);
        this.date = date;
    }

    /**
     * Create string representation of Deadline object
     * @return string representation of Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + date + ")";
    }

    /**
     * Create string representation of Deadline object to be saved in file
     * @return string representation of Deadline object to be saved in file
     */
    @Override
    public String fileFormatString() {
        String mark;
        if (isDone()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return "E/" + mark + "/" + getWord() + "/" + date;
    }
}
