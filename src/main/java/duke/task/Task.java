package duke.task;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Task {
    private String description;
    public boolean isDone;


    /**
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * @return
     */
    public String getStatusIcon() {
        return ("[" + (isDone ? "X" : " ") + "] "); // mark done task with X
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}