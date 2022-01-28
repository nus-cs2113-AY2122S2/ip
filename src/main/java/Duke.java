import java.util.*;
public class Duke {
    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        print("Hello from JRobo! I'm your personal assistant!\n\t" + "Nice to meet you. What can I do for you?");

        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("list")) {
                displayList(tasks);
                continue;
            }
            if (input.equals("bye")) {
                break;
            }
            tasks.add(input);
            print("added: " + input);
        }

        print("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void print(String s) {
        System.out.println("\t____________________________________________________________\n\t" 
        + s
        + "\n\t____________________________________________________________");
    }

    public static void displayList(List<String> tasks) {
        System.out.println("\t____________________________________________________________");
        int index = 1;
        for (String s : tasks) {
            System.out.println("\t" + index++ + ". " + s);
        }
        System.out.println("\t____________________________________________________________");
    }
}
