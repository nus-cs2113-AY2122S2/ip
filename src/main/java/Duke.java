import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> listArray = new ArrayList<>();
    public static int itemNumber;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String DISPLAY_LINE= "________________________________________________\n";

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(DISPLAY_LINE + ANSI_BLUE + logo);
        System.out.println("Hello! Duke here!:)");
        System.out.print("Is there anything I can do for you?\n" + ANSI_RESET + DISPLAY_LINE);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listItems (List<Task> array, int itemNumber) {
        System.out.println(DISPLAY_LINE + "Here are the tasks in your list: ");
        for(int i = 0; i < itemNumber; i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(array.get(i));
        }
        System.out.print(DISPLAY_LINE);
    }


    public static void main(String[] args) {
        itemNumber = 0;
        greet();
        Scanner in = new Scanner(System.in);
        boolean isLoop = true;
        while (isLoop) {
            String message = in.nextLine();
            String messageLowerCase = message.toLowerCase();
            if (messageLowerCase.equals("bye")) {
                exit();
                isLoop = false;
            } else if (messageLowerCase.equals("list")) {
                listItems(listArray, itemNumber);
            } else if (messageLowerCase.contains("unmark")) {
                MarkUnmarkItem.unMarkItem(listArray,message);
            } else if (messageLowerCase.contains("mark")) {
                MarkUnmarkItem.markItem(listArray,message);
            } else if (messageLowerCase.contains("todo")) {
                AddTask.addTodo(listArray,message,itemNumber);
                itemNumber++;
            } else if (messageLowerCase.contains("deadline")) {
                AddTask.addDeadline(listArray,message,itemNumber);
                itemNumber++;
            } else if (messageLowerCase.contains("event")) {
                AddTask.addEvent(listArray,message,itemNumber);
                itemNumber++;
            } else {
                System.out.println("Invalid command! Please try again:)");
            }
        }
    }
}
