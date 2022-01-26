import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printSeparator();
        System.out.println(" Hello! I'm Boba");
        System.out.println(" What can I do for you?");
        printSeparator();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            printSeparator();
            System.out.println(" " + input);
            printSeparator();
            input = scan.nextLine();
        }
        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("............................................................");
    }
}
