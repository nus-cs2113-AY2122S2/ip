package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToDo extends Task {
     
    public ToDo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    public LocalDate getStartDate() {
        return null;
    }

    public LocalDate getEndDate() {
        return null;
    }

    public String getDateTime() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}