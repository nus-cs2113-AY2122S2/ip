package shrek.task;

/**
 * Represents all tasks. Contains data of each task.
 */
public class Task {
    private String content;
    private boolean isMark;
    private String taskName;

    public Task(String content) {
        setContent(content);
        setUnmark();
        setTaskName(" ");
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMark() {
        isMark = true;
    }

    public void setUnmark() {
        isMark = false;
    }

    public String getContent() {
        return content;
    }

    public boolean getMark() {
        return isMark;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }


    @Override
    public String toString() {
        String mark;
        if (this.getMark()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return ("[" + this.taskName + "]" + "[" + mark + "] " + getContent());
    }
}
