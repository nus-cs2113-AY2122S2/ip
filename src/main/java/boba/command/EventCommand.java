package boba.command;

import boba.data.Storage;
import boba.exception.BobaException;
import boba.response.Ui;
import boba.task.Event;
import boba.task.TaskList;

public class EventCommand extends Command{

    private Event event;

    public EventCommand(String description, String at) {
        event = new Event(description, at);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(event);
            ui.printAddSuccess(event, tasks.size());
            storage.save(tasks);
        } catch (BobaException e) {
            ui.printLimitError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
