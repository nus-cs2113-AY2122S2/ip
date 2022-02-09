package alexis.task;

public class Deadline extends Task {

    public Deadline(String description, String timing) {
        super(description, timing);
    }

    @Override
    public char typeOfTask() {
        return 'D';             //D represents Deadline
    }

    @Override
    public String getFullDescription() {
        return description + " (by: " + timing + ")";
    }
}
