package duke.tasks;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Asserts that a call to the ArrayList constructor using the ArrayList(Collection c) signature specifically passes in ArrayList<Task> as the collection.
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        super(taskList);
    }

}
