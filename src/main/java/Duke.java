import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final Ui ui = null;
    private static final Parser parser = null;
    private static TaskList tasklist = new TaskList();
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println(ui.printLogo());
        System.out.println(ui.greet());
        String command = parser.getCommand();

        while(!command.equals("bye")) {
            if(command.equals("list")) {
                System.out.println(line + tasklist + line);
            }
            else {
                Task task = new Task(command);
                tasklist.add(task);
                System.out.println(line + "added: " + command + "\n" + line);
            }
            command = parser.getCommand();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
