import java.util.Scanner;

public class Duke {

    public static void echoMessage(String line){
        String message = "\t____________________________________________________________\n" +
                "\t" + line + "\n" +
                "\t____________________________________________________________";
        System.out.println(message);
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "\t____________________________________________________________\n" +
                "\t Hello! I'm Duke\n" +
                "\t What can I do for you?\n" +
                "\t____________________________________________________________";

        String bye = "\t____________________________________________________________\n" +
                "\t Bye. Hope to see you again soon!\n" +
                "\t____________________________________________________________";

        System.out.println(greeting);

        line = in.nextLine();

        while (!line.contains("bye")){
            echoMessage(line);
            line = in.nextLine();
        }
        System.out.println(bye);
    }
}
