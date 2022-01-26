import java.util.Scanner;

public class Marites {
    static void printSeparator() {
        System.out.println("========================================");
    }
    static void printIntroduction() {
        // Found in https://emojicombos.com/kaomoji
        String logo = "(งツ)ว";

        System.out.println(logo);
        System.out.println("Hi, I'm Marites! I've heard so many things about you!");
        System.out.println("I have a lot of stories to share, but first, how can I help you?");
        printSeparator();
    }
    public static void main(String[] args) {
        printIntroduction();

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            String[] tokens = input.split("\\s");
            if (tokens[0].equals("bye")) {
                break;
            }
            printSeparator();
            System.out.println(input);
            printSeparator();
        }

        printSeparator();
        System.out.println("See you next time!");
        printSeparator();
    }
}
