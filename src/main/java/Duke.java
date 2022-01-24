import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("-----------------------------");
        System.out.println("Hello! I'm Olivia, your lovely personal assistant.");
        System.out.println("What can Olivia do for you my love?");
        System.out.println("-----------------------------");
        String userInput;
        ArrayList<String> taskList=new ArrayList<String>();
        do {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            else if (userInput.equals("list")) {
                System.out.println("-----------------------------");
                for (int i = 0; i < taskList.size(); i++) {
                    //System.out.println(45+5 + "=" +45+5)
                    System.out.printf(i+1 + ". ");
                    System.out.println(taskList.get(i) + " ");
                }
                System.out.println("-----------------------------");
            }
            else {
                taskList.add(userInput);
                System.out.println("-----------------------------");
                System.out.print("added: ");
                System.out.println(userInput);
                System.out.println("-----------------------------");
            }
        } while(!userInput.equals("bye"));
        System.out.println("-----------------------------");
        System.out.println("Bye. Hope to see you again soon! With Love, Olivia");
        System.out.println("-----------------------------");
    }
}
