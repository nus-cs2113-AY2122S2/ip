import java.util.Scanner;

public class TaskManager {
    private final String BOT_NAME = "[iWish]: ";
    public Task[] taskList = new Task[100];
    public int trackList = 0;

    public void startUp() {
        String userInput = "";
        Scanner in = new Scanner(System.in);
        boolean hasAddedTask = false;
        while (true) {
            hasAddedTask = false;
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(BOT_NAME + "Bye! Hope to hear from you soon :)");
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.contains("untick")) {
                String choice = userInput.substring(userInput.indexOf(' ') + 1);
                int choiceNumber = Integer.parseInt(choice) - 1;
                taskList[choiceNumber].setCompleted(false);
                System.out.println(taskList[choiceNumber]);
            } else if (userInput.contains("tick")) {
                String choice = userInput.substring(userInput.indexOf(' ') + 1);
                int choiceNumber = Integer.parseInt(choice) - 1;
                taskList[choiceNumber].setCompleted(true);
                System.out.println(taskList[choiceNumber]);
            } else if ((userInput.contains("todo:"))) {
                String description = userInput.substring(userInput.indexOf("todo:") + 5);
                description = description.trim();
                taskList[trackList] = new Todo(description);
                trackList++;
                System.out.println(BOT_NAME + " noting down your wish -> " + taskList[trackList - 1]);
                hasAddedTask = true;
            } else if ((userInput.contains("deadline:"))) {
                String description = userInput.substring(userInput.indexOf("deadline:") + 9);
                description = description.trim();
                String by = description.substring(description.indexOf("~") + 1);
                by = by.trim();
                description = description.substring(0, description.indexOf("~"));
                taskList[trackList] = new Deadline(description, by);
                trackList++;
                System.out.println(BOT_NAME + " noting down your wish -> " + taskList[trackList - 1]);
                hasAddedTask = true;
            } else if ((userInput.contains("event:"))) {
                String description = userInput.substring(userInput.indexOf("event:") + 6);
                String[] splitData = description.split("@");
                description = splitData[0].trim();
                String at = splitData[1].trim();
                taskList[trackList] = new Event(description, at);
                trackList++;
                System.out.println(BOT_NAME + " noting down your wish -> " + taskList[trackList - 1]);
                hasAddedTask = true;
            }
            if (hasAddedTask) {
                System.out.println("Now you have " + trackList + " wish(es) in the list.");
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
