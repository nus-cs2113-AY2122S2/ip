package vera;

import java.util.Scanner;

import static vera.constant.Messages.GOODBYE_MESSAGE;
import static vera.constant.Messages.WELCOME_MESSAGE;


public class Ui {
    private static Scanner in;
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";
    private static final String LS = System.lineSeparator();
    private static final String LOGO = " __     __             \n"
            + " \\ \\   / /__ _ __ __ _ \n"
            + "  \\ \\ / / _ \\ '__/ _` |\n"
            + "   \\ V /  __/ | | (_| |\n"
            + "    \\_/ \\___|_|  \\__,_|\n";

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void showLine() {
        System.out.println(PARTITION_LINE);
    }

    public void showToUser(String... message) {
        for (String s : message) {
            System.out.println(s);
        }
    }

    public void showWelcomeMessage() {
        String message = PARTITION_LINE + LS + LOGO +
                WELCOME_MESSAGE + LS + PARTITION_LINE;
        showToUser(message);
    }

    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

}
