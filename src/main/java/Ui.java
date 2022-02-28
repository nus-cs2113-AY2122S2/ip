import commands.ExecutedCommandResults;
import common.Message;
import tasks.Task;
import tasks.TaskList;

public class Ui {

    public static void printHorizontalLine() {
        System.out.println(Message.HORIZONTAL_LINE);
    }

    public static void printTextBetweenLines(String text) {
        printHorizontalLine();
        System.out.print(text);
        printHorizontalLine();
    }

    public static void startDuke() {
        System.out.println(Message.GREET_MESSAGE);
    }

    public static void endDuke() {
        printHorizontalLine();
        System.out.println(Message.GOODBYE_MESSAGE);
        printHorizontalLine();
    }

    public static void printExecutedCommandResults(ExecutedCommandResults results) {
        printHorizontalLine();
        System.out.println(results.getCommandMessage());
        TaskList tasks = results.getTasks();
        Task task = results.getTask();

        if (tasks != null) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print("\t" + (i + 1) + ". ");
                System.out.println(tasks.get(i));
            }
        } else if (task != null) {
            System.out.println("\t" + task);
        }
        printHorizontalLine();
    }
}
