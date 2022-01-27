import java.util.Scanner;

public class Duke {

    public static void greetings() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void addList (Task[] array, String message, int itemNumber) {
        Task t = new Task(message);
        array[itemNumber] = t;
        System.out.println("added: " + t.description);
    }

    public static void listItems (Task[] array, int itemNumber) {
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < itemNumber; i++) {
            System.out.println((i + 1) + ".[" + array[i].getStatusIcon() + "] " + array[i].description);
        }
    }

    public static void markItem (Task[] array, String message, int itemNumber) {
        String[] splitMessage = message.split(" ");
        String positionOfNumber = splitMessage[1];
        int positionToMark = Integer.parseInt(positionOfNumber) - 1;
        array[positionToMark].markAsDone();
        System.out.println("Nice! I've marked this as done:");
        System.out.println("[" + array[positionToMark].getStatusIcon() + "] " + array[positionToMark].description);
    }

    public static void unMarkItem(Task[] array, String message, int itemNumber) {
        String[] splitMessage = message.split(" ");
        String positionOfNumber = splitMessage[1];
        int positionToUnMark = Integer.parseInt(positionOfNumber) - 1;
        array[positionToUnMark].unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + array[positionToUnMark].getStatusIcon() + "] " + array[positionToUnMark].description);
    }



    public static void exits() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        int itemNumber = 0;
        Task[] listArray = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greetings();
        boolean isLoop = true;
        while (isLoop) {
            Scanner in = new Scanner(System.in);
            String message = in.nextLine();
            String messageLowerCase = message.toLowerCase();
            if (messageLowerCase.equals("bye")) {
                exits();
                isLoop = false;
            } else if (messageLowerCase.equals("list")) {
                listItems(listArray, itemNumber);
            } else if (messageLowerCase.contains("unmark")) {
                unMarkItem(listArray,message,itemNumber);
            } else if (messageLowerCase.contains("mark")) {
                markItem(listArray,message,itemNumber);
            } else {
                addList(listArray, message, itemNumber);
                itemNumber++;
            }
        }
    }
}
