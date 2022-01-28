import java.util.Scanner;

public class Duke {
    private static String[] userList = new String [100]; //array of String to store user's list
    private static int listCount = 0; //Keeps track of number of items in list

    final static String horizontalLine = "____________________________________________________________\n";
    final static String OPENING_MSG = horizontalLine
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + horizontalLine;
    final static String CLOSING_MSG = horizontalLine +  " Bye. Hope to see you again soon!\n"
            + horizontalLine;

    public static void printUserList() {
        if (listCount == 0) {
            System.out.println(horizontalLine
                    + "Your list is currently empty. Add some items to your list first.\n"
                    + horizontalLine);
        } else {
            System.out.print(horizontalLine);
            for (int i = 0; i < listCount; i++) {
                System.out.println(i+1 + ": " + userList[i]);
            }
            System.out.print(horizontalLine);
        }
    }

    public static void addUserList(String task) {
        userList[listCount] = task;
        listCount++;
        System.out.println(horizontalLine
                + "added: " + task + "\n"
                + horizontalLine);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println(OPENING_MSG);

        while (true) {
            String userCommand = in.nextLine();
            switch (userCommand) {
            case "bye": //exit command
                System.out.println(CLOSING_MSG);
                return;
            case "list":
                printUserList();
                break;
            default:
                addUserList(userCommand);
            }
        }

    }
}
