import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

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
    static void listTasks(ArrayList<Task> tasks) {
        for (int i = 1; i <= tasks.size(); ++i) {
            System.out.printf("%d. %s%n", i, tasks.get(i-1));
        }
    }
    public static void main(String[] args) {
        printIntroduction();

        Scanner in = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = in.nextLine();
            String[] tokens = input.split("\\s");
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                surroundWithSeparators(() ->
                        listTasks(tasks)
                );
            } else if (tokens[0].equals("mark")) {
                int taskIndex = parseInt(tokens[1]);
                Task taskToMark = tasks.get(taskIndex - 1);
                taskToMark.setDone(true);
                surroundWithSeparators(() ->
                    System.out.printf("Good job on getting this done!%n    %s%n", taskToMark)
                );
            } else if (tokens[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(tokens[1]);
                Task taskToUnmark = tasks.get(taskIndex - 1);
                taskToUnmark.setDone(false);
                surroundWithSeparators(() ->
                    System.out.printf("Okay, I've marked this as not done:%n    %s%n", taskToUnmark)
                );
            } else {
                tasks.add(new Task(input));

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
