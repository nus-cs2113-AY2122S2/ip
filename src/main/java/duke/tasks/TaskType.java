package duke.tasks;

public enum TaskType {
    Todo("T"),
    Deadline("D"),
    Event("E");

    private String abbreviation;
    TaskType(String desc) {
        this.abbreviation =desc;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static TaskType fromString(String text) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.getAbbreviation().equals(text)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException();
    }
}
