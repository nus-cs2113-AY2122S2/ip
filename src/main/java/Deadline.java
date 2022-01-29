public class Deadline extends Task {

    public Deadline(String description, String timing) {
        super(description, timing);
    }

    @Override
    public char typeOfTask() {
        return 'D';
    }

    @Override
    public String fullDescription() {
        return description + " (by: " + timing + ")";
    }
}
