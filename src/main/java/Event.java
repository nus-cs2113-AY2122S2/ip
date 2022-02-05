public class Event extends Todo{
    protected String timePeriod;

    public Event(String name, String timePeriod) {
        super(name);
        this.timePeriod = timePeriod;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString(){
        if (isMarked) {
            return "[E][X] " + name + " at:(" + timePeriod + ")";
        } else {
            return "[E][ ] " + name + " at:(" + timePeriod + ")";
        }
    }
}
