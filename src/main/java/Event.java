public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.type = "E";
        this.at = at;
    }


    public String getAt() {
        return this.at;
    }

    public void setAt(String at) {

        this.at = at;
    }

    @Override
    public String toString() {

        return super.toString() + " (at: " + at + ")";
    }


    public static String getEventTask(String input) {
        //first space
        int firstSpaceIndex = input.indexOf(" ");
        int byIndex = input.indexOf("/at");
        String eventTask = input.substring(firstSpaceIndex+1,byIndex-1);
        //System.out.println(deadlineTask);
        return eventTask.trim();
    }

    public static String getEventDateTime(String input) {
        int atIndex = input.indexOf("/at");
        int eventDateTimeIndex = input.indexOf(" ",atIndex);
        String eventDateTime = input.substring(eventDateTimeIndex + 1);
        //System.out.println(eventDateTime);
        return eventDateTime;
    }
}
