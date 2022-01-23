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
            System.out.println(line);
            userInput = in.nextLine();
            System.out.println(userInput);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
