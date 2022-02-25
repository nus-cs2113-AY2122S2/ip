import java.util.Scanner;

public class Duke {

    private static void endProgramme() {
        Storage.save();
        Ui.printBye();
    }

    private static void startProgramme() {
        Scanner in = new Scanner(System.in);
        String userInput = Ui.readCommand(in);
        Parser.parseInput(userInput, in);
        endProgramme();
    }

    public static void main(String[] args) {
        Ui.printWelcome();
        Storage.load();
        startProgramme();
    }

}
