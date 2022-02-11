package bob;

import static bob.util.controller.Command.greetings;
import static bob.util.controller.Command.listenAndExecuteCommands;
import static bob.util.controller.Command.goodBye;

public class Bob {
    public static void main(String[] args) {
        greetings();
        listenAndExecuteCommands();
        goodBye();
    }
}
