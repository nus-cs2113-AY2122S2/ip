public class Deadline extends Task{
    public Deadline(String description){
        super(description);
        this.description = addInDate(description);
        symbol = "[D]";
    }

    public String addInDate(String task){
        int marker = task.indexOf("/");
        String date;
        String description;
        description = task.substring(0,marker);
        date = task.substring(marker+3);

        return description + " (by: " + date + ")";
    }
}
