import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class FindCommand extends Command {
    String searchInput;

    public FindCommand(String searchInput) {
        this.searchInput = searchInput;
    }

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
