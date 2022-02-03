import java.util.Scanner;

public class Duke {
    public static void greet() {
        String logo = "   ________                            \n"
                + "  / ____/ /_  ___  ___  ____ ___  _____\n"
                + " / /   / __ \\/ _ \\/ _ \\/ __ `__ \\/ ___/\n"
                + "/ /___/ / / /  __/  __/ / / / / (__  ) \n"
                + "\\____/_/ /_/\\___/\\___/_/ /_/ /_/____/  \n"
                + "                                       \n"
                + "-------------------------------------------\n";
        String greet = "Hemlo from\n" + logo + "Whamt cam cheems do for you?\n"
                + "-------------------------------------------";
        System.out.println(greet);
    }

    public static void farewell() {
        String farewell = "Goodbye. See you next time frem!\n" + "-------------------------------------------";
        System.out.println(farewell);
    }

    public static void addList() {
        int ind;
        String command;
        Task[] tasks = new Task[100];
        int index = 0;
        String separator = "-------------------------------------------";
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        String[] commands = command.split(" ");
        System.out.println(separator);
        do {
            if (command.equals("list")) {
                for (int i = 0; i < index; i++) {
                    String number = String.valueOf(i + 1) + ". ";
                    System.out.print(number);
                    tasks[i].printTask();
                }
                System.out.println(separator);
            } else if (commands[0].equals("mark")) {
                ind = Integer.parseInt(commands[1]) - 1;
                tasks[ind].setDone(true);
                System.out.println("Ok! I hamve marked the task:");
                tasks[ind].printTask();
            } else if (commands[0].equals("unmark")) {
                ind = Integer.parseInt(commands[1]) - 1;
                tasks[ind].setDone(false);
                System.out.println("Ok! I hamve unmarked the task:");
                tasks[ind].printTask();
            } else {
                Task task = new Task(command);
                tasks[index++] = task;
                System.out.println("I hamve added: " + command);
                System.out.println(separator);
            }
            in = new Scanner(System.in);
            command = in.nextLine();
            commands = command.split(" ");
            System.out.println(separator);
        } while (!command.equals("bye"));
    }

    public static void main(String[] args) {
        greet();
        addList();
        farewell();
    }

}
