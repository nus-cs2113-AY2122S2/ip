import commands.Command;
import commands.ExecutedCommandResults;
import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import java.util.Scanner;

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
                Ui.printTextBetweenLines("Whoopsies! I dont know what you're talking about! Try again!\n");
            } catch (MissingDescriptionException e) {
                Ui.printTextBetweenLines("I think you forgot some stuff there for that command! Try again!\n");
            }
            System.out.println();
            line = userInput.nextLine();
        }
        Storage.writeTasksToFile(tasks);
        Ui.endDuke();
    }
}
