import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {

        String[] storeItems = new String[100];
        int itemNum = 0;

        System.out.println("Hello! I'm Hage");
        System.out.println("What can I do for you?");
        Scanner scan = new Scanner(System.in);
        String inCommand = scan.nextLine();

        while (!inCommand.equals("bye")) {
            if (inCommand.equals("list")) {
                int i = 1;
                for (int j = 0; j < itemNum; j++)  {
                    System.out.println(i + ". " + storeItems[j]);
                    i++;
                }
            }
            else {
                storeItems[itemNum] = inCommand;
                System.out.println("added:" + inCommand);
                itemNum++;
            }
            inCommand = scan.nextLine();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
