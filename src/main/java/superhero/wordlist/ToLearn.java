package Superhero.wordlist;

/**
 * Represents a Vocab that has to be learnt with no restriction on timing
 */
public class ToLearn extends Vocabulary {
    /**
     * Constructor for ToLearn
     * @param word word to add
     */
    public ToLearn(String word) {
        super(word);
    }

    /**
     * Create string representation of ToLearn object
     * @return string representation of ToLearn object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Create string representation of ToLearn object to be saved in file
     * @return string representation of ToLearn object to be saved in file
     */
    @Override
    public String fileFormatString() {
        String mark;
        if (isDone()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return "T/" + mark + "/" + getWord();
    }
}
