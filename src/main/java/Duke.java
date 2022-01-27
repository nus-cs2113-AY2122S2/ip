import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
        exit();
    }

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void greet(){
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void exit(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void echo(){
        Scanner sc = new Scanner(System.in);

        String response;
        response = sc.nextLine();
        while (!(response.equals("bye"))){
            printLine();
            System.out.println(response);
            printLine();
            response = sc.nextLine();
        }
        return;
    }
}
