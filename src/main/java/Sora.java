import java.util.ArrayList;
import java.util.Scanner;

public class Sora {
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
        if (Helper.getHourOfDay() < 12) {
            System.out.print("Good morning! ");
        } else if (Helper.getHourOfDay() < 18) {
            System.out.print("Good afternoon! ");
        } else {
            System.out.print("Good evening. ");
        }

        System.out.println("I'm Sora ヽ(・∀・)ﾉ");
        System.out.println("What can I do for you?");
        Helper.printLine();
    }

    public void printGoodbye() {
        if (Helper.getHourOfDay() < 18) {
            System.out.println("Goodbye! Have a great day ahead (⌒▽⌒)☆");
        } else if (Helper.getHourOfDay() < 22) {
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
        SoraList soraList = new SoraList();
        sora.printGreetings();

        // Get user command
        Scanner in = new Scanner(System.in);
        String userInput;

        System.out.print("> ");
        userInput = in.nextLine();
        Helper.printLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println(Helper.getRandomAcknowledgement() + ", here's the list of texts that you have given to me:");
                System.out.println();

                // Get the list, iterate through and print it
                ArrayList<String> currentList = soraList.getList();
                for (int i = 0; i < currentList.size(); i += 1) {
                    System.out.println("\t" + (i + 1) + ". " + currentList.get(i));
                }

                System.out.println();

            } else {
                // Add text to list
                boolean addSuccess = soraList.addText(userInput);

                if (addSuccess) {
                    System.out.println(Helper.getRandomAcknowledgement() + ", I have added your text to my list:");
                    System.out.println();
                    System.out.println("\t" + userInput);
                    System.out.println();
                } else {
                    System.out.println("Oops! Somehow I wasn't able to add your text to my list...");
                    System.out.println("Sorry about that! (-ω-、)");
                }
            }

            // Get next user prompt
            Helper.printLine();
            System.out.println("What's next?");
            Helper.printLine();
            System.out.print("> ");
            userInput = in.nextLine();
            Helper.printLine();
        }

        // User has entered "bye"
        sora.printGoodbye();
    }
}
