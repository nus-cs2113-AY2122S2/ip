import java.util.Scanner;

public class TaskManager {
    private final String BOT_NAME = "[iWish]: ";
    public String[] taskList = new String[100];
    public int trackList = 0;

    public void startUp() {
        String userInput = "";
        Scanner in = new Scanner(System.in);
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(BOT_NAME + "Bye! Hope to hear from you soon :)");
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else {
                taskList[trackList] = userInput;
                trackList++;
                System.out.println(BOT_NAME + " added " + userInput);
            }
        }
    }

    public void printList() {
        System.out.println(BOT_NAME + " ** These are your wishes **");
        for (int i = 0; i < trackList; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        System.out.println("We reached the end of the list. Anymore wish?");
    }
}
