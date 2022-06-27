package shrek.commands;

import shrek.exception.InvalidCommandException;
import shrek.task.Deadlines;
import shrek.task.Events;
import shrek.task.Task;
import shrek.constant.Indexes;
import shrek.data.TaskList;
import shrek.data.ErrorCount;

import java.util.ArrayList;

/**
 * Finds a task or time from the list.
 */
public class FindCommand {
    /**
     * Determines if user input is find task or find time and passes the perimeters to the respective command handlers.
     *
     * @param input user input after "find" command.
     * @throws InvalidCommandException if user mistyped task or time, or did not input the task or time.
     */
    public static void findTaskOrTime(String input) throws InvalidCommandException {
        try {
            String[] splitTaskOrTimeInputs = input.split(" ", Indexes.NUMBER_OF_TERMS_IN_SPLIT);
            if (splitTaskOrTimeInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING].equals("")) {
                throw new InvalidCommandException("Did you forget to input time or task?", ErrorCount.errorCount);
            }
            switch (splitTaskOrTimeInputs[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING]) {
            case "time":
                findTime(splitTaskOrTimeInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
                break;
            case "task":
                findTask(splitTaskOrTimeInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
                break;
            default:
                throw new InvalidCommandException("Did you type time or task wrongly?", ErrorCount.errorCount);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Did you forget to declare the time or task?", ErrorCount.errorCount);
        }
    }

    /**
     * finds all tasks containing the user input, prints the list of all matches.
     *
     * @param inputToSearchFor Represents the term to search for in all tasks.
     */
    public static void findTask(String inputToSearchFor) {
        ArrayList<Task> listOfFoundTasks = new ArrayList<>();
        for (Task item : TaskList.lists) {
            if (item.getContent().contains(inputToSearchFor)) {
                listOfFoundTasks.add(item);
            }
        }
        if (listOfFoundTasks.size() == 0) {
            System.out.println("The task \"" + inputToSearchFor + "\" was not found in the list, sorry!");
        } else {
            Ui.printList(listOfFoundTasks, "Here are the tasks containing \""
                    + inputToSearchFor + "\" in the list");
        }
    }

    /**
     * finds all time containing the user input, prints the list of all matches.
     *
     * @param inputToSearchFor Represents the term to search for in all time.
     * @throws InvalidCommandException if task name is invalid.
     */
    public static void findTime(String inputToSearchFor) throws InvalidCommandException {
        ArrayList<Task> listOfFoundTimes = new ArrayList<>();
        for (Task item : TaskList.lists) {
            String taskName = item.getTaskName();
            switch (taskName) {
            case "T":
                break;
            case "D":
                Deadlines deadlineTask = (Deadlines) item;
                if (deadlineTask.getTaskDueBy().contains(inputToSearchFor)) {
                    listOfFoundTimes.add(item);
                }
                break;
            case "E":
                Events eventTask = (Events) item;
                if (eventTask.getEventOccurAt().contains(inputToSearchFor)) {
                    listOfFoundTimes.add(item);
                }
                break;
            default:
                throw new InvalidCommandException("Not a valid task to find!", ErrorCount.errorCount);
            }
        }
        if (listOfFoundTimes.size() == 0) {
            System.out.println("No task has the deadline or occurs at \"" + inputToSearchFor + "\", sorry!");
        } else {
            Ui.printList(listOfFoundTimes, "Here are the times containing \""
                    + inputToSearchFor + "\" in the list");
        }
    }
}
