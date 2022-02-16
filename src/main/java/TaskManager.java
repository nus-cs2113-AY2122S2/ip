import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private final String BOT_NAME = "[iWish]: ";
    private ArrayList<Task> taskList = new ArrayList<>();
    private final char INDICATE_DEADLINE = '~';
    private final char INDICATE_EVENT = '@';
    private TaskFileManager taskFileManager = new TaskFileManager();

    public void startUp() {
        String userInput = "";
        loadTaskFile();
        Scanner in = new Scanner(System.in);
        boolean hasAddedTask;
        boolean hasUpdate = false;
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
                hasUpdate = true;
            } else if (userInput.contains("tick")) {
                tickTaskCompleted(userInput, true);
            } else if ((userInput.contains("delete"))) {
                deleteTask(userInput);
                hasUpdate = true;
            } else {
                hasAddedTask = isTaskAdded(userInput, hasAddedTask);
            }
            if (hasAddedTask) {
                printNumberOfWish();
            }
            if (hasAddedTask || hasUpdate) {
                updateToFile();
            }
        }
    }

    private void updateToFile() {
        try {
            taskFileManager.saveTaskList("wishlist.txt", taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTaskFile() {
        try {
            taskFileManager.loadTaskList("wishlist.txt", taskList);
        } catch (IOException e) {
            System.out.println("--not valid file--");
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

    private void deleteTask(String userInput) {
        String choice = userInput.substring(userInput.indexOf(' ') + 1);
        int choiceNumber = Integer.parseInt(choice) - 1;
        System.out.println("oh no, this task has been deleted: " + System.lineSeparator() + taskList.get(choiceNumber));
        taskList.remove(choiceNumber);
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
        System.out.println("Now you have " + taskList.size() + " wish(es) in the list.");
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
        taskList.add(new Event(description, at));
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
        taskList.add(new Deadline(description, by));
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
        taskList.add(new Todo(description));
        printMessage(" noting down your wish -> " + taskList.get(taskList.size() - 1));
        hasAddedTask = true;
        return hasAddedTask;
    }

    private void tickTaskCompleted(String userInput, boolean isCompleted) {
        String choice = userInput.substring(userInput.indexOf(' ') + 1);
        int choiceNumber = Integer.parseInt(choice) - 1;
        taskList.get(choiceNumber).setCompleted(isCompleted);
        System.out.println(taskList.get(choiceNumber));
    }

    private void untickTask(String userInput) {
        tickTaskCompleted(userInput, false);
    }

    public void printNoted() {
        printMessage("noting down your wish -> " + taskList.get(taskList.size() - 1));
    }

    public void printList() {
        printMessage(" ** These are your wishes **");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("We reached the end of the list. Anymore wish?");
    }

    public void printMessage(String message) {
        System.out.println(BOT_NAME + message);
    }
}


