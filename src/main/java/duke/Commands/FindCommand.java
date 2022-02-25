package duke.Commands;

import duke.exception.IndexOutOfRangeException;
import duke.tasks.Task;
import duke.util.PatternGenerator;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Find a task by a keyword.
 */

public class FindCommand extends Command{

    protected String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }
    @Override
    public void execute() throws IndexOutOfRangeException {
        ArrayList<Task> tasksFound = new ArrayList<>();
        int i = 1;
        tasksFound = taskList.findTask(keyword);
        if (tasksFound.size() == 0) {
            System.out.println("Im sorry :(. It seems no task matches your input.");
        }
        else {
            PatternGenerator.generateLine();
            System.out.println("Here are the matching tasks in your list: ");
            for (Task task : tasksFound){
                System.out.println(i + "." +task.toString());
            }
            PatternGenerator.generateLine();
        }
    }
}
