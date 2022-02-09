import java.util.Scanner;

public class TaskManager {
    public static final int MAX_TASK = 100;
    private final String BOT_NAME = "[iWish]: ";
    public Task[] taskList = new Task[MAX_TASK];
    public int trackList = 0;
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
            } else {
                hasAddedTask = isTaskAdded(userInput, hasAddedTask);
            }
            if (hasAddedTask) {
                printNumberOfWish();
            }
        }
    }

    public boolean handleTaskCommand(String userInput) throws DukeWrongCommandException {
        if ((userInput.contains("todo:"))) {
            return addTodo(userInput);
        } else if ((userInput.contains("deadline:"))) {
            return addDeadline(userInput);
        } else if ((userInput.contains("event:"))) {
            return addEvent(userInput);
        } else {
            throw new DukeWrongCommandException();
        }
    }

    private boolean isTaskAdded(String userInput, boolean hasAddedTask) {
        try {
            hasAddedTask = handleTaskCommand(userInput);
        } catch (DukeWrongCommandException error) {
            printMessage("Please state a valid wish!!!");
        }
        return hasAddedTask;
    }

    private void printExitMessage() {
        printMessage("Bye! Hope to hear from you soon :)");
    }

    private void printNumberOfWish() {
        System.out.println("Now you have " + trackList + " wish(es) in the list.");
    }

    public String deriveDescription(String input, String command) throws DukeEmptyStringException {
        String description = input.substring(input.indexOf(command) + command.length());
        description = description.trim();
        if (description.isEmpty()) {
            throw new DukeEmptyStringException();
        }
        return description;
    }

    public String deriveTimeDate(String input, char indication) {
        String storeTimeDate = input.substring(input.indexOf(indication) + 1);
        storeTimeDate = storeTimeDate.trim();
        return storeTimeDate;
    }

    private boolean addEvent(String userInput) {
        boolean hasAddedTask;
        String description = "";
        try {
            description = deriveDescription(userInput, "event:");
        } catch (DukeEmptyStringException e) {
            printMessage("The description for event: cannot be empty");
            return false;
        }
        String at = deriveTimeDate(description, INDICATE_EVENT);
        try {
            description = description.substring(0, description.indexOf("@"));
        } catch (StringIndexOutOfBoundsException e) {
            printMessage("Incorrect event usage -> 'event: sing @ 3pm'");
            return false;
        }
        taskList[trackList] = new Event(description, at);
        trackList++;
        printNoted();
        hasAddedTask = true;
        return hasAddedTask;
    }

    private boolean addDeadline(String userInput) {
        boolean hasAddedTask;
        String description = "";
        try {
            description = deriveDescription(userInput, "deadline:");
        } catch (DukeEmptyStringException error) {
            printMessage("The description for deadline: cannot be empty");
            return false;
        }
        String by = deriveTimeDate(description, INDICATE_DEADLINE);
        try {
            description = description.substring(0, description.indexOf("~"));
        } catch (StringIndexOutOfBoundsException error) {
            printMessage("Incorrect deadline usage -> 'deadline: homework ~ 3pm'");
            return false;
        }
        taskList[trackList] = new Deadline(description, by);
        trackList++;
        printNoted();
        hasAddedTask = true;
        return hasAddedTask;
    }

    private boolean addTodo(String userInput) {
        boolean hasAddedTask;
        String description = "";
        try {
            description = deriveDescription(userInput, "todo:");
        } catch (DukeEmptyStringException error) {
            printMessage("The description for todo: cannot be empty");
            return false;
        }
        taskList[trackList] = new Todo(description);
        trackList++;
        printMessage(" noting down your wish -> " + taskList[trackList - 1]);
        hasAddedTask = true;
        return hasAddedTask;
    }

    private void tickTaskCompleted(String userInput, boolean isCompleted) {
        String choice = userInput.substring(userInput.indexOf(' ') + 1);
        int choiceNumber = Integer.parseInt(choice) - 1;
        taskList[choiceNumber].setCompleted(isCompleted);
        System.out.println(taskList[choiceNumber]);
    }

    private void untickTask(String userInput) {
        tickTaskCompleted(userInput, false);
    }

    public void printNoted() {
        printMessage("noting down your wish -> " + taskList[trackList - 1]);
    }

    public void printList() {
        printMessage(" ** These are your wishes **");
        for (int i = 0; i < trackList; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        System.out.println("We reached the end of the list. Anymore wish?");
    }

    public void printMessage(String message) {
        System.out.println(BOT_NAME + message);
    }
}
