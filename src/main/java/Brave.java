import java.util.Scanner;
import java.lang.String;

public class Brave {
    public static void main(String[] args) {
        String input;
        String[] inputSplitted;
        String command;
        Scanner in = new Scanner(System.in);
        TaskManager tasks = new TaskManager();

        tasks.showWelcomeMessage();


        while (true) {
            input = in.nextLine();
            inputSplitted = input.split(" ", 2);
            command = inputSplitted[0];

            if (command.startsWith("bye")) {
                break;
            }

            switch (command) {
            case "list":
                tasks.printTaskList();
                break;
            case "mark":
                tasks.markTask(Integer.parseInt(inputSplitted[1]) - 1);
                break;
            case "unmark":
                tasks.unmarkTask(Integer.parseInt(inputSplitted[1]) - 1);
                break;
            default:
                tasks.addTask(input);
            }
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye, Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }


}
