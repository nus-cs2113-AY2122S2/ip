public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getDoneIcon() {
        String icon = "[ ] ";
        if (isDone) {
            icon = "[X] ";
        }
        return icon;
    }

    public String toStringInFormat() {
        String formatted = "";
        if (isDone) {
            formatted += String.valueOf(1);
        } else {
            formatted += String.valueOf(0);
        }
        formatted += " | " + description;
        return formatted;
    }

    @Override
    public String toString() {
        return getDoneIcon() + description;
    }
}
