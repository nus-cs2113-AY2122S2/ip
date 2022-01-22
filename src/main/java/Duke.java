import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Hi, I'm Robit!");
        System.out.println("What would you like me to do?");
        System.out.println("------------------------------------------------------------");

        while (true) {
            System.out.print("> ");
            String userInput = in.nextLine();
            System.out.println("------------------------------------------------------------");
            if (userInput.equals("bye")) {
                System.out.println("Goodbye!");
                System.out.println("------------------------------------------------------------");
                break;
            } else {
                System.out.println(userInput);
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}
