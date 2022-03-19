package taskitems.task;

import java.util.ArrayList;
import helper.Ui;
public class TaskList {
    public ArrayList<Task> taskList = new ArrayList<>();
    public int size = 0;
    private Ui ui = new Ui();

    public void add (Task task) {
        taskList.add(task);
        size++;
        ui.print("added " + taskList.get(size-1));
        ui.printCont("You now have " + size + " tasks in the list.");
    }

    public void add (Task task, boolean Backend) {
        taskList.add(task);
        size++;
    }

    public void delete (int num) {
        taskList.remove(num - 1);
        size--;
    }

    public Task getTask(int num) {
        return taskList.get(num - 1);
    }

    public void mark (int num) {
        taskList.get(num - 1).setMarked(true);
    }

    public void unmark (int num) {
        taskList.get(num - 1).setMarked(false);
    }

}
