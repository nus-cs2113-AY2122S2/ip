import java.util.Scanner;

public class Duke {

    private static final String SEPARATOR = "-------------------------------------------";
    private static int numberOfTasks = 0;
    private static Task[] tasks = new Task[100];
    private static Scanner in = new Scanner(System.in);

    /**
     * Prints the greeting of Cheems.
     */
    public static void greet() {
        String logo = "   ________                            \n"
                + "  / ____/ /_  ___  ___  ____ ___  _____\n"
                + " / /   / __ \\/ _ \\/ _ \\/ __ `__ \\/ ___/\n"
                + "/ /___/ / / /  __/  __/ / / / / (__  ) \n"
                + "\\____/_/ /_/\\___/\\___/_/ /_/ /_/____/  \n"
                + "                                       \n"
                + SEPARATOR;
        String greet = "Hemlo from\n" + logo + "\nWhamt cam cheems do for you?";
        System.out.println(greet);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the farewell of Cheems.
     */
    public static void farewell() {
        String farewell = "Goomdbye. See you nemxt time frem!";
        System.out.println(farewell);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints all the Tasks that are stored
     */
    public static void printTasks() {
        String entryNumber;
        System.out.println("Hemre are the tamsks im youmr limst:");
        for (int i = 0; i < numberOfTasks; i++) {
            entryNumber = String.valueOf(i + 1) + ". ";
            System.out.print(entryNumber);
            System.out.println(tasks[i]);
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Sets the selected Task status to Done and prints the selected Task.
     *
     * @param command The command given from input.
     */
    public static void handleMark(String command) {
        int taskPosition = Integer.parseInt(command.substring(5)) - 1;
        tasks[taskPosition].setDone(true);
        System.out.println("Ok! I hamve markemd the tamsk:");
        System.out.println(tasks[taskPosition]);
        System.out.println(SEPARATOR);
    }

    /**
     * Sets the selected Task status to not Done and prints the selected Task.
     *
     * @param command The command given from input.
     */
    public static void handleUnmark(String command) {
        int taskPosition = Integer.parseInt(command.substring(7)) - 1;
        tasks[taskPosition].setDone(false);
        System.out.println("Oof! I hamve unmarkemd the tamsk: ");
        System.out.println(tasks[taskPosition]);
        System.out.println(SEPARATOR);
    }

    /**
     * Creates a new Deadline and prints the new Deadline.
     *
     * @param command The command given from input
     */
    public static void addDeadline(String command) {
        int dueDateIndex = command.indexOf("/") + 1;
        String dueDate = command.substring(dueDateIndex);
        String description = command.substring(9, dueDateIndex - 1);
        Deadline newDeadline = new Deadline(description, dueDate);
        tasks[numberOfTasks++] = newDeadline;
        System.out.println("I hamve addemd: ");
        System.out.println(newDeadline);
        System.out.println(SEPARATOR);
    }

    /**
     * Creates a new ToDo and prints the new ToDo.
     *
     * @param command The command given from input
     */
    public static void addToDo(String command) {
        String description = command.substring(5);
        ToDo newToDo = new ToDo(description);
        tasks[numberOfTasks++] = newToDo;
        System.out.println("I hamve addemd: ");
        System.out.println(newToDo);
        System.out.println(SEPARATOR);
    }

    /**
     * Creates a new Event and prints the new Event.
     *
     * @param command The command given from input
     */
    public static void addEvent(String command) {
        int timingIndex = command.indexOf("/") + 1;
        String timing = command.substring(timingIndex);
        String description = command.substring(6, timingIndex - 1);
        Event newEvent = new Event(description, timing);
        tasks[numberOfTasks++] = newEvent;
        System.out.println("I hamve addemd: ");
        System.out.println(newEvent);
        System.out.println(SEPARATOR);
    }

    /**
     * Keeps asking for inputs until the input is "bye".
    */
    public static void askInput() {
        String command = in.nextLine();
        System.out.println(SEPARATOR);
        do {
            if (command.startsWith("list")) {
                printTasks();
            } else if (command.startsWith("mark")) {
                handleMark(command);
            } else if (command.startsWith("unmark")) {
                handleUnmark(command);
            } else if (command.startsWith("deadline")) {
                addDeadline(command);
            } else if (command.startsWith("todo")) {
                addToDo(command);
            } else if (command.startsWith("event")) {
                addEvent(command);
            } else {
                System.out.println("Unknomwn command! Try agaimn.");
            }
            command = in.nextLine();
            System.out.println(SEPARATOR);
        } while (!command.equals("bye"));
    }

    public static void main(String[] args) {
        greet();
        askInput();
        farewell();
    }
}
