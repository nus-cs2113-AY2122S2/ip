public class Deadline extends Task {

    private String ddl;

    public Deadline(String description, String ddl) {
        super(description);
        this.ddl = ddl;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon() +"( by: "+ ddl + ")";
    }

    @Override
    public String getDescription() {
        return super.getDescription()+ "( by: "+ ddl + ")";
    }
}