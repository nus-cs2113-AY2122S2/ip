package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the command which is to be executed.
 */
public abstract class Command {

    public abstract boolean isBye();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException, IOException;

    public abstract void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException;

    /**
     * This is the getDescription function that is used to get the description for todo commands.
     *
     * @param typeOfTask This is the type of task.
     * @param fullCommand This is the full command that the user input
     * @return The description of the tasks.
     */
    public String getDescription(String typeOfTask, String fullCommand) {
        int lowerBound = typeOfTask.length();
        int upperBound = fullCommand.length();
        String description = fullCommand.substring(lowerBound, upperBound);
        return description.trim();
    }

    /**
     * This is the getDescription method that is used to get the description for deadline and event commands.
     *
     * @param typeOfTask This is the type of task.
     * @param preposition This is the preposition that is used in the commands.
     * @param fullCommand This is the full command that the user input.
     * @return The description of the tasks.
     */
    public String getDescription(String typeOfTask, String preposition, String fullCommand) throws AdditionalException {
        int lowerBound = typeOfTask.length();
        int upperBound = fullCommand.indexOf(preposition);
        if (upperBound == -1) {
            throw new AdditionalException("You don't know basic grammar or what?");
        }
        String description = fullCommand.substring(lowerBound, upperBound);
        return description.trim();
    }

    /**
     * This is the getLocation method that is used to get the location for event commands.
     *
     * @param firstPreposition This is the first preposition used in the commands.
     * @param secondPreposition This is the second preposition used in the commands.
     * @param fullCommand This is the full command that the user input.
     * @return The location of the events.
     */
    public String getLocation(String firstPreposition, String secondPreposition, String fullCommand)
            throws AdditionalException {
        int lowerBound = fullCommand.indexOf(firstPreposition) + firstPreposition.length();
        int upperBound = fullCommand.indexOf(secondPreposition);
        if (upperBound == -1) {
            throw new AdditionalException("The event has to be ON a certain date right?");
        }
        String location = fullCommand.substring(lowerBound, upperBound);
        return location.trim();
    }

    /**
     * This is the getDate method that is used to get the date for deadline and event commands.
     *
     * @param preposition This is the preposition used in the commands.
     * @param fullCommand This is the full command that the user input.
     * @return The date of the tasks.
     */
    public String getDate (String preposition, String fullCommand) {
        int lowerBound = fullCommand.indexOf(preposition) + preposition.length();
        int upperBound = fullCommand.length();
        String date = fullCommand.substring(lowerBound, upperBound);
        return date.trim();
    }

    /**
     * This is the getIndex method that is used to get the index for mark and unmark commands.
     *
     * @param fullCommand This is the full command that the user input.
     * @return The index to mark or unmark.
     * @throws AdditionalException If the user did not input exactly 1 index.
     */
    public int getIndex(String fullCommand) throws AdditionalException {
        String[] words = fullCommand.split(" ");
        if (words.length != 2) {
            throw new AdditionalException("Please input the index and only the index");
        }
        int index = Integer.parseInt(words[1]) - 1;
        return index;
    }

}
