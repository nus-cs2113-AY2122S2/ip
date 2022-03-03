import java.util.Scanner;

public class Duke {

    private static void run() {
        Ui.showWelcomeMessage();
        Storage.loadData();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        Command.executeCommand(userInput, in);
        Storage.saveData();
        Ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        run();
    }
}
