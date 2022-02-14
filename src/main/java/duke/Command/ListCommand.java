package duke.Command;
import duke.TaskList;
import duke.UI;
import duke.save;

public class ListCommand extends Command {
        public void execute(TaskList tasks, UI ui, save save) {
            ui.printTaskList(tasks);
        }
}
