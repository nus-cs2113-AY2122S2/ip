import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Command.greetUser();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true){
            input = sc.nextLine();
            if (input.equals("bye")){
                Command.exit();
                break;
            }
            Command.echo(input);
        }
    }


}
