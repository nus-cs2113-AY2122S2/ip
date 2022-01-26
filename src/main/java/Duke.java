import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void displayLogo() {
        String logo = "                       ___\n"
                +"                      / ()\\\n"
                +"                    _|_____|_\n"
                +"                   | | === | |\n"
                +" _____  _      _   |_|  0  |_|\n"
                +"| ___| | \\    / |   ||  0  ||\n"
                +"| |__  |  \\  /  |   ||__*__||\n"
                +"|  __| |   \\/   |  |~ \\___/ ~| \n"
                +"| |___ | |\\__/| |  /=\\ /=\\ /=\\ \n"
                +"|_____||_|    |_|__[_]_[_]_[_]______________________________\n";
        String greetings = "____________________________________________________________\n"
                + "Hello! I'm EM\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo + greetings);
    }

    public static void displayFarewell() {
        String farewell = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(farewell);
    }

    public static void main(String[] args) {
        String lineSeparator = "____________________________________________________________\n";
        displayLogo();

        String userInput;
        String[] taskList = new String[100];
        int listCount = 0;

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while(!userInput.equalsIgnoreCase("Bye")) {
            if (!userInput.equalsIgnoreCase("List")) {
                taskList[listCount] = userInput.toLowerCase();
                listCount ++;
                System.out.println(lineSeparator + "added: " + userInput.toLowerCase() + "\n" + lineSeparator);
            } else if (userInput.equalsIgnoreCase("List")) {
                System.out.println(lineSeparator);
                if (listCount == 0) {
                    System.out.println("List is Empty!\n");
                }
                for(int i = 0; i < listCount; i++) {
                    System.out.println((i+1) + ". " + taskList[i]);
                }
                System.out.println(lineSeparator);
            }
            userInput = in.nextLine();
        }
        displayFarewell();
    }
}