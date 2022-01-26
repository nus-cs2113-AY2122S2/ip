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
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while(!userInput.equalsIgnoreCase("Bye")) {
            System.out.println(lineSeparator + userInput + "\n" + lineSeparator);
            userInput = in.nextLine();
        }
        displayFarewell();
    }

}