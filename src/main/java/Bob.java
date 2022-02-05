import static util.controller.Command.greetings;
import static util.controller.Command.listenAndExecuteCommands;
import static util.controller.Command.goodBye;

public class Bob {
    public static void main(String[] args) {
        greetings();
        listenAndExecuteCommands();
        goodBye();
    }
}
