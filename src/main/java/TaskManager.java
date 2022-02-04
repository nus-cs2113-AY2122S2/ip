import java.util.Scanner;

public class TaskManager {
    public static final int MAX_TASK = 100;
    private final String BOT_NAME = "[iWish]: ";
    public Task[] taskList = new Task[MAX_TASK];
    public int trackList = 0;
    private final int TODO_WORD_COUNT = 5;
    private final int DEADLINE_WORD_COUNT = 9;
    private final int EVENT_WORD_COUNT = 6;
    private final char INDICATE_DEADLINE = '~';
    private final char INDICATE_EVENT = '@';

    public void startUp() {
        String userInput = "";
        Scanner in = new Scanner(System.in);
        boolean hasAddedTask = false;
        while (true) {
            hasAddedTask = false;
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                printExitMessage();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.contains("untick")) {
                untickTask(userInput);
            } else if (userInput.contains("tick")) {
                tickTaskCompleted(userInput, true);
            } else if ((userInput.contains("todo:"))) {
                hasAddedTask = addTodo(userInput);
            } else if ((userInput.contains("deadline:"))) {
                hasAddedTask = addDeadline(userInput);
            } else if ((userInput.contains("event:"))) {
                hasAddedTask = addEvent(userInput);
            }
            if (hasAddedTask) {
                printNumberOfWish();
            }
        }
    }

    private void printExitMessage() {
        System.out.println(BOT_NAME + "Bye! Hope to hear from you soon :)");
    }

    private void printNumberOfWish() {
        System.out.println("Now you have " + trackList + " wish(es) in the list.");
    }

    public String deriveDescription (String input, int wordCountOfTask) {
        String description = input.substring(input.indexOf("todo:") + wordCountOfTask);
        description = description.trim();
        return description;
    }

    public String deriveTimeDate (String input, char indication) {
        String storeTimeDate = input.substring(input.indexOf(indication) + 1);
        storeTimeDate = storeTimeDate.trim();
        return  storeTimeDate;
    }

    private boolean addEvent(String userInput) {
        boolean hasAddedTask;
        String description = deriveDescription(userInput, EVENT_WORD_COUNT);
        String at = deriveTimeDate(description, INDICATE_EVENT);
        description = description.substring(0, description.indexOf("@"));
        taskList[trackList] = new Event(description, at);
        trackList++;
        printNoted();
        hasAddedTask = true;
        return hasAddedTask;
    }

    private boolean addDeadline(String userInput) {
        boolean hasAddedTask;
        String description = deriveDescription(userInput, DEADLINE_WORD_COUNT);
        String by = deriveTimeDate(description, INDICATE_DEADLINE);
        description = description.substring(0, description.indexOf("~"));
        taskList[trackList] = new Deadline(description, by);
        trackList++;
        printNoted();
        hasAddedTask = true;
        return hasAddedTask;
    }

    private boolean addTodo(String userInput) {
        boolean hasAddedTask;
        String description = deriveDescription(userInput, TODO_WORD_COUNT);
        taskList[trackList] = new Todo(description);
        trackList++;
        System.out.println(BOT_NAME + " noting down your wish -> " + taskList[trackList - 1]);
        hasAddedTask = true;
        return hasAddedTask;
    }

    private void tickTaskCompleted(String userInput, boolean b) {
        String choice = userInput.substring(userInput.indexOf(' ') + 1);
        int choiceNumber = Integer.parseInt(choice) - 1;
        taskList[choiceNumber].setCompleted(b);
        System.out.println(taskList[choiceNumber]);
    }

    private void untickTask(String userInput) {
        tickTaskCompleted(userInput, false);
    }

    public void printNoted() {
        System.out.println(BOT_NAME + " noting down your wish -> " + taskList[trackList - 1]);
    }

    public void printList() {
        System.out.println(BOT_NAME + " ** These are your wishes **");
        for (int i = 0; i < trackList; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        System.out.println("We reached the end of the list. Anymore wish?");
    }
}
