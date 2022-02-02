import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String indentation = "._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._.._._.";
        System.out.println(indentation);
        System.out.println("Hello! I'm Tom");
        System.out.println("What can I do for you?");
        System.out.println(indentation);

        //public static void listItems(String [100] listName) {
        String list[] = new String[100];
        for (int i = 0; i < 100; i++) {
            Scanner input1 = new Scanner(System.in);
            String command = input1.nextLine();
            list[i] = command;
            if (command.equals("list")) {
                for (int j = 0; j < i; j++) {
                    System.out.println((j + 1) + "." + "[ ]" + list[j]);
                }
            } else if ((command.substring(0, 4)).equals("mark")) {
                command = command.replaceAll("\\s", "");
                String indexPositioning = command.substring(4);
                int index = Integer.parseInt(indexPositioning);
                Task t = new Task(list[index - 1]);
                t.markAsDone(list[index - 1]);

            } else if ((command.substring(0, 6)).equals("unmark")) {
                command = command.replaceAll("\\s", "");
                String indexPositioning = command.substring(6);
                int index = Integer.parseInt(indexPositioning);
                Task t = new Task(list[index - 1]);
                t.markAsNotDone(list[index - 1]);

            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                //continue;
                System.out.println("added:" + command);
            }

        }
    }
}

