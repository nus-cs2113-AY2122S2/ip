package shrek.commands;

import shrek.constant.Indexes;
import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;
import shrek.data.ListOfTasks;

public class MarkCommand {
    public static void markTask(String indexOfList) throws InvalidCommandException {
        try {
            if (!ListOfTasks.lists.get(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION).getMark()) {
                ListOfTasks.lists.get(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION).setMark();
                System.out.println("So you've done this task, that's great I guess?");
            } else {
                System.out.println("You have done this task already!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Input of mark must be a number!", ErrorCount.errorCount);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You do not have that many items in the list!", ErrorCount.errorCount);
        }
        System.out.println(ListOfTasks.lists.get(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION));
    }

    public static void unmarkTask(String indexOfList) throws InvalidCommandException {
        try {
            if (ListOfTasks.lists.get(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION).getMark()) {
                System.out.println("What do you mean you've undone");
                ListOfTasks.lists.get(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION).setUnmark();
            } else {
                System.out.println("How can you undo something you've never did?");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Input of unmark must be a number!", ErrorCount.errorCount);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You do not have that many items in the list!", ErrorCount.errorCount);
        }
        System.out.println(ListOfTasks.lists.get(Integer.parseInt(indexOfList) + Indexes.LIST_INDEX_CORRECTION));
    }
}
