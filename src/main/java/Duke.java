import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

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
        ArrayList<Task> taskList = new ArrayList<Task>();
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
                for (int i = 0; i<taskList.size(); i++) {
                    String printString = String.format("%d. [%c] %s",
                            i+1,
                            taskList.get(i).getStatusIcon(),
                            taskList.get(i).getDescription());
                    System.out.println(printString);
                }
                System.out.println("____________________________________________________________\n");
                break;
            case "mark":
                markIndex = Integer.valueOf(commandMatcher.group(2).strip())-1;
                taskList.get(markIndex).setStatusIcon(true);
                System.out.println("____________________________________________________________"
                        + "\nNice! I've marked this task as done:"
                        + "\n[X] " + taskList.get(markIndex).getDescription()
                        + "\n____________________________________________________________");
                break;
            case "unmark":
                markIndex = Integer.valueOf(commandMatcher.group(2).strip())-1;
                taskList.get(markIndex).setStatusIcon(false);
                System.out.println("____________________________________________________________"
                        + "\nOK, I've marked this task as not done yet:"
                        + "\n[ ] " + taskList.get(markIndex).getDescription()
                        + "\n____________________________________________________________");
                break;
            default:
                taskList.add(new Task(userInput));
                System.out.println("____________________________________________________________"
                        + "\nadded: " + userInput
                        + "\n____________________________________________________________");
                break;
            }
        }
        scannerInput.close();

        System.out.println("____________________________________________________________"
                + "\nBye. Hope to see you again soon!"
                + "\n____________________________________________________________");
    }
}
