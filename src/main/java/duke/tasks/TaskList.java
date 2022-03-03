package duke.tasks;

import duke.exceptions.InputLengthException;
import duke.exceptions.UnreachableTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<ToDo> toDos;
    public int taskCounter;

    public TaskList() {
        toDos = new ArrayList<ToDo>();
    }

    public void add(ToDo todo) {
        toDos.add(todo);
        taskCounter++;
    }

    public void remove(ToDo todo) {
        toDos.remove(todo);
        taskCounter--;
    }

    public void remove(int index) {
        toDos.remove(toDos.get(index));
        taskCounter--;
    }

    public ToDo get(int index) {
        return toDos.get(index);
    }

}
