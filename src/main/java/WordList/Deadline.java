package WordList;

public class Deadline extends Vocabulary {
    private String date;

    public Deadline(String word, String date) {
        super(word);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + date + ")";
    }
}
