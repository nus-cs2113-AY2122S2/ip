import java.util.Locale;
import java.util.Scanner;

public class Bob {

    public static void PrintBorder() {
        String border = "\t____________________________________________________________";
        System.out.println(border);
    }

    public static void Greetings() {
        PrintBorder();
        String logo = "\t.______     ______   .______   \n"
                + "\t|   _  \\   /  __  \\  |   _  \\  \n"
                + "\t|  |_)  | |  |  |  | |  |_)  | \n"
                + "\t|   _  <  |  |  |  | |   _  <  \n"
                + "\t|  |_)  | |  `--'  | |  |_)  | \n"
                + "\t|______/   \\______/  |______/  \n\n"
                + "\tHello! I'm Bob\n"
                + "\tWhat can I do for you?";
        System.out.println(logo);
        PrintBorder();
    }

    public static void GoodBye() {
        PrintBorder();
        System.out.println("\tSee next time. /|\\(◦.◦)/|\\");
        PrintBorder();
    }

    public static void main(String[] args) {
        Greetings();
        String command;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("");
            command = in.nextLine();

            switch (command.split(" ")[0]) {
            case "bye":
                break;
            default:
                PrintBorder();
                System.out.println("\t" + command);
                PrintBorder();
            }
        } while (command.split(" ")[0].compareTo("bye") != 0);

        GoodBye();
    }
}
