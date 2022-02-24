package vera;


import vera.task.Deadline;
import vera.task.Event;
import vera.task.Task;
import vera.task.Todo;

import java.util.ArrayList;

import static vera.constant.Indexes.TASK_DESCRIPTION_INDEX;
import static vera.constant.Messages.ERROR_SYSTEM_FAULT_MESSAGE;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> savedTasks) {
        tasks = savedTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(String taskDescription, String taskDate, String taskType) {
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new Todo(taskDescription);
            break;
        case "deadline":
            newTask = new Deadline(taskDescription, taskDate);
            break;
        case "event":
            newTask = new Event(taskDescription, taskDate);
            break;
        default:
            System.out.println(ERROR_SYSTEM_FAULT_MESSAGE);
            return;
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task:\n  " + newTask
                + "\nNow you have " + tasks.size() + " task(s) in the list.");
    }

    public void printAllTasks(Ui ui) {
        int printIndex = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(printIndex + ". " + task);
            printIndex++;
        }
        ui.showLine();
        System.out.println("A total of " + (printIndex - 1) + " item(s) have been found!");
    }

    public boolean isTaskDone(int markIndex) {
        return tasks.get(markIndex).isDone();
    }

    public void markTask(int markIndex) {
        tasks.get(markIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(markIndex));
    }

    public void unmarkTask(int markIndex) {
        tasks.get(markIndex).markAsUndone();
        System.out.println("Ok, I've marked this task as"
                + " not done yet:\n  " + tasks.get(markIndex));
    }

    public boolean isTaskAlreadyAdded(String taskDescription) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                return true;
            }
        }
        return false;
    }


    public int findIndexToReplace(String[] filteredTaskContent) {
        int index = -1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription()
                    .equalsIgnoreCase(filteredTaskContent[TASK_DESCRIPTION_INDEX].trim())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void replaceTaskDate(int taskIndexToReplace, String newTaskDate, Ui ui) {
        tasks.get(taskIndexToReplace).resetInput(newTaskDate);
        ui.showLine();
        System.out.println("Done! I've updated this task:\n  " + tasks.get(taskIndexToReplace));
    }

    public boolean isTaskExist(int deleteIndex) {
        return tasks.get(deleteIndex) != null;
    }

    public void removeTask(int deleteIndex) {
        Task taskToBeRemoved = tasks.get(deleteIndex);
        tasks.remove(deleteIndex);
        System.out.println("Okay. I've removed this task:\n  " + taskToBeRemoved
                + "\nNow you have " + tasks.size() + " task(s) in the list.");
    }
}
