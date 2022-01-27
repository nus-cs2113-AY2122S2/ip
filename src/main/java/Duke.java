import java.util.Scanner;

public class Duke {

    public static void greetings() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void addList (String[] array, String message, int itemNumber) {
        array[itemNumber] = message;
        System.out.println("added: " + message);
    }

    public static void listItems (String[] array, int itemNumber) {
        for(int i = 0; i < itemNumber; i++) {
            System.out.println((i + 1) + ". " + array[i]);
        }
    }

//    public static void echo(String message) {
//        System.out.println(message);
//    }

    public static void exits() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        int itemNumber = 0;
        String[] listArray = new String[100];
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
            } else {
                addList(listArray, message, itemNumber);
                itemNumber++;
            }
        }
    }
}
