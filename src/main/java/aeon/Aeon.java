package aeon;

import aeon.controller.Command;
import aeon.controller.UI;
/**
 * Represents Aeon itself, displaying a welcome message before making sense
 * of user inputs that are typed in. Upon exit, displays goodbye message.
 */
public class Aeon {

    public static void main(String[] args) {
            UI.printWelcomeMessage();
            Command.CommandProcessor();
            UI.printGoodbyeMessage();
    }




}

