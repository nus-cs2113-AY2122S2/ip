package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for the ToDo object. ToDos are Tasks that do not have any specific due date.
 */

public class ToDo extends Task {
     
    /**
     * Constructor for the ToDo object.
     * 
     * @param description A String describing the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method used to show that the Task is an ToDo.
     * 
     * @return A String "T".
     */
    public String getType() {
        return "T";
    }

    /**
     * Un-used method from Task
     */
    public LocalDate getStartDate() {
        return null;
    }

    /**
     * Un-used method from Task
     */
    public LocalDate getEndDate() {
        return null;
    }

    /**
     * Un-used method from Task
     */
    public String getDateTime() {
        return null;
    }

    /**
     * The method to represent the ToDo object as a String
     * 
     * @return String that contains the information of the ToDo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}