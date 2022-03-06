package duke;

/**
 * The main UI which helps print relevant output.
 */
public class UI {

    final static String DASHED_LINE = "\t____________________________________________________________";

    /**
     * When user requests to mark a task, this method not only marks the
     * task as done, but also prints out the relevant message.
     *
     * @param index the index of the task to mark
     */
    static void markTask(int index){
        TaskList.taskList.get(index - 1).setDone(true);
        String message = DASHED_LINE + "\n" +
                "\t Nice! I've marked this task as done: \n" +
                "\t \t" + TaskList.taskList.get(index - 1).toString() + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    /**
     * Marks a task as not done and prints out the relevant message as
     * confirmation.
     *
     * @param index the index of the task to unmark
     */
    static void unMarkTask(int index){
        TaskList.taskList.get(index - 1).setDone(false);
        String message = DASHED_LINE + "\n" +
                "\tOK, I've marked this task as not done yet: \n" +
                "\t \t" + TaskList.taskList.get(index - 1).toString() + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    /**
     * Pretty prints the recently added task with a message confimation.
     *
     * @param task the task which was just added to the TaskList
     */
    static void printAddedItem(Task task){
        String message = DASHED_LINE + "\n" +
                "\t Got it. I've added this task:" + "\n" +
                "\t \t" + task.toString() + "\n" +
                "\t Now you have " + TaskList.taskList.size() +
                " tasks in the list." + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    /**
     * Pretty prints the recently deleted task which confirms deletion.
     *
     * @param task
     */
    static void printDeletedItem(Task task){
        String message = DASHED_LINE + "\n" +
                "\t Got it. I've removed this task:" + "\n" +
                "\t \t" + task.toString() + "\n" +
                "\t Now you have " + TaskList.taskList.size() +
                " tasks in the list." + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    /**
     * Prints out all tasks in the TaskList when the user requests to view
     * all tasks present.
     */
    static void printList(){
        System.out.print(DASHED_LINE);
        for (int j = 0; j < TaskList.taskList.size(); j++){
            System.out.print("\n");
            System.out.print("\t" + (j+1) + "." + TaskList.taskList.get(j).toString());
        }
        System.out.println("\n" + DASHED_LINE);
    }


    /**
     * Prints out an error message when the DukeIllegalKeyword exception is thrown.
     *
     * @see DukeIllegalKeyword
     */
    static void printIllegalKeyword(){
        String message = DASHED_LINE + "\n" +
                "\t You typed an illegal keyword." + "\n" +
                "\t I have no idea what you are trying to say. :(" + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    /**
     * Prints out an error message when the DukeIllegalDescription exception is thrown.
     *
     * @see DukeIllegalDescription
     */
    static void printIllegalDescription(){
        String message = DASHED_LINE + "\n" +
                "\t You did not add any description." + "\n" +
                "\t I can't do much. Try again!" + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    /**
     * Prints out an error message when the command cannot be parsed and *any*
     * kind of exception is thrown.
     *
     * @see Parser
     */
    static void printIllegalTerm(){
        String message = DASHED_LINE + "\n" +
                "\t You did not make any sense" + "\n" +
                "\t I can't do much. Try again!" + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    final static String greeting = UI.DASHED_LINE + "\n" +
            "\t Hello! I'm Duke\n" +
            "\t What can I do for you?\n" +
            UI.DASHED_LINE;

    final static String bye = UI.DASHED_LINE + "\n" +
            "\t Bye. Hope to see you again soon!\n" +
            UI.DASHED_LINE;
}
