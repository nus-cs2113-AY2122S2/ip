import java.util.Locale;
import java.util.Scanner;
import util.Task;

public class Bob {

    public static void PrintBorder() {
        String border = "\t____________________________________________________________";
        System.out.println(border);
    }

    public static void Greetings() {
        PrintBorder();
        String logo = "\t.______     ______   .______   \n"
                + "\t|   _  \\   /  __  \\  |   _  \\  \n"
                + "\t|  |_)  | |  |  |  | |  |_)  | \n"
                + "\t|   _  <  |  |  |  | |   _  <  \n"
                + "\t|  |_)  | |  `--'  | |  |_)  | \n"
                + "\t|______/   \\______/  |______/  \n\n"
                + "\tHello! I'm Bob\n"
                + "\tWhat can I do for you?";
        System.out.println(logo);
        PrintBorder();
    }

    public static void GoodBye() {
        PrintBorder();
        System.out.println("\tSee next time. /|\\(◦.◦)/|\\");
        PrintBorder();
    }

    public static void DisplayList(Task[] list) {
        int count = Task.getCount();

        PrintBorder();
        System.out.println("\tTask list:");
        for (int i = 0; i < count; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.print("[" + list[i].getStatusSymbol() + "] ");
            System.out.println(list[i].getDescription());
        }
        PrintBorder();
    }

    public static void UpdateStatus(Task[] list, String command, boolean doneStatus) {
        if (command.split(" ").length != 2) {
            System.out.println("\tInvalid number of arguments.");
            return;
        }
        int id = Integer.parseInt(command.split(" ")[1]);
        if (id > Task.getCount()) {
            System.out.println("\tInvalid task id detected.");
            return;
        }
        Task target = list[id - 1];
        target.setDone(doneStatus);

        PrintBorder();
        if (doneStatus) {
            System.out.println("\tThe following task has been checked off:");
        }
        else {
            System.out.println("\tThe following task has yet to be completed:");
        }
        System.out.println("\t  [" + target.getStatusSymbol() + "] " + target.getDescription());
        PrintBorder();
    }

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];

        Greetings();
        do {
            System.out.println("");
            command = in.nextLine();

            switch (command.split(" ")[0]) {
            case "list":
                DisplayList(list);
                continue;
            case "mark":
                UpdateStatus(list, command, true);
                continue;
            case "unmark":
                UpdateStatus(list, command, false);
                continue;
            case "bye":
                break;
            default:
                // the 'Add' function
                Task temp = new Task(command);
                list[Task.getCount() - 1] = temp;

                PrintBorder();
                System.out.println("\tadded:" + command);
                PrintBorder();
            }
        } while (command.split(" ")[0].compareTo("bye") != 0);

        GoodBye();
    }
}
