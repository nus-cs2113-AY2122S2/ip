import java.util.ArrayList;

public class UiManager {
    private String botName;

    public UiManager(String botName) {
        this.botName = botName;
    }

    public void printExitMessage() {
        printMessage("Bye! Hope to hear from you soon :)");
    }

    public void printNumberOfWish(int numTask) {
        System.out.println("Now you have " + numTask + " wish(es) in the list.");
    }

    public void printNoted(Task newTask) {
        printMessage("noting down your wish -> " + newTask);
    }

    public void printList(ArrayList<Task> taskList) {
        printMessage(" ** These are your wishes **");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("We reached the end of the list. Anymore wish?");
    }

    public void printMessage(String message) {
        System.out.println(botName + message);
    }

    public void printError(String message) {
        System.out.println(botName + "ERROR ERROR!!!! -----> " + message);
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }
}
