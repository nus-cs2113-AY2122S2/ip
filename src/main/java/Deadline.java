public class Deadline extends Task {

    private String ddl;

    public Deadline(String description, String ddl) {
        super(description);
        this.ddl = ddl;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon() ;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+ "( by: "+ ddl + ")";
    }

    @Override
    public String saveInfo(){
        String sep = " / ";
        return "D" + sep + super.saveInfo() + this.ddl;
    }
}