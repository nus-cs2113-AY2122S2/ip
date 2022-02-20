package shrek.commands;

import shrek.constant.CommandList;
import shrek.data.ErrorCount;
import shrek.exception.InvalidCommandException;
import shrek.constant.PrintStrings;
import shrek.constant.Indexes;
import shrek.data.ListOfTasks;

public class HandleCommands {
    public static void handleInput(String userInput, boolean toPrint) throws InvalidCommandException {
        if (toPrint) {
            System.out.print(PrintStrings.LINE);
        }
        try {
            String[] splitUserInputs = userInput.split(" ", Indexes.NUMBER_OF_TERMS_IN_SPLIT);
            if (!isCommandInList(splitUserInputs[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING])) {
                throw new InvalidCommandException("Input a command from the list", ErrorCount.errorCount);
            }
            switch (splitUserInputs[Indexes.INDEX_OF_FIRST_ITEM_IN_STRING]) {
            case "list":
                PrintOutput.printList(ListOfTasks.lists, "Go finish these tasks, NOW:");
                break;
            case "find":
                FindCommand.findTaskOrTime(splitUserInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
                break;
            case "unmark":
                MarkCommand.unmarkTask(splitUserInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
                break;
            case "mark":
                MarkCommand.markTask(splitUserInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
                break;
            case "todo":
                AddCommand.addToList(splitUserInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING],
                        "todo", toPrint);
                break;
            case "deadline":
                AddCommand.addToList(splitUserInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING],
                        "deadline", toPrint);
                break;
            case "event":
                AddCommand.addToList(splitUserInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING],
                        "event", toPrint);
                break;
            case "delete":
                DeleteCommand.deleteFromList(splitUserInputs[Indexes.INDEX_OF_SECOND_ITEM_IN_STRING]);
                break;
            default:
                if (splitUserInputs.length < Indexes.NUMBER_OF_TERMS_IN_SPLIT) {
                    throw new InvalidCommandException("Missing input after the command!", ErrorCount.errorCount);
                }
                throw new InvalidCommandException("How did you get here?", ErrorCount.errorCount);
            }
        } catch (InvalidCommandException e) {
            ErrorCount.errorCount++;
        }
        if (toPrint) {
            System.out.print(PrintStrings.LINE);
        }
    }

    public static boolean isCommandInList(String input) {
        for (CommandList str : CommandList.values()) {
            if (str.name().equals(input)) {
                return true;
            }
        }
        return false;
    }
}
