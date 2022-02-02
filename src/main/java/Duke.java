import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    //fields
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int counter = 0;

    //utility methods
    public static void printWithDivider(String stringWithinDivider) {
        String breakLine = "\t____________________________________________________________";
        System.out.println(breakLine);
        stringWithinDivider = stringWithinDivider.replace("\n", "\n\t");
        System.out.println("\t" + stringWithinDivider);
        System.out.println(breakLine);
    }
    public static ArrayList<String> processInput(String line, String delimiter) {
        ArrayList<String> words = new ArrayList<>();
        int divider = line.indexOf(delimiter);

        words.add(line);

        if (divider != -1) {
            words.set(0, line.substring(0, divider));
            words.add(line.substring(divider+1));
        }
        return words;
    }

    //command methods
    public static void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printWithDivider("Hello! I'm Duke\nWhat can I do for you?");
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
    public static void addTask(String type, String description) {
        Task toBeAdded;
        ArrayList<String> words = processInput(description, "/");
        String taskName = words.get(0);
        String addInfo = words.size() >= 2 ? words.get(1) : null;

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
    public static void markCompleted (Task task) {
        task.setCompleted(true);
        printWithDivider(task.toString());
    }
    public static void unmarkCompleted (Task task) {
        task.setCompleted(false);
        printWithDivider(task.toString());
    }

    //main
    public static void run (String line) {
        ArrayList<String> words = processInput(line, " ");
        String command = words.get(0);
        String description = words.size()>= 2 ? words.get(1) : null;

        switch(command) {
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
    }

    public static void main(String[] args) {

        hello();

        Scanner sc = new Scanner (System.in);
        String line = sc.nextLine();

        while (!line.equals("bye")){
            run(line);
            line = sc.nextLine();
        }
        printWithDivider("Bye. Hope to see you again soon!");

    }
}
