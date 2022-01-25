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

        System.out.print("____________________\n"+
                          "Hello! I'm Duke\n"+
                          "What can I do for you?\n");
        boolean userToQuit = false;
        String input;
        while(!userToQuit){
            input = sc.nextLine();
            switch(input){
                case "bye":
                    userToQuit = true;
                    break;
                default:
                    echo(input);
            }
        }

        System.out.print("____________________\n"+
                "Bye. Hope to see you again soon!\n"+
                "____________________\n");
    }

    private static void echo(String input){
        System.out.printf("%s\n", input);
    }
}
