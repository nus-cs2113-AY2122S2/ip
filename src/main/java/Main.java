import sora.Sora;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Start Sora
        try {
            Sora sora = new Sora();
            sora.startContinuousUserPrompt();
        } catch (IOException e) {
            // Exit application
            System.out.println("[Main class] An IOException has occurred and Sora needs to terminate." +
                    "\nSorry about that");
            System.exit(-1);
        }
    }
}
