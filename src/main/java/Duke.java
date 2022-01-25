import java.util.Scanner;

public class Duke {
    public static void greet() {
        printLine();
        System.out.println("Hi there! I'm Domo the chatbot.\nWhat would you like to do? " +
                "Type a command/anything you wish!");
    }

    public static void parseCommand(Task[] tasks, int numTasks) {
        String command = getCommand();
        while (!isBye(command)) {
            if (!isList(command)) {
                Task t = new Task(command);
                tasks[numTasks] = t;
                numTasks += 1;
            } else {
                listTasks(tasks, numTasks);
            }
            command = getCommand();
        }
    }

    public static void exit() {
        printLine();
        System.out.println("Goodbye. Hope to see you again soon!");
        printDomo();
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int numTasks = 0;
        greet();
        parseCommand(tasks, numTasks);
        exit();
    }

    public static void listTasks(Task[] tasks, int numTasks) {
        System.out.println("Command accepted! Here is what you have typed so far:");
        for (int i = 0; i < numTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescription());
        }
        System.out.println("Type a command/anything you wish!");
    }

    public static String getCommand() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static Boolean isBye(String command) {
        return command.equals("bye");
    }

    public static Boolean isList(String command) {
        return command.equals("list");
    }

    public static void printDomo() {
        String domoLogo = "─────────▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄─────────\n" +
                "───────▄▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄───────\n" +
                "──────▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒███▒▒▒▒▒▒▒▒███▒▒▒▌──────\n" +
                "▄▄────▐▒▒▒███▒▒▒▒▒▒▒▒███▒▒▒▌────▄▄\n" +
                "▌▒▀▄──▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌──▄▀▒▐\n" +
                "▌▒▒▒▀▄█▒▒▒▄▀▄▄▀▀▄▄▀▀▄▄▀▄▒▒▒█▄▀▒▒▒▐\n" +
                "▀▄▒▒▒▒▐▒▒▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌▒▒▌▒▒▒▒▄▀\n" +
                "──▀▄▒▒▐▒▒▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌▒▒▌▒▒▄▀──\n" +
                "────▀▄▐▒▒▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌▒▒▌▄▀────\n" +
                "──────█▒▒▐▄▀▄▀▀▄▄▀▀▄▄▀▀▄▌▒▒█──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▌──────\n" +
                "──────▐▒▒▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▓▓▄▀▀▄▓▓▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▌──▐▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▌──▐▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▌──▐▒▒▒▒▒▒▒▒▌──────\n" +
                "──────▐▒▒▒▒▒▒▒▒▌──▐▒▒▒▒▒▒▒▒▌──────\n" +
                "───────▀▀▀▀▀▀▀▀────▀▀▀▀▀▀▀▀───────";
        System.out.println(domoLogo);
    }
    public static void printLine() {
        String horizontalLine = "───────────────────────────────────";
        System.out.println(horizontalLine);
    }
}
