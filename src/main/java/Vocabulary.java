public class Vocabulary {
    private String word;
    private boolean isConfirmed = false;

    public Vocabulary(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public boolean isConfirmed() {
        return this.isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}
