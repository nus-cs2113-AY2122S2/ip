import java.util.Scanner;


public class Duke {
    private static Task[] userList = new Task[100]; //array of String to store user's list
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
                String message = i+1 + ".[" + userList[i].getStatusIcon() + "] " + userList[i].description;
                System.out.println(message);
            }
            System.out.print(horizontalLine);
        }
    }

    public static void printOneTask(int index) {
        //This method assumes the index provided is valid
        System.out.println("[" + userList[index].getStatusIcon() + "] " + userList[index].description);
    }


    public static void addUserList(String inputTask) {
        userList[listCount] = new Task(inputTask);
        listCount++;
        System.out.println(horizontalLine
                + "added: " + inputTask + "\n"
                + horizontalLine);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println(OPENING_MSG);

        while (true) {
            String inputLine = in.nextLine();
            String[] userCommand = inputLine.split(" ");
            switch (userCommand[0]) {
            case "bye": //exit command
                System.out.println(CLOSING_MSG);
                return;
            case "list":
                printUserList();
                break;
            case "mark":
                int index = Integer.parseInt(userCommand[1]) - 1;
                if (index >= listCount) {
                    System.out.println(horizontalLine
                            + "No such task exists. Add more tasks first!\n"
                            + horizontalLine);
                    break;
                }
                Task targetTask = userList[index];

                System.out.print(horizontalLine);
                if (targetTask.markAsDone()) {
                    System.out.println("Nice! I've marked this task as done:");
                    printOneTask(index);
                }
                System.out.print(horizontalLine);
                break;
            case "unmark":
                int taskIndex = Integer.parseInt(userCommand[1]) - 1;
                if (taskIndex >= listCount) {
                    System.out.println(horizontalLine
                            + "No such task exists. Add more tasks first!\n"
                            + horizontalLine);
                    break;
                }
                Task specifiedTask = userList[taskIndex];
                System.out.print(horizontalLine);
                if (specifiedTask.markAsUndone()) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    printOneTask(taskIndex);
                }
                System.out.print(horizontalLine);
                break;
            default:
                addUserList(inputLine);
            }
        }
    }
}
