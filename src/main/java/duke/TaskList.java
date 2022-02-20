package duke;

import duke.exception.AdditionalException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<Task> getList() throws AdditionalException {
        if (listOfTasks.isEmpty()) {
            throw new AdditionalException("YAY!!! you do not have any tasks at the moment hehe");
        }
        return listOfTasks;
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

    public ArrayList<Task> getListOfSameDates(LocalDate date) {
        int count = 0;
        ArrayList<Task> listOfTasksWithSameDate = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            addToList(listOfTasksWithSameDate, date, i);
        }
        return listOfTasksWithSameDate;
    }

    private void addToList(ArrayList<Task> tasksWithSameDate, LocalDate date, int index) {
        Task task = listOfTasks.get(index);
        LocalDate dateOfTask;
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            dateOfTask = deadline.getDate();
            addIfSameDate(tasksWithSameDate, date, dateOfTask, deadline);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            dateOfTask = event.getDate();
            addIfSameDate(tasksWithSameDate, date, dateOfTask, event);
        } else {
            return;
        }

    }

    private void addIfSameDate(ArrayList<Task> tasksWithSameDate, LocalDate date, LocalDate dateOfTask, Task task) {
        if (date.equals(dateOfTask)) {
            tasksWithSameDate.add(task);
        }
    }
}
