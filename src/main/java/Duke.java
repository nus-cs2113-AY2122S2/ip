import java.util.Scanner;

public class Duke {

    public static String[] storedUserInputs = new String[100];
    public static int numberOfUserInputs = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        respondToUser();
        exit();
    }

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void store(String userInput) {
        storedUserInputs[numberOfUserInputs] = userInput;
        numberOfUserInputs++;
    }

    public static void list() {
        if (numberOfUserInputs == 0) {
            System.out.println("You have not entered any tasks yet!");
            System.out.println("____________________________________________________________");
        } else {
            for (int i = 0; i < numberOfUserInputs; i++) {
                System.out.println(i+1 + ". " + storedUserInputs[i]);
            }
        }
    }


    public static void respondToUser() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        String lowerCaseUserInput = userInput.toLowerCase();
        while (!lowerCaseUserInput.equals("bye")) {
            switch(lowerCaseUserInput) {
            case "list":
                list();
                break;
            default:
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                store(userInput);
                System.out.println("____________________________________________________________");
            }
            userInput = in.nextLine();
            lowerCaseUserInput = userInput.toLowerCase();
        }
    }

    public static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}