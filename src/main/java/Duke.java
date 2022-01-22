import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     /\\   |  ____/ __ \\| \\ | |\n"
                + "    /  \\  | |__ | |  | |  \\| |\n"
                + "   / /\\ \\ |  __|| |  | | . ` |\n"
                + "  / ____ \\| |___| |__| | |\\  |\n"
                + " /_/    \\_\\______\\____/|_| \\_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AEON\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while (!response.equals("bye")) {
            System.out.println(response);
            response = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}
