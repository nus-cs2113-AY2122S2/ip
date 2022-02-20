package duke;

import duke.exception.AdditionalException;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void printList() throws AdditionalException {
        if (listOfTasks.isEmpty()) {
            throw new AdditionalException("YAY!!! you do not have any tasks at the moment hehe");
        }
        for (int i = 0; i < listOfTasks.size(); i++) {
            int numbering = i + 1;
            System.out.println(numbering + ". " + listOfTasks.get(i));
        }
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    public int getSize() {
        return listOfTasks.size();
    }

    public void deleteTask(int indexToDelete) {
        listOfTasks.remove(indexToDelete);
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

}
