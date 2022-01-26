import java.util.Scanner;
import java.util.ArrayList;

// Functional interface for lambda expressions that produce output.
interface Printer {
    void print();
}

public class Marites {
    static void printSeparator() {
        System.out.println("========================================");
    }
    static void surroundWithSeparators(Printer p) {
        printSeparator();
        p.print();
        printSeparator();
    }
    static void printIntroduction() {
        // Found in https://emojicombos.com/kaomoji
        String logo = "(งツ)ว";

        System.out.println(logo);
        System.out.println("Hi, I'm Marites! I've heard so many things about you!");
        System.out.println("I have a lot of stories to share, but first, how can I help you?");
        printSeparator();
    }
    static void listTasks(ArrayList<String> tasks) {
        for (int i = 1; i <= tasks.size(); ++i) {
            System.out.printf("%d. %s%n", i, tasks.get(i-1));
        }
    }
    public static void main(String[] args) {
        printIntroduction();

        Scanner in = new Scanner(System.in);

        ArrayList<String> tasks = new ArrayList<>();

        while (true) {
            String input = in.nextLine();
            String[] tokens = input.split("\\s");
            if (tokens[0].equals("bye")) {
                break;
            } else if (input.equals("list")) {
                surroundWithSeparators(() ->
                    listTasks(tasks)
                );
            } else {
                tasks.add(input);

                surroundWithSeparators(() ->
                    System.out.printf("added: %s%n", input)
                );
            }
        }

        surroundWithSeparators(() ->
            System.out.println("See you next time!")
        );
    }
}
