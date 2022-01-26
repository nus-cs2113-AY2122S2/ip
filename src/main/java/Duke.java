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
        Scanner sc = new Scanner(System.in);
        ArrayList<String> listOfStrings = new ArrayList<>();

        System.out.print("____________________\n"+
                          "Hello! I'm Duke\n"+
                          "What can I do for you?\n");
        boolean userToQuit = false;
        String input;
        while(!userToQuit){
            input = sc.nextLine();
            switch(input){
                case "list":
                    list(listOfStrings);
                    break;
                case "bye":
                    userToQuit = true;
                    break;
                default:
                    addItem(input, listOfStrings);
                    break;
            }
        }

        System.out.print("____________________\n"+
                "Bye. Hope to see you again soon!\n"+
                "____________________\n");
    }

    private static void addItem(String input, ArrayList<String> list){
        list.add(input);
        System.out.printf("added: %s\n", input);
    }

    private static void list(ArrayList<String> list){
        for(int i = 0; i< list.size(); i++){
            System.out.printf("%d. %s\n", i+1, list.get(i));
        }
    }
}
