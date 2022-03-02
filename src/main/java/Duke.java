import java.util.Scanner;

public class Duke {

    /**
     * main method that calls other methods when the program starts
     * @param args arguments passed to the program
     */
    public static void main(String[] args) {
        Storage.checkFile();
        greet();
        TaskList.executeCommands();
    }

    private static void greet() {
        System.out.println(UI.LOGO);
        System.out.println(UI.MESSAGE_GREET);
    }

    public static void scanInput(Task task) {
        Scanner in = new Scanner(System.in);
        task.instruction = in.nextLine();
    }
}
