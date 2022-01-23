import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput = "";
        String line = "------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        while (!userInput.equals("bye")) {
            userInput = Echo(in, line);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String Echo(Scanner in, String line) {
        String userInput;
        System.out.println(line);
        userInput = in.nextLine();
        System.out.println(userInput);
        return userInput;
    }
}
