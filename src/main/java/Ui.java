import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "-------------------------------------------";
    private Scanner in = new Scanner(System.in);
    public Ui() {}

    /**
     * Retrieves the full input text from user.
     * @return the full input text from user.
     */
    public String getCommand() {
        String fullText = in.nextLine();
        System.out.println(SEPARATOR);
        return fullText;
    }

    /**
     * Prints the greeting of Cheems.
     */
    public static void greet() {
        String logo = "   ________                            \n"
                + "  / ____/ /_  ___  ___  ____ ___  _____\n"
                + " / /   / __ \\/ _ \\/ _ \\/ __ `__ \\/ ___/\n"
                + "/ /___/ / / /  __/  __/ / / / / (__  ) \n"
                + "\\____/_/ /_/\\___/\\___/_/ /_/ /_/____/  \n"
                + "                                       \n"
                + SEPARATOR;
        String greet = "Hemlo from\n" + logo + "\nWhamt cam cheems do for you?";
        System.out.println(greet);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the farewell of Cheems.
     */
    public static void farewell() {
        String farewell = "Goomdbye. See you nemxt time frem!";
        System.out.println(farewell);
        System.out.println(SEPARATOR);
    }
}
