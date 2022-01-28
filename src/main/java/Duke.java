import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________"
                + "\nHello! I'm Dook!"
                + "\nWhat can I do for you?"
                + "\n____________________________________________________________");
        String[] userInputList = new String[100];
        int userInputIndex = 0;
        Scanner scannerInput = new Scanner(System.in);
        boolean loopInput = true;
        while (loopInput) {
            String userInput = scannerInput.nextLine();
            switch (userInput) {
            case "bye":
                loopInput = false;
                break;
            case "list":
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i<userInputIndex; i++) {
                    System.out.println(String.valueOf(i+1)+". "+userInputList[i]);
                }
                System.out.println("____________________________________________________________\n");
                break;
            default:
                userInputList[userInputIndex] = userInput;
                System.out.println("____________________________________________________________"
                        + "\nadded: " + userInput
                        + "\n____________________________________________________________");
                userInputIndex++;
                break;
            }
        }
        scannerInput.close();

        System.out.println("Bye. Hope to see you again soon!"
                + "\n____________________________________________________________");
    }
}
