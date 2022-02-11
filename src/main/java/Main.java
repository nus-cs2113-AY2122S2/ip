import bot.SailfishController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            SailfishController fish = new SailfishController();
            fish.takeControl();
        } catch (IOException e) {
            System.out.printf("There was an error reading/saving the database: %s\n" +
                    "Stopping application...\n", e.getMessage());
        }
    }
}
