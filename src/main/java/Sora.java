import java.util.Calendar;
import java.util.TimeZone;
import java.util.Scanner;

public class Sora {
    private int hourOfDay = Helper.getHourOfDay();

    // Accessors of fields in Sora class
    public int getHourOfDay() {
        return this.hourOfDay;
    }

    private void printGreetings() {
        String logo = "     _______.  ______   .______          ___      \n"
                + "    /       | /  __  \\  |   _  \\        /   \\     \n"
                + "   |   (----`|  |  |  | |  |_)  |      /  ^  \\    \n"
                + "    \\   \\    |  |  |  | |      /      /  /_\\  \\   \n"
                + ".----)   |   |  `--'  | |  |\\  \\----./  _____  \\  \n"
                + "|_______/     \\______/  | _| `._____/__/     \\__\\";

        // Print banner
        System.out.println(logo);
        Helper.printLine();

        // Print greetings
        if (getHourOfDay() < 12) {
            System.out.print("Good morning! ");
        } else if (getHourOfDay() < 18) {
            System.out.print("Good afternoon! ");
        } else {
            System.out.print("Good evening. ");
        }

        System.out.println("I'm Sora ヽ(・∀・)ﾉ");
        System.out.println("What can I do for you?");
        Helper.printLine();
    }

    public void printGoodbye() {
        if (getHourOfDay() < 18) {
            System.out.println("Goodbye! Have a great day ahead (⌒▽⌒)☆");
        } else if (getHourOfDay() < 22) {
            System.out.println("Goodbye! Have a good evening <(￣︶￣)>");
        } else {
            System.out.println("Good night, have a good rest... (－ω－) zzZ");
        }

        System.out.println("See you again soon~");
        Helper.printLine();
    }

    public static void main(String[] args) {
        // Start Sora
        Sora sora = new Sora();
        sora.printGreetings();

        // Get user command
        Scanner in = new Scanner(System.in);
        String userInput = "";

        System.out.print("> ");
        userInput = in.nextLine();
        Helper.printLine();

        while (!userInput.toLowerCase().equals("bye")) {

            // Echo the user input
            System.out.println(userInput);
            Helper.printLine();

            // Get next user prompt
            System.out.print("> ");
            userInput = in.nextLine();
            Helper.printLine();
        }

        // User has entered "bye"
        sora.printGoodbye();
    }
}
