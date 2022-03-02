package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Event;
import Duke.Ui.Ui;

/**
 * Represents the command to add and event task.
 */
public class EventCommand extends Command {
    private Event event;

    public EventCommand(String description, boolean isDone, String date) {
        event = new Event(description, isDone, date);
    }

    /**
     * Indicates whether the program should exit.
     *
     * @return Indication on whether the program should exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks Tasklist containing of  the tasks.
     * @param ui    User interface of Duke.
     * @param storage   Storage of Duke.
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(event);
        System.out.println(Ui.DISPLAY_LINE + System.lineSeparator() + "Okay! I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" + Ui.DISPLAY_LINE);
    }

}
