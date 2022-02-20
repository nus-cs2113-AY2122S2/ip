package shrek.commands;

import shrek.constant.Indexes;
import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;
import shrek.helper.Time;
import shrek.storage.SaveToOutput;
import shrek.task.Deadlines;
import shrek.task.Events;
import shrek.task.ToDo;
import shrek.data.TaskList;

public class AddCommand {

    public static boolean isEitherStringEmpty(String a, String b) {
        return (a.equals("") || b.equals(""));
    }

    public static void addDeadlineOrEventToList(String input, String taskTimeReference)
            throws InvalidCommandException {
        String[] splitDeadlineOrEventInputs;
        try {
            splitDeadlineOrEventInputs = input.split(taskTimeReference, Indexes.NUMBER_OF_TERMS_IN_SPLIT);
            if (splitDeadlineOrEventInputs.length > Indexes.NUMBER_OF_TERMS_IN_SPLIT) {
                throw new InvalidCommandException("Did you add in more than one \""
                        + taskTimeReference + "\"?", ErrorCount.errorCount);
            } else if (isEitherStringEmpty(splitDeadlineOrEventInputs[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING],
                    splitDeadlineOrEventInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING])) {
                throw new InvalidCommandException("Did you forget to add in the time or task?", ErrorCount.errorCount);
            }
            String Datetime = Time.modifyDatetime(splitDeadlineOrEventInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
            if (taskTimeReference.equals("/at ")) {
                TaskList.lists.add(new Events(splitDeadlineOrEventInputs[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING],
                        Datetime));
            } else {
                TaskList.lists.add(new Deadlines(splitDeadlineOrEventInputs[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING],
                        Datetime));
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            if (!input.contains(taskTimeReference)) {
                throw new InvalidCommandException("Did you forget \"" + taskTimeReference + "\"?", ErrorCount.errorCount);
            }
            throw new InvalidCommandException("Did you forget to add in the time or task?", ErrorCount.errorCount);
        }
    }

    public static void addToList(String input, String taskName, boolean toPrint) {
        boolean isTaskRanSuccessful = true;
        switch (taskName) {
        case "todo":
            TaskList.lists.add(new ToDo(input));
            break;
        case "deadline":
            addDeadlineOrEventToList(input, "/by ");
            break;
        case "event":
            addDeadlineOrEventToList(input, "/at ");
            break;
        default:
            System.out.println("Did you type the task properly? Re-enter your task");
            isTaskRanSuccessful = false;
        }
        if (isTaskRanSuccessful) {
            if (toPrint) {
                System.out.println("Done putting this in the list:");
                System.out.println(TaskList.lists.get(TaskList.lists.size() + Indexes.LIST_INDEX_CORRECTION));
                System.out.println("Go do the " + TaskList.lists.size() + " task(s)!!");
                SaveToOutput.saveData();
            }

        }
    }
}
