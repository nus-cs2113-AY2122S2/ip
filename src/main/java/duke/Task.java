package duke;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the parameter isDone of the task.
     * @param isDone Parameter representing whether the task is done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if the task contains the specific keywords.
     *
     * @param keyword Words to be matched.
     * @return Boolean representing whether the task contains the specific keywords.
     */
    public boolean containsWord(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        return description.equals(task.description);
    }

    /**
     * Returns the task string in a specific format for data saving.
     * @return String of the task.
     */
    public String toStringInSaveFormat() {
        String formatted = "";
        if (isDone) {
            formatted += String.valueOf(1);
        } else {
            formatted += String.valueOf(0);
        }
        formatted += " / " + description;
        return formatted;
    }

    @Override
    public String toString() {
        return getDoneIcon() + description;
    }

    private String getDoneIcon() {
        String icon = "[ ] ";
        if (isDone) {
            icon = "[X] ";
        }
        return icon;
    }
}
