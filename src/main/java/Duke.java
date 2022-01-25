import java.util.Scanner;

public class Duke {
    public static void greet() {
        printLine();
        System.out.println("Hi there! I'm Domo the chatbot.\nWhat would you like to do?");
    }

    public static void exit() {
        printLine();
        System.out.println("Goodbye. Hope to see you again soon!");
        printDomo();
    }

    public static void parseCommand(Task[] tasks, int numTasks) {
        String command = getCommand();
        while (!isBye(command)) {
            if (isValidCommand(command)) {
                System.out.println("\nCommand accepted!");
                switch(command) {
                case "list":
                    System.out.println("Here is your list so far:");
                    listTasks(tasks, numTasks);
                    break;
                case "mark":
                    System.out.println("Which task would you like to mark as done?");
                    listTasks(tasks,numTasks);
                    int markChoice = getInteger();
                    tasks[markChoice - 1].markAsDone();
                    break;
                case "unmark":
                    System.out.println("Which task would you like to unmark as done?");
                    listTasks(tasks,numTasks);
                    int unmarkChoice = getInteger();
                    tasks[unmarkChoice - 1].markAsNotDone();
                    break;
                }
            } else {
                Task t = new Task(command);
                tasks[numTasks] = t;
                numTasks += 1;
            }
            printBlankLine();
            command = getCommand();
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int numTasks = 0;
        greet();
        parseCommand(tasks, numTasks);
        exit();
    }

    public static void listTasks(Task[] tasks, int numTasks) {
        for (int i = 0; i < numTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getTask());
        }
    }

    public static String getCommand() {
        System.out.println("Type a valid command (bye, list, mark, unmark) or add a task to your list:");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static int getInteger() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static Boolean isBye(String command) {
        return command.equals("bye");
    }

    public static Boolean isList(String command) {
        return command.equals("list");
    }

    public static Boolean isMarkAsDone(String command) {
        return command.equals("mark");
    }

    public static Boolean isUnmarkAsDone(String command) {
        return command.equals("unmark");
    }

    public static Boolean isValidCommand(String command) {
        return isList(command) || isMarkAsDone(command) || isUnmarkAsDone(command);
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

    public static void printBlankLine() {
        System.out.println("");
    }

    public static void printLine() {
        System.out.println("───────────────────────────────────");
    }
}
