import java.util.ArrayList;

/**
 * Identify wish task related to what user wish to determine.
 */
import static java.util.stream.Collectors.toList;

public class FindCommand extends Command {
    String searchInput;

    public FindCommand(String searchInput) {
        this.searchInput = searchInput;
    }

    /**
     * Execute filtering to store all relevant wish task from
     * the list of wish. List of relevant wishes would be printed
     * out to notify the user of search results.
     *
     * @param taskList    contains all wish task.
     * @param uiManager   assist in printing exit message.
     * @param fileManager manages the file.
     * @throws DukeWrongCommandException when no search result is being found
     */
    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) throws DukeWrongCommandException {
        ArrayList<Task> taskFound = (ArrayList<Task>) taskList.stream()
                .filter((t) -> t.getTaskName().toLowerCase().contains(searchInput))
                .collect(toList());
        if (taskFound.isEmpty()) {
            uiManager.printMessage("Opps! There is no search result found in wishlist");
            return;
        }
        uiManager.printList(taskFound);
    }
}
