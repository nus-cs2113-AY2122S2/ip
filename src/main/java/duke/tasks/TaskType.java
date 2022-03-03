package duke.tasks;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private String tag;

    /**
     * Give each TaskType enum an associated tag
     *
     * @param tag single char String, representing tag of TaskType
     */
    TaskType(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    /**
     * Returns the proper TaskType Enum from the given tag
     *
     * @param text single char String, the tag of TaskType
     * @return the Enum representing the full name of the type of task
     */
    public static TaskType fromString(String text) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.getTag().equals(text)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException();
    }
}
