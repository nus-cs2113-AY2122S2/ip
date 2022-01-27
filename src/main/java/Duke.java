import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Hage");
        System.out.println("What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String inCommand = scan.nextLine();
        while (!inCommand.equals("bye")) {
            System.out.println(inCommand);
            inCommand = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
