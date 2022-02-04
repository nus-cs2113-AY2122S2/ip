package superhero.WordList;

public class Vocabulary {
    private String word;
    private boolean isDone = false;

    public Vocabulary(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

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
}
