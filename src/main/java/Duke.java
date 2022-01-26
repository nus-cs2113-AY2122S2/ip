import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        botResponse("Hello! I'm Boba\n\tWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<String> inputList = new ArrayList<>();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int index = 1;
                // StringBuilder to reduce constructing a new string due to concatenation
                StringBuilder sb = new StringBuilder();
                for (String item : inputList) {
                    sb.append(index++); // Post-increment to keep index up to date
                    sb.append(". ");
                    sb.append(item);
                    sb.append("\n\t");
                }
                // substring used to ignore the last newline character
                botResponse(sb.substring(0, sb.length() - 2));
            } else if(inputList.size() == 100) {
                // no more than 100 tasks
                botResponse("Sorry. No more tasks can be added.");
            } else {
                botResponse("added: " + input);
                inputList.add(input);
            }
            input = scan.nextLine();
        }
        botResponse("Bye. Hope to see you again soon!");
    }

    private static void botResponse(String response) {
        System.out.println("............................................................");
        System.out.println("\t" + response);
        System.out.println("............................................................");
    }
}
