import commands.ExecutedCommandResults;
import common.Message;
import tasks.Task;
import tasks.TaskList;

/**
 * This class handles almost all the output of text onto the CLI.
 */
public class Ui {

    /**
     * Prints a horizontal line.
     */
    public static void printHorizontalLine() {
        System.out.println(Message.HORIZONTAL_LINE);
    }

    /**
     * Prints text wrapped between two horizontal lines.
     * @param text The text to be wrapped
     */
    public static void printTextBetweenLines(String text) {
        printHorizontalLine();
        System.out.println(text);
        printHorizontalLine();
    }

    /**
     * Prints the opening message.
     */
    public static void startDuke() {
        System.out.println(Message.GREET_MESSAGE);
    }

    /**
     * Prints the closing message.
     */
    public static void endDuke() {
        printHorizontalLine();
        System.out.println(Message.GOODBYE_MESSAGE);
        printHorizontalLine();
    }

    /**
     * Prints out to the ui the relevant information after issuing a command.
     *
     * @param results The instance which carries the relevant information to be printed
     */
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
