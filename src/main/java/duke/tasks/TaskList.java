package duke.tasks;

import duke.exceptions.InputLengthException;
import duke.exceptions.UnreachableTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.util.ArrayList;

public class TaskList {
    /** List containing all tasks */
    private ArrayList<ToDo> toDos;
    /** Number of tasks in list */
    public int taskCounter;

    /**
     * Constructor of the TaskList class
     *
     * @return An instance of TaskList
     */
    public TaskList() {
        toDos = new ArrayList<ToDo>();
    }

    /**
     * Adds a task to a given ToDo object
     *
     * @param todo A ToDo object to add a task to
     */
    public void add(ToDo todo) {
        toDos.add(todo);
        taskCounter++;
    }

    /**
     * Removes a task to a given ToDo object
     * Uses a ToDo as a parameter
     *
     * @param todo A ToDo object to remove a task from
     */
    public void remove(ToDo todo) {
        toDos.remove(todo);
        taskCounter--;
    }

    /**
     * Removes a task to a given ToDo object
     * Uses an index as a parameter
     *
     * @param index An index accessed to remove its corresponding ToDo from the TaskList
     */
    public void remove(int index) {
        toDos.remove(toDos.get(index));
        taskCounter--;
    }

    /**
     * Returns a specified ToDo object in the list
     *
     * @param index The index of TaskList to return the ToDo object from
     * @returns A ToDo object specified by user
     */
    public ToDo get(int index) {
        return toDos.get(index);
    }

}
