class Deadline extends Task {

    protected String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}