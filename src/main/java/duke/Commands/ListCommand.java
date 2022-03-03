package duke.Commands;

import duke.util.PatternGenerator;

/**
 * Lists all tasks in the task list
 */

public class ListCommand extends Command{
    public void execute(){
        PatternGenerator.generateLine();
        System.out.println("Here are the tasks in your list.");
        taskList.list();
        PatternGenerator.generateLine();
    }
}
