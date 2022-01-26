import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
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

    public static void main(String[] args) {

        hello();

        //Level-1
        Scanner sc = new Scanner (System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        int counter = 0;

        String line = sc.nextLine();
        boolean sayByeAlready = false;


        while (!sayByeAlready){

            int divider = line.indexOf(' ');

            String command = line;
            String number = "";
            if (divider != -1) {
               command = line.substring(0, divider);
               number = line.substring(divider+1);
            }

            switch(command) {
            case "bye":
                sayByeAlready = true;
                break;
            case "mark":
                markCompleted(tasks.get(Integer.parseInt(number)-1));
                break;
            case "unmark":
                unmarkCompleted(tasks.get(Integer.parseInt(number)-1));
                break;
            case "list" :
                printWithDivider(numerateList(tasks));
                break;
            default:
                printWithDivider("added: " + line);
                tasks.add(new Task(line));
                counter ++;
            }

            line = sc.nextLine();
        }

        printWithDivider("Bye. Hope to see you again soon!");

    }
}
