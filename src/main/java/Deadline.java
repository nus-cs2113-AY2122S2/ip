public class Deadline extends Task {
    private final String time;
    private final String symbol = "D";

    public Deadline(String description, String time) {
        super(description);
        this.time = time.substring(2);
    }


    @Override
    public String getStatus() {
        String taskStr = super.getStatus();
        return String.format("[%s]%s (by: %s)", this.symbol, taskStr, this.time);
    }
}
