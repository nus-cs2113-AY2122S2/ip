import java.util.Scanner;

public class Duke {

    public static void echoCommand(String command){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t"+command);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void sayGoobye(String command){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(""+logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Duke.");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        System.out.println();

        String command;
        while (true) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();

            if (command.contentEquals("bye")){
                sayGoobye(command);
                break;
            }
            else {
                echoCommand(command);
            }

        }

    }
}
