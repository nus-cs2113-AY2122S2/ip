package duke.Commands;

import duke.exception.IndexOutOfRangeException;
import duke.util.PatternGenerator;

/**
 * Exit the program.
 */

public class ExitCommand extends Command{

    @Override
    public void execute() {
        PatternGenerator.generateArrows();
        System.out.println("Goodbye! Can't wait to see you soon. :D");
        PatternGenerator.generateLine();
    }
}
