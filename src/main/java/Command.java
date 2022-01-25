import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Command {
    /**
     * Scanner to get user input
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * Store all the reminders
     */
    private static List<String> reminders = new ArrayList<String>();


    /**
     *  Get the input string from user
     * @return the string from user (without any changes)
     */
    public static String getRawCommand() {
        return sc.nextLine();
    }

    /**
     * Split the raw string from {@link #getRawCommand()} into tokens,
     * recognizing string in quotes as a whole string. Automatically
     * add a quote at the end of the string if there is an odd number
     * of quotes.
     *
     * @param raw raw string from {@link #getRawCommand()}
     * @return a string array containing tokens
     */
    public static String[] parseCommand(String raw){
        int leftIndex = 0;
        boolean inQuotes = false;
        List<String> tokens = new ArrayList<String>();
        raw = raw.trim();
        for(int i = 0; i < raw.length(); i++) {
            switch(raw.charAt(i)) {
            case ' ':
                if(inQuotes) {
                    continue;
                } else if(leftIndex != -1) {
                    tokens.add(raw.substring(leftIndex, i));
                    leftIndex = -1;
                }
                break;
            case '\"':
                if(inQuotes) {
                    tokens.add(raw.substring(leftIndex, i));
                    leftIndex = -1;
                    inQuotes = false;
                } else {
                    inQuotes = true;
                    leftIndex = i + 1;
                }
                break;
            default:
                if(!inQuotes && leftIndex == -1) {
                    leftIndex = i;
                }
                break;
            }
        }
        if(leftIndex != -1) {
            tokens.add(raw.substring(leftIndex));
        }
        return tokens.toArray(new String[0]);
    }

    /**
     * Run the corresponding command/method regarding args, default command is echo
     * @param args a list of tokens, args[0] should be the name of the command, and the rest is the arguments of that command
     */
    public static void runCommand(String[] args) {
        // TODO Maybe use something like a functor array
        switch (args[0]) {
        case "list":
            list(args);
            break;
        default:
            String[] defaultArgs = new String[args.length+1];
            defaultArgs[0] = "add";
            for(int i = 1; i < defaultArgs.length; i++) {
                defaultArgs[i] = args[i-1];
            }
            add(defaultArgs);
            break;
        }

    }

    /**
     * Print every token
     * @param args tokens to print
     */
    private static void echo(String[] args) {
        for(int i = 1; i < args.length; i++) {
            System.out.print(args[i]);
            if(i != args.length - 1) {
                System.out.print(" ");
            } else {
                System.out.print("\n");
            }
        }
    }

    /**
     * Add one reminder which is concatenated of args
     *
     * @param args reminder to be added
     */
    private static void add(String[] args) {
        // TODO Add all the reminders in args
        String reminder = "";
        for(int i = 1; i < args.length; i++) {
            reminder += args[i];
            reminder += " ";
        }
        reminder = reminder.trim();
        System.out.println("Added: " + reminder);
        reminders.add(reminder);
    }

    /**
     * List all the reminders
     * @param args dump variable
     */
    private static void list(String[] args) {
        // TODO args can be used to control the style of output
        for(int i = 0; i < reminders.size(); i++) {
            System.out.printf("%d: %s\n", i+1, reminders.get(i));
        }
    }
}
