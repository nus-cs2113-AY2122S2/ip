package shrek.commands;

import shrek.constant.Indexes;
import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;
import shrek.task.Task;
import shrek.data.TaskList;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand {
    /**
     * Deletes a task from the list.
     *
     * @param indexOfList Index of task to be deleted.
     * @throws InvalidCommandException if index given is not within the list.
     */
    public static void deleteFromList(String indexOfList) throws InvalidCommandException {
        Task taskToBeRemoved;
        try {
            taskToBeRemoved = TaskList.lists.get(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION);
            TaskList.lists.remove(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You cannot delete whats not there!", ErrorCount.errorCount);
        }
        System.out.println("This task is gone, reduced to ashes:");
        System.out.println(taskToBeRemoved);
        System.out.println(TaskList.lists.size() + " task(s) remain");
    }
}
