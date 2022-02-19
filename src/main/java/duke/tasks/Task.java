package duke.tasks;

import duke.exception.IndexOutOfRangeException;
import duke.util.PatternGenerator;

import java.util.ArrayList;


public class Task {
    protected String description;
    protected boolean isDone;

    private static int taskCount = 0;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon(){
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getStatusNumber(){
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }


    public String toString(){
        return "[" + this.getStatusIcon() + "]" + this.description;
    }


    public String getCategory(){
        return "TK";
    }

    public String getDescription(){
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

}
