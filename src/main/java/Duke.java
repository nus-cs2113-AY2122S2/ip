import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        print("Hello from JRobo! I'm your personal assistant!\n\t" + "Nice to meet you. What can I do for you?");

        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            print(input);
        }

        print("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void print(String s) {
        System.out.println("\t____________________________________________________________\n\t" 
        + s
        + "\n\t____________________________________________________________");
    }
}
