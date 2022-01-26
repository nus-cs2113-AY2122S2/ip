import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        welcomeMessage();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        while(!userInput.equals("bye")) {
            userInput = wrapMessage(userInput);
            System.out.println(userInput);
            userInput = input.nextLine();
        }
        byeMessage();
    }

    public static String wrapMessage(String message) {
        message = "____________________________________________________________\n "+
                message +
                " \n____________________________________________________________\n";

        return message;
    }

    public static void welcomeMessage() {
        String welcome="____________________________________________________________\n"+
                " Hello! I'm Nnythingy\n"+
                " What can I do for you?\n"+
                "____________________________________________________________\n";
        System.out.println(welcome);
    }

    public static void byeMessage() {
        String goodBye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(goodBye);
    }
}