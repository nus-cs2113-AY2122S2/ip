package superhero.wordlist;

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
