package duke.commands;

public class ListCommand extends Command {
    private static final String LIST_PRE_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nHere are the tasks in your list";
    private static final String LIST_MESSAGE_FORMAT =
            "%d. %s";
    private static final String LIST_POST_MESSAGE_FORMAT =
            "____________________________________________________________";

    /**
     * Lists out all tasks and their corresponding information.
     */
    public void execute() {
        System.out.println(LIST_PRE_MESSAGE_FORMAT);
        for (int i = 0; i<this.taskList.size(); i++) {
            String taskInfo = this.taskList.get(i).toString();
            String formattedTaskInfo = String.format(LIST_MESSAGE_FORMAT, i+1, taskInfo);
            System.out.println(formattedTaskInfo);
        }
        System.out.println(LIST_POST_MESSAGE_FORMAT);
    }
}
