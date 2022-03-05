package duke;

public class UI {

    final static String DASHED_LINE = "\t____________________________________________________________";

    static void markTask(int index){
        TaskList.taskList.get(index - 1).setDone(true);
        String message = DASHED_LINE + "\n" +
                "\t Nice! I've marked this task as done: \n" +
                "\t \t" + TaskList.taskList.get(index - 1).toString() + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    static void unMarkTask(int index){
        TaskList.taskList.get(index - 1).setDone(false);
        String message = DASHED_LINE + "\n" +
                "\tOK, I've marked this task as not done yet: \n" +
                "\t \t" + TaskList.taskList.get(index - 1).toString() + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    static void printAddedItem(Task task){
        String message = DASHED_LINE + "\n" +
                "\t Got it. I've added this task:" + "\n" +
                "\t \t" + task.toString() + "\n" +
                "\t Now you have " + TaskList.taskList.size() +
                " tasks in the list." + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    static void printDeletedItem(Task task){
        String message = DASHED_LINE + "\n" +
                "\t Got it. I've removed this task:" + "\n" +
                "\t \t" + task.toString() + "\n" +
                "\t Now you have " + TaskList.taskList.size() +
                " tasks in the list." + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    static void printList(){
        System.out.print(DASHED_LINE);
        for (int j = 0; j < TaskList.taskList.size(); j++){
            System.out.print("\n");
            System.out.print("\t" + (j+1) + "." + TaskList.taskList.get(j).toString());
        }
        System.out.println("\n" + DASHED_LINE);
    }

    static void printIllegalKeyword(){
        String message = DASHED_LINE + "\n" +
                "\t You typed an illegal keyword." + "\n" +
                "\t I have no idea what you are trying to say. :(" + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    static void printIllegalDescription(){
        String message = DASHED_LINE + "\n" +
                "\t You did not add any description." + "\n" +
                "\t I can't do much. Try again!" + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

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
