package main.java.duke.task;

import java.time.LocalDate;

/**
 * Abstract class for ToDos, Deadlines, and Events. It contains basic methods for the 
 * 3 sub-classes.
 */
public abstract class Task {

    public String description;
    public Boolean isDone = false;
    public String mark = " ";

    /**
     * Constructor for the Task object.
     * 
     * @param description String of the description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done or not done.
     * 
     * @param isDone Boolean value that marks the task as done or not.
     */
    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Method to return the description of the task.
     * 
     * @return String desciption of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method to find out if the task is done.
     * 
     * @return Boolean value whether the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Method to show whether a task is done with an "X", or " " if not done.
     * 
     * @return A String "X" or " " depending on whether the task is done.
     */
    public String getMark() {
        if (this.isDone()) {
            this.mark = "X";
        } else {
            this.mark = " ";
        }
        return this.mark;
    }

    /**
     * Abstract method to find out the type of task.
     * 
     * @return String "T" for ToDos, String "D" for Deadlines, String "E" for Events.
     */
    public abstract String getType();

    /**
     * Abstract method to get the starting date of a task.
     * 
     * @return A LocalDate of the starting date of a task.
     */
    public abstract LocalDate getStartDate();

    /**
     * Abstract method to get the ending date of a task.
     * 
     * @return A LocalDate of the ending date of a task.
     */
    public abstract LocalDate getEndDate();

    /**
     * Abstract method to get the date and time of a task in the format for writing onto
     * the save file.
     * 
     * @return A String containing the dates and times of the task.
     */
    public abstract String getDateTime();

    /**
     * The method to represent the Task object as a String
     * 
     * @return A partially complete String that shows whether the task is marked as done, and
     *         it's description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getMark(), this.description);
    }
}