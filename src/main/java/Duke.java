import java.util.Scanner;
import java.util.Arrays;



public class Duke {

    public static void main(String[] args) {

        Task[] taskItems;
        taskItems = new Task[100];
        int itemNum = 0;

        System.out.println("Hello! I'm Hage");
        System.out.println("What can I do for you?");
        Scanner scan = new Scanner(System.in);
        String inCommand = scan.nextLine();

        while (!inCommand.equals("bye")) {
            String[] commandArr = inCommand.split(" ");
            if (inCommand.equals("list")) {
                int listNum = 1;
                for (int i = 0; i < itemNum; i++) {
                    System.out.println(listNum + ".[" + taskItems[i].getStatusIcon() + "] " + taskItems[i].description);
                    listNum++;
                }
            }
            else if (commandArr[0].equals("mark")) {
                int markNum = Integer.parseInt(commandArr[1]) - 1;
                taskItems[markNum].isDone = true;
                System.out.println("Nice! I've marked this task as done:\n" + "[X] " + taskItems[markNum].description);
            } else if (commandArr[0].equals("unmark")) {
                int unmarkNum = Integer.parseInt(commandArr[1]) - 1;
                taskItems[unmarkNum].isDone = false;
                System.out.println("Nice! I've marked this task as not done yet:\n" + "[ ] " + taskItems[unmarkNum].description);
            }
            else {
                taskItems[itemNum] = new Task(inCommand);
                System.out.println("added:" + inCommand);
                itemNum++;
            }
            inCommand = scan.nextLine();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
