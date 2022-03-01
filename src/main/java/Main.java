import bot.SailfishUI;

import java.io.IOException;

/**
 * Bot starts here.
 */
public class Main {
    public static void main(String[] args) {
        try {
            SailfishUI fish = new SailfishUI();
            fish.takeControl();
        } catch (IOException e) {
            System.out.printf("There was an error reading/saving the database: %s\n" +
                    "Stopping application...\n", e.getMessage());
        }
    }
}
