package shrek.commands;

import shrek.constant.Indexes;
import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;
import shrek.task.UserContent;
import shrek.data.ListOfTasks;

public class DeleteCommand {
    public static void deleteFromList(String indexOfList) throws InvalidCommandException {
        UserContent taskToBeRemoved;
        try {
            taskToBeRemoved = ListOfTasks.lists.get(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION);
            ListOfTasks.lists.remove(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You cannot delete whats not there!", ErrorCount.errorCount);
        }
        System.out.println("This task is gone, reduced to ashes:");
        System.out.println(taskToBeRemoved);
        System.out.println(ListOfTasks.lists.size() + " task(s) remain");
    }
}
