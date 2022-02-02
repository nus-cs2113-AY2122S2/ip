import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int counter = 0;

    public static void printWithDivider(String stringWithinDivider) {
        String breakLine = "\t____________________________________________________________";
        System.out.println(breakLine);
        stringWithinDivider = stringWithinDivider.replace("\n", "\n\t");
        System.out.println("\t" + stringWithinDivider);
        System.out.println(breakLine);
    }

    public static String numerateList(ArrayList<Task> list) {
        String output = "";
        int number = 1 ;
        for (Task item : list) {
            output += String.format("%d. %s", number, item.toString());
            if (number != list.size()) {
                output += "\n";
            }
            number ++;
        }
        return output;
    }

    public static void markCompleted (Task task) {
        task.setCompleted(true);
        printWithDivider(task.toString());
    }
    public static void unmarkCompleted (Task task) {
        task.setCompleted(false);
        printWithDivider(task.toString());
    }

    public static void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printWithDivider("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void addTask(String type, String description) {
        Task toBeAdded;
        String taskName = description;
        String addInfo = "";
        int divider = description.indexOf("/");
        if (divider != -1) {
           taskName = description.substring(0,divider);
           addInfo = description.substring(divider+1);
        }

        switch (type) {
        case "todo":
            toBeAdded = new ToDo(taskName);
            break;
        case "deadline":
            toBeAdded =  new Deadline(taskName, addInfo);
            break;
        case "event":
            toBeAdded = new Event(taskName, addInfo);
            break;
        default:
            toBeAdded = new Task(taskName);
            break;

        }
        counter ++;
        tasks.add(toBeAdded);

        printWithDivider("Got it. I've added this task:\n\t" + toBeAdded.toString()
                + String.format("\nNow you have %d task%s in the list.", counter, counter>1 ? "s" : ""));

    }

    public static void main(String[] args) {

        hello();

        //Level-1
        Scanner sc = new Scanner (System.in);


        String line = sc.nextLine();
        boolean sayByeAlready = false;


        while (!sayByeAlready){

            int divider = line.indexOf(' ');

            String command = line;
            String description = "";

            if (divider != -1) {
               command = line.substring(0, divider);
               description = line.substring(divider+1);
            }

            switch(command) {
            case "bye":
                sayByeAlready = true;
                break;
            case "mark":
                markCompleted(tasks.get(Integer.parseInt(description)-1));
                break;
            case "unmark":
                unmarkCompleted(tasks.get(Integer.parseInt(description)-1));
                break;
            case "list" :
                printWithDivider(numerateList(tasks));
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(command, description);
                break;
            default:
                addTask("task", line);
            }

            line = sc.nextLine();
        }

        printWithDivider("Bye. Hope to see you again soon!");

    }
}
