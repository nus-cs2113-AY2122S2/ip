import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n\n"
                + "____________________________________________________________\n";

        System.out.println(greeting);

        while (true) {
            String line = sc.nextLine();
            switch (line) {
            case "bye":
                System.out.println("____________________________________________________________\n");
                System.out.println("     Bye; Don't restart me.");
                System.out.println("____________________________________________________________\n");
                System.exit(0);
            default:
                System.out.println("____________________________________________________________\n");
                System.out.println("     " + line);
                System.out.println("____________________________________________________________\n");
            }
        }
    }
}
