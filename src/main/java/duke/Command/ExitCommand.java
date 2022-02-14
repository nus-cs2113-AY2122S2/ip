package duke.Command;

import duke.TaskList;
import duke.UI;
import duke.save;

public class ExitCommand extends Command{
    public void execute(TaskList tasks, UI ui, save save) {
        ui.bye();
        save.storeToFile(tasks.getTaskList());
        setIsExit(true);
    }
}
