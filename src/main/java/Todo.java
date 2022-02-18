public class Todo extends Task {
    private static String icon = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringInFormat() {
        return icon + " | " + super.toStringInFormat();
    }

    @Override
    public String toString() {
        String taskIcon = "[" + icon + "]";
        return taskIcon + super.toString();
    }
}
