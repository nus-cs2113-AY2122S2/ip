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

    public static void DisplayList(String[] list, int count) {
        PrintBorder();
        for (int i = 0; i < count; i++) {
            System.out.print("\t" + (i + 1) + ". ");
            System.out.println(list[i]);
        }
        PrintBorder();
    }

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int count = 0;

        Greetings();
        do {
            System.out.println("");
            command = in.nextLine();

            switch (command.split(" ")[0]) {
            case "list":
                DisplayList(list, count);
                continue;
            case "bye":
                break;
            default:
                // the 'Add' function
                list[count] = command;
                count++;

                PrintBorder();
                System.out.println("\tadded:" + command);
                PrintBorder();
            }
        } while (command.split(" ")[0].compareTo("bye") != 0);

        GoodBye();
    }
}
