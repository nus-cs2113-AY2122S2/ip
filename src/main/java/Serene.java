import java.util.Scanner;

public class Serene {
    public static String logo = " #####  ####### ######  ####### #     # ####### \n"
            + "#     # #       #     # #       ##    # #       \n"
            + "#       #       #     # #       # #   # #       \n"
            + " #####  #####   ######  #####   #  #  # #####   \n"
            + "      # #       #   #   #       #   # # #       \n"
            + "#     # #       #    #  #       #    ## #       \n"
            + " #####  ####### #     # ####### #     # ####### ";
    public static String greetLine = "Hello~ I'm Serene\nWhat can I do for you?";
    public static String partitionLine = "____________________________________________________________";
    public static String exitLine = "Till next time. Hope to see you again soon~";
    public static final int DONE = -1;
    public static final int CONTINUE = -2;



    public static void partitionPrint(String input) {
        System.out.println(partitionLine);
        System.out.println(input);
        System.out.println(partitionLine);
    }

    public static int parseInput(String userInput) {
        switch(userInput) {
        case "bye":
            return DONE;
        default:
            partitionPrint(userInput);
        }
        return CONTINUE;
    }

    public static void printWelcomeMessage() {
        System.out.println(partitionLine);
        System.out.println("Booting up\n" + logo);
        partitionPrint(greetLine);
    }

    public static void main(String[] args) {
        int statusOfSerene = CONTINUE;
        Scanner in = new Scanner(System.in);
        printWelcomeMessage();
        while (statusOfSerene != DONE) {
            String userInput = in.nextLine();
            statusOfSerene = parseInput(userInput);
        }
        partitionPrint(exitLine);
    }
}
