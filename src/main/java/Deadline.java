public class Deadline extends ToDo {
    protected String doBy;

    public Deadline(String description, String doBy) {
        super(description);
        this.doBy = doBy;
    }

    @Override
    public String getDescription() {
        String completeDescription = description + doBy;
        return completeDescription;
    }

    @Override
    public String getStatusIcon() {
        String status = (isDone ? "X" : " ");
        String finalString = "[D][" + status + "]";
        return finalString;
    }
}
