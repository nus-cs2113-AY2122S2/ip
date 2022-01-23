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
            getCommand(input);
            if(input.equals("bye")){
                break;
            }
        }
    }

    public static void getCommand (String input){
        switch (input) {
        case "bye":
            Command.exit();
            break;
        case "list":
            Command.list();
            break;
        default:
            Command.add(input);
            break;
        }
    }


}
