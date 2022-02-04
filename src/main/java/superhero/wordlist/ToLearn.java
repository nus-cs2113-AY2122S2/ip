package superhero.wordlist;

public class ToLearn extends Vocabulary {
    public ToLearn(String word) {
        super(word);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
