import java.util.Scanner;

public class Duke {
    protected static Task[] tasks = new Task[100];
    protected static int numTasks = 0;

    public static void main(String[] args) {
        greet();
        acceptCommand();
        exit();
    }

    public static void greet() {
        printLine();
        System.out.println("Hi there! I'm Domo the chat bot.\nWhat would you like to do?");
    }

    public static void exit() {
        printLine();
        System.out.println("Goodbye. Hope to see you again soon!");
        printDomo();
    }

    public static void acceptCommand() {
        String input = getInput();
        while (!isBye(input)) {
            try {
                checkIfValidCommand(input);
                executeCommand(input);
            } catch (DukeException error){
                System.out.println(error.getMessage());
            }
            input = getInput();
        }
    }

    public static void checkIfValidCommand(String input) throws DukeException {
        if (!isValidCommand(input)) {
            if (input.length() == 0) {
                throw new DukeException("Hmmm... you didn't type anything. Please try again using a valid command!");
            } else {
                throw new DukeException("Sorry, command is not recognised. Please try again using a valid command!");
            }
        }
    }

    public static void executeCommand(String input) {
        String command = getCommand(input);
        System.out.println("\nCommand accepted!");
        switch(command) {
        case "list":
            System.out.println("Here is your list so far:");
            listTasks();
            break;
        case "mark":
            executeMark();
            break;
        case "unmark":
            executeUnmark();
            break;
        case "todo":
            executeTodo(input);
            break;
        case "deadline":
            executeDeadline(input);
            break;
        case "event":
            executeEvent(input);
            break;
        }
    }

    public static void executeMark() {
        System.out.println("Which task would you like to mark as done?");
        listTasks();
        int index = getInteger() - 1;
        tasks[index].markAsDone();
        System.out.println("Congrats! You've completed:\n" + tasks[index]);
    }

    public static void executeUnmark() {
        System.out.println("Which task would you like to unmark as done?");
        listTasks();
        int index = getInteger() - 1;
        tasks[index].markAsNotDone();
        System.out.println("Aw, you've marked this as undone:\n" + tasks[index]);
    }

    public static void executeTodo(String input) {
        String description = getDescription(input);
        Todo t = new Todo(description);
        tasks[numTasks] = t;
        numTasks += 1;
    }

    public static void executeDeadline(String input) {
        String[] parsedCommand = parseDeadlineOrEvent(input);
        String description = parsedCommand[0];
        String by = parsedCommand[1];
        Deadline t = new Deadline(description, by);
        tasks[numTasks] = t;
        numTasks += 1;
    }

    public static void executeEvent(String input) {
        String[] parsedCommand = parseDeadlineOrEvent(input);
        String description = parsedCommand[0];
        String at = parsedCommand[1];
        Event t = new Event(description, at);
        tasks[numTasks] = t;
        numTasks += 1;
    }

    //Returns a String array with the first element as the description  and the second element as the deadline/time period
    public static String[] parseDeadlineOrEvent(String input) {
        String command = getCommand(input);
        String[] inputArray;
        if (command.equals("deadline")) {
            inputArray = input.split(" /by ");
        } else {
            inputArray = input.split(" /at ");
        }
        inputArray[0] = inputArray[0].substring(inputArray[0].indexOf(" "));
        return inputArray;
    }

    public static void listTasks() {
        if (numTasks == 0) {
            System.out.println("You don't seem to have any tasks so far!");
        } else {
            for (int i = 0; i < numTasks; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }

    public static String getCommand(String input) {
        String[] arrayOfInput = input.split(" ", 0);
        return arrayOfInput[0];
    }

    public static String getDescription(String input) {
        return input.substring(input.indexOf(" "));
    }

    public static String getInput() {
        System.out.println("Type a valid command (bye, list, mark, unmark, todo, deadline, event) or add a task to your list:");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static int getInteger() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static boolean isValidCommand(String command) {
        return isList(command) || isMarkAsDone(command) || isUnmarkAsDone(command) || isTodo(command)
                || isDeadline(command) || isEvent(command);
    }

    public static boolean isBye(String command) {
        return command.equals("bye");
    }

    public static boolean isList(String command) {
        return command.equals("list");
    }

    public static boolean isMarkAsDone(String command) {
        return command.equals("mark");
    }

    public static boolean isUnmarkAsDone(String command) {
        return command.equals("unmark");
    }

    public static boolean isTodo(String command) {
        return command.equals("todo");
    }

    public static boolean isDeadline(String command) {
        return command.equals("deadline");
    }

    public static boolean isEvent(String command) {
        return command.equals("event");
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
        System.out.println("───────────────────────────────────");
    }
}
