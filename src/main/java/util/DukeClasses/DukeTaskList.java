package util.DukeClasses;

import util.task.Todo;
import util.task.Deadline;
import util.task.Event;
import util.task.Task;

import java.util.ArrayList;

public class DukeTaskList {
    protected ArrayList<Task> tasks;

    public DukeTaskList() {
        this.tasks = new ArrayList<Task>();
    }

    protected Task getTask(int index) {
        return this.tasks.get(index);
    }

    protected int size() {
        return this.tasks.size();
    }

    protected void mark(int markedItem) {
        ((this.tasks).get(markedItem)).mark();
    }

    protected void unmark(int unmarkedItem) {
        ((this.tasks).get(unmarkedItem)).unmark();
    }

    protected void deleteItem(int index) {
        this.tasks.remove(index);
    }

    protected void addTodo(String todo) {
        this.tasks.add(new Todo(todo));
    }

    protected void addDeadline(String deadline, String by) {
        this.tasks.add(new Deadline(deadline, by));
    }

    protected void addEvent(String event, String at) {
        this.tasks.add(new Event(event, at));
    }
}
