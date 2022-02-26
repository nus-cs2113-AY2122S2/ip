package Superhero.wordlist;

/**
 * Abstract class to be extended by ToLearn, Deadline, Event.
 * Represents words that have to be learnt and a boolean attribute (isDone) to
 * show if the word has been learnt.
 */
public abstract class Vocabulary {
    private String word;
    private boolean isDone = false;

    /**
     * Constructor for Vocabulary
     * @param word
     */
    public Vocabulary(String word) {
        this.word = word;
    }

    /**
     * Get word of Vocab
     * @return word of Vocab
     */
    public String getWord() {
        return word;
    }

    /**
     * Get Boolean representation of whether word has been learnt
     * @return value of whether word has been learnt
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Set Boolean representation of whether word has been learnt
     * @param isDone value of whether word has been learnt
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Create default string representation of all Vocabulary
     * @return string representation of Vocabulary
     */
    @Override
    public String toString() {
        String mark;
        if (isDone()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return "[" + mark + "] " + word;
    }

    /**
     * Abstract method for string representation of all Vocabulary to be saved in the file
     * @return string representation of all Vocabulary to be saved in the file
     */
    public abstract String fileFormatString();
}
