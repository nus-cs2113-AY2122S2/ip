import java.util.Scanner;

public class TaskManager {
    private final String BOT_NAME = "[iWish]: ";

    public void echoInput() {
        String userInput = "";
        Scanner in = new Scanner(System.in);
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(BOT_NAME + "Bye! Hope to hear from you soon :)");
                break;
            } else {
                System.out.println(BOT_NAME + userInput);
            }
        }
    }
}
