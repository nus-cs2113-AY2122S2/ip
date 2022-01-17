import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        ArrayList<String> store = new ArrayList<String>();

        while (!userInput.equals("bye")){
            if (userInput.equals("list")){
                for (int i = 0; i < store.size(); i++){
                    System.out.println(i + 1 + ". " + store.get(i));
                }
            }
            else{
                store.add(userInput);
                System.out.println("added: " + userInput);
            }
            userInput = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
