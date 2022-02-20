package shrek.commands;

import shrek.exception.InvalidCommandException;
import shrek.task.Deadlines;
import shrek.task.Events;
import shrek.task.UserContent;
import shrek.constant.Indexes;
import shrek.data.TaskList;
import shrek.data.ErrorCount;

import java.util.ArrayList;

public class FindCommand {
    public static void findTaskOrTime(String input) {
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

    public static void findTask(String inputToSearchFor) {
        ArrayList<UserContent> listOfFoundTasks = new ArrayList<>();
        for (UserContent item : TaskList.lists) {
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

    public static void findTime(String inputToSearchFor) {
        ArrayList<UserContent> listOfFoundTimes = new ArrayList<>();
        for (UserContent item : TaskList.lists) {
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
