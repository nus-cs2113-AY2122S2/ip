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
    public static final int TASK_LIMIT = 100;
    private static final int DONE = -1;
    private static final int CONTINUE = -2;
    private static String[] storedResponses = new String[TASK_LIMIT];
    private static int responsesSoFar = 0;



    public static void partitionPrint(String input) {
        System.out.println(partitionLine);
        System.out.println(input);
        System.out.println(partitionLine);
    }

    public static void printResponseList() {
        System.out.println(partitionLine);
        for (int i = 0; i < responsesSoFar; i++) {
            System.out.println(i+1 + ". " + storedResponses[i]);
        }
        System.out.println(partitionLine);
    }

    public static int parseInput(String userInput) {
        switch(userInput) {
        case "bye":
            return DONE;
        case "list":
            printResponseList();
            break;
        default:
            storedResponses[responsesSoFar] = userInput;
            partitionPrint("added: " + userInput);
            responsesSoFar++;
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
        String[] storedResponses = new String[TASK_LIMIT];
        int responsesSoFar = 0;
        Scanner in = new Scanner(System.in);
        printWelcomeMessage();
        while (statusOfSerene != DONE) {
            String userInput = in.nextLine();
            statusOfSerene = parseInput(userInput);
        }
        partitionPrint(exitLine);
    }
}
