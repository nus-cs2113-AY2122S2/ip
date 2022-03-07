import exceptions.DukeException;
import tasks.TaskList;

/**
 * Duke is a smart task manager.
 */
public class Duke {


    /**
     * The main function of Duke
     *
     * @param args The commandline argument
     */
    public static void main(String[] args) {
        try {
            TaskList dukeTaskList = new TaskList();
        } catch (DukeException e) {
            System.out.println(e);
        }
        Controller dukeController = new Controller();
        dukeController.listen();
    }
}
