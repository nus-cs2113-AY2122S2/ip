import java.util.ArrayList;

/**
 * Identify task type and updates into the list of wish task.
 * Updates to file when new wishes is being made.
 */
public class CreateTaskCommand extends Command {
    private String taskType;
    private String description;
    private String dateInformation;

    public CreateTaskCommand(String taskType, String description, String dateInformation) {
        this.taskType = taskType;
        this.description = description;
        this.dateInformation = dateInformation;
    }

    public CreateTaskCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) throws DukeWrongCommandException {
        switch (taskType) {
        case "T":
            taskList.add(new Todo(description));
            break;
        case "E":
            taskList.add(new Event(description, dateInformation));
            break;
        case "D":
            taskList.add(new Deadline(description, dateInformation));
            break;
        }
        uiManager.printNoted(taskList.get(taskList.size() - 1));
        updateToFile("wishlist.txt", taskList, fileManager);
        uiManager.printNumberOfWish(taskList.size());
    }
}
