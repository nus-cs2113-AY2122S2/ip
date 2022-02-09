package task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.by = by;
    }

    public String getBy() {

        return this.by;
    }

    public void setBy(String by) {

        this.by = by;
    }

    @Override
    public String toString() {

        return super.toString() + " (by: " + by + ")";
    }


    public static String getDeadlineTask(String input) {
        //first space

        int firstSpaceIndex = input.indexOf(" ");
        int byIndex = input.indexOf("/by");
        String deadlineTask = input.substring(firstSpaceIndex+1,byIndex-1).trim();
        if (deadlineTask.length() == 0){
            throw new StringIndexOutOfBoundsException();
        }
        return deadlineTask.trim();
    }

    public static String getDeadlineDate(String input) {
        int byIndex = input.indexOf("/by");
        int deadlineIndex = input.indexOf(" ",byIndex);
        String deadlineDate = input.substring(deadlineIndex + 1).trim();
        if (deadlineDate.length() == 0){
            throw new StringIndexOutOfBoundsException();
        }
        System.out.println(deadlineDate);
        return deadlineDate.trim();
    }
}
