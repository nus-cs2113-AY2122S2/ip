package duke.tasks;

public enum TaskType {
    TODO('T'),
    DEADLINE('D'),
    EVENT('E');

    private char tag;

    /**
     * Give each TaskType enum an associated tag
     *
     * @param tag single char String, representing tag of TaskType
     */
    TaskType(char tag) {
        this.tag = tag;
    }

    public char getTag() {
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
            String tagString = String.valueOf(taskType.getTag());
            if (tagString.equals(text)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException();
    }
}
