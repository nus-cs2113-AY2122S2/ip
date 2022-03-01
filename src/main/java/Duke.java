import java.util.Scanner;
import java.io.IOException;

public class Duke {

    public static final String PREFIX = "  \\[T]\\[ ]";

    public static void main(String[] args) throws IOException {
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
