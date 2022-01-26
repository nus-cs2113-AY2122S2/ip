import java.util.Scanner;

public class TaskManager {
    private final String BOT_NAME = "[iWish]: ";
    public Task[] taskList = new Task[100];
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
            } else if (userInput.contains("untick")) {
                int choice = Integer.parseInt(userInput.substring(userInput.indexOf(' ') + 1)) - 1;
                taskList[choice].setCompleted(false);
                System.out.println(taskList[choice].getTaskStatus());
            } else if (userInput.contains("tick")) {
                int choice = Integer.parseInt(userInput.substring(userInput.indexOf(' ') + 1)) - 1;
                taskList[choice].setCompleted(true);
                System.out.println(taskList[choice].getTaskStatus());
            } else {
                taskList[trackList] = new Task(userInput);
                trackList++;
                System.out.println(BOT_NAME + " added " + userInput);
            }


        }
    }

    public void printList() {
        System.out.println(BOT_NAME + " ** These are your wishes **");
        for (int i = 0; i < trackList; i++) {
            System.out.println((i + 1) + ". " + taskList[i].getTaskStatus());
        }
        System.out.println("We reached the end of the list. Anymore wish?");
    }

}
