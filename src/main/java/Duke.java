import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {

    public static final String COMMAND_FORMAT = "(\\S+)(.*)";

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
        //this setup assumes the user is being cooperative
        String[] taskList = new String[100];
        Boolean[] markedTaskList = new Boolean[100];
        int userInputIndex = 0;
        Scanner scannerInput = new Scanner(System.in);
        boolean loopInput = true;
        int markIndex;
        while (loopInput) {
            String userInput = scannerInput.nextLine();
            //split userinput by regex
            Pattern commandPattern = Pattern.compile(COMMAND_FORMAT);
            Matcher commandMatcher = commandPattern.matcher(userInput);
            commandMatcher.find();
            String commandInput = commandMatcher.group(1);
            switch (commandInput) {
            case "bye":
                loopInput = false;
                break;
            case "list":
                System.out.println("____________________________________________________________\n");
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i<userInputIndex; i++) {
                    String printString = String.format("%d. [%c] %s", i+1, markedTaskList[i]?'X':' ', taskList[i]);
                    System.out.println(printString);
                }
                System.out.println("____________________________________________________________\n");
                break;
            case "mark":
                markIndex = Integer.valueOf(commandMatcher.group(2).strip());
                markedTaskList[markIndex] = true;
                System.out.println("____________________________________________________________"
                        + "\nNice! I've marked this task as done:"
                        + "\n[X] " + taskList[markIndex]
                        + "\n____________________________________________________________");
                break;
            case "unmark":
                markIndex = Integer.valueOf(commandMatcher.group(2).strip());
                markedTaskList[markIndex] = false;
                System.out.println("____________________________________________________________"
                        + "\nOK, I've marked this task as not done yet:"
                        + "\n[ ] " + taskList[markIndex]
                        + "\n____________________________________________________________");
                break;
            default:
                taskList[userInputIndex] = userInput;
                markedTaskList[userInputIndex] = false;
                System.out.println("____________________________________________________________"
                        + "\nadded: " + userInput
                        + "\n____________________________________________________________");
                userInputIndex++;
                break;
            }
        }
        scannerInput.close();

        System.out.println("____________________________________________________________"
                + "\nBye. Hope to see you again soon!"
                + "\n____________________________________________________________");
    }
}
