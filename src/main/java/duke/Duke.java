package duke;

import java.io.IOException;

/**
 * Starts the program.
 */
public class Duke {

    final static String FILE_NAME = "/Users/aimanimtiaz/software-engineering/ip/data/duke.txt";

    /**
     * Runs the main duke program using the Runner class.
     *
     * @param args arguments to run the program, can be left empty
     * @throws IOException exception could be thrown if reading or writing from a file is not successful
     */
    public static void main(String[] args) throws IOException {
        Runner.run();
    }
}
