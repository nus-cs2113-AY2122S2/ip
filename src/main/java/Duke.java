import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "-----------------------------";
        Scanner in = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
        int index = 0;

        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello there! I'm Duke :D");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            input = in.nextLine();
            input = input.trim();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you soon :D");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < index; i++) {
                    int numbering = i + 1;
                    System.out.println(numbering + ". " + '[' + list[i].getStatusIcon() + ']' + list[i].description);
                }
                System.out.println(line);
            } else {
                String[] words = input.split(" ");
                if (words[0].equalsIgnoreCase("mark")) {
                    int indexToMark = Integer.parseInt(words[1]) - 1;
                    list[indexToMark].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println('[' + list[indexToMark].getStatusIcon() + ']' + list[indexToMark].description);
                    System.out.println(line);
                } else if (words[0].equalsIgnoreCase("unmark")) {
                    int indexToUnmark = Integer.parseInt(words[1]) - 1;
                    list[indexToUnmark].markAsUndone();
                    System.out.println("Nice! I've unmarked this task as undone:");
                    System.out.println('[' + list[indexToUnmark].getStatusIcon() + ']' + list[indexToUnmark].description);
                    System.out.println(line);
                } else {
                    Task t = new Task(input);
                    list[index] = t;
                    index++;
                    System.out.println("Added: " + input + System.lineSeparator() + line);
                }
            }
        }
    }
}
