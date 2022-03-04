package Interfaces;

import Components.Task;

import java.util.ArrayList;

public interface UI {
    public abstract void helpMessage(String command);
    public abstract void newTaskMessage(Task task);
    public abstract void deleteTaskMessage(Task deletedTask);
    public abstract void findTasksMessage(ArrayList<String> taskStrings);
    public abstract void listTasksMessage();
    public abstract void markMessage(String taskString);
    public abstract void unmarkMessage(String taskString);
    public abstract void unknownCommandMessage();
    public abstract void farewell();
}
