import java.util.Scanner;

public class Duke {
    private static Ui ui = null;
    private static Parser parser = null;

    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println(ui.printLogo());
        System.out.println(ui.greet());
        String command = parser.getCommand();
        while(!command.equals("bye")) {
            System.out.println(parser.echo(command));
            command = parser.getCommand();
        }
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
