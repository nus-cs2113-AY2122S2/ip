package duke;

import java.time.LocalDate;

public class UI {

    //    static int currentCount = 0;
        final static String DASHED_LINE = "\t____________________________________________________________";

    private static void markTask(int index){
        TaskList.taskList.get(index - 1).setDone(true);
        String message = DASHED_LINE + "\n" +
                "\t Nice! I've marked this task as done: \n" +
                "\t \t" + TaskList.taskList.get(index - 1).toString() + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    private static void unMarkTask(int index){
        TaskList.taskList.get(index - 1).setDone(false);
        String message = DASHED_LINE + "\n" +
                "\tOK, I've marked this task as not done yet: \n" +
                "\t \t" + TaskList.taskList.get(index - 1).toString() + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    private static void printAddedItem(Task task){
        String message = DASHED_LINE + "\n" +
                "\t Got it. I've added this task:" + "\n" +
                "\t \t" + task.toString() + "\n" +
                "\t Now you have " + TaskList.taskList.size() +
                " tasks in the list." + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    private static void printDeletedItem(Task task){
        String message = DASHED_LINE + "\n" +
                "\t Got it. I've removed this task:" + "\n" +
                "\t \t" + task.toString() + "\n" +
                "\t Now you have " + TaskList.taskList.size() +
                " tasks in the list." + "\n" +
                DASHED_LINE;
        System.out.println(message);
    }

    private static void printList(){
        System.out.print(DASHED_LINE);
        for (int j = 0; j < TaskList.taskList.size(); j++){
            System.out.print("\n");
            System.out.print("\t" + (j+1) + "." + TaskList.taskList.get(j).toString());
        }
        System.out.println("\n" + DASHED_LINE);
    }

    static void processLine(String line) throws DukeIllegalKeyword, DukeIllegalDescription {
        if (line.equals("list")){
            printList();
        } else if (line.startsWith("mark")){
            int indexToMark = Integer.parseInt(line.substring(5));
            markTask(indexToMark);
        } else if(line.startsWith("unmark")){
            int indexToUnmark = Integer.parseInt(line.substring(7));
            unMarkTask(indexToUnmark);
        } else if (line.startsWith("todo")){
            if (line.length() < 5){
                throw new DukeIllegalDescription();
            }
            String todoDescription = line.substring(4);
            Todo task = new Todo(todoDescription);
            TaskList.taskList.add(task);
//            currentCount += 1;
            printAddedItem(task);
        } else if (line.startsWith("deadline")){
            if (line.length() < 9 || !line.contains("/by")){
                throw new DukeIllegalDescription();
            }
            int byIndex = line.indexOf("/by");
            String deadlineDescription = line.substring(8, byIndex - 1);
            String by = line.substring(byIndex + 4);
            Deadline task = new Deadline(deadlineDescription, LocalDate.parse(by));
            TaskList.taskList.add(task);
//            currentCount += 1;
            printAddedItem(task);
        } else if (line.startsWith("event")){
            if (line.length() < 6 || !line.contains("/at")){
                throw new DukeIllegalDescription();
            }
            int atIndex = line.indexOf("/at");
            String eventDescription = line.substring(5, atIndex - 1);
            String at = line.substring(atIndex + 4);
            Event task = new Event(eventDescription, at);
            TaskList.taskList.add(task);
//            currentCount += 1;
            printAddedItem(task);
        } else if (line.startsWith("delete")) {
            if (line.length() < 8) {
                throw new DukeIllegalDescription();
            }
            int indexToDelete = Integer.parseInt(line.substring(7));
            Task deletedTask = TaskList.taskList.get(indexToDelete);
            TaskList.taskList.remove(indexToDelete);
            printDeletedItem(deletedTask);
        } else throw new DukeIllegalKeyword();
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
