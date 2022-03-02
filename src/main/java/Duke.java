import commands.Command;
import commands.ExecutedCommandResults;
import exceptions.InvalidCommandException;
import exceptions.InvalidIndexException;
import exceptions.MissingDescriptionException;
import tasks.TaskList;
import java.util.Scanner;
import static common.Message.INVALID_COMMAND_MESSAGE;
import static common.Message.MISSING_DESCRIPTION_MESSAGE;
import static common.Message.INVALID_INDEX_MESSAGE;

/**
 * The Duke class starts, runs and stops the program
 *
 * @author Haziq
 * @version 0.2
 */
public class Duke {

    public static void main(String[] args) {
        Ui.startDuke();

        Scanner userInput = new Scanner(System.in);
        String line;
        TaskList tasks = new TaskList();
        Storage.storageSetup(tasks);
        line = userInput.nextLine();
        while (!"bye".equals(line)) {
            try {
                Command command = Parser.parseInput(line);
                ExecutedCommandResults results = command.executeCommand(tasks);
                Ui.printExecutedCommandResults(results);
            } catch (InvalidCommandException e) {
                Ui.printTextBetweenLines(INVALID_COMMAND_MESSAGE);
            } catch (MissingDescriptionException e) {
                Ui.printTextBetweenLines(MISSING_DESCRIPTION_MESSAGE);
            } catch (InvalidIndexException e) {
                Ui.printTextBetweenLines(INVALID_INDEX_MESSAGE);
            }
            System.out.println();
            line = userInput.nextLine();
        }
        Storage.writeTasksToFile(tasks);
        Ui.endDuke();
    }
}
