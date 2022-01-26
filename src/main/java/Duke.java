import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        botResponse("\tHello! I'm Boba\n\tWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            botResponse("\t" + input);
            input = scan.nextLine();
        }
        botResponse("\tBye. Hope to see you again soon!");
    }

    private static void botResponse(String response) {
        System.out.println("............................................................");
        System.out.println(response);
        System.out.println("............................................................");
    }
}
