public class Event extends Task{
    private String time;
    private String symbol = "E";
    public Event(String description, String time){
        super(description);
        this.time = time.substring(2);
    }



    @Override
    public String getStatus(){
        String taskStr = super.getStatus();
        return String.format("[%s]%s (at: %s)",this.symbol,taskStr, this.time);
    }
}
