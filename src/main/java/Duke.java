import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    static int idx = 0;
    static String[] list = new String[100];
    static boolean[] marked = new boolean[100];
    static String[] importance = new String[100];
    static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        Arrays.fill(marked, Boolean.FALSE);
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner input = new Scanner(System.in);
        System.out.println("    _________________________________________");
        System.out.println("    Hello I'm Duke\n    What can I help you with?");
        System.out.println("    _________________________________________");
        String ans = "";

        String command = "";
        String time = "";
        int commandSeparator;
        int timeSeparator;
        int index;
        boolean loopInput = true;
        while (loopInput) {
            ans = input.nextLine();
            commandSeparator = ans.indexOf(' ');
            if (commandSeparator == -1) {
                command = ans;
            } else {
                command = ans.substring(0, commandSeparator);
            }
            timeSeparator = ans.indexOf('/');
            switch (command) {
            case "bye":
                loopInput = false;
                break;
            case "list":
                System.out.println("    _________________________________________");
                System.out.println("    Here the task you've written m'lord:");
                printTasks();
                System.out.println("    _________________________________________\n");
                break;
            case "mark":
                ans = ans.substring(commandSeparator + 1);
                index = Integer.parseInt(ans) - 1;
                mark(index);
                System.out.println("    _________________________________________");
                System.out.println("    I've marked the task as done m'lord:");
                printTasks();
                System.out.println("    _________________________________________\n");
                break;
            case "unmark":
                ans = ans.substring(commandSeparator + 1);
                index = Integer.parseInt(ans) - 1;
                unmark(index);
                System.out.println("    _________________________________________");
                System.out.println("    I've revert the task to active m'lord:");
                printTasks();
                System.out.println("    _________________________________________\n");
                break;
            case "todo":
                ans = ans.substring(commandSeparator + 1);
                System.out.println("    _________________________________________");
                addTask(ans, 'T', time);
                System.out.println("     added: " + tasks[idx - 1]);
                System.out.println("     there are currently " + idx + " tasks ");
                System.out.println("    _________________________________________\n");
                break;
            case "deadline":
                // timeSeparator = (timeSeparator == -1 ? ans.length() : timeSeparator);
                if (timeSeparator == -1) {
                    time = "";
                } else {
                    time = ans.substring(timeSeparator + 4);
                }
                ans = ans.substring(commandSeparator + 1, timeSeparator);
                System.out.println("    _________________________________________");
                addTask(ans, 'D', time);
                System.out.println("     added: " + tasks[idx - 1]);
                System.out.println("     there are currently " + idx + " tasks ");
                System.out.println("    _________________________________________\n");
                break;
            case "event":
                // timeSeparator = (timeSeparator == -1 ? ans.length() : timeSeparator);
                if (timeSeparator == -1) {
                    time = "";
                } else {
                    time = ans.substring(timeSeparator + 4);
                }
                ans = ans.substring(commandSeparator + 1, timeSeparator);
                System.out.println("    _________________________________________");
                addTask(ans, 'E', time);
                System.out.println("     added: " + tasks[idx - 1]);
                System.out.println("     there are currently " + idx + " tasks ");
                System.out.println("    _________________________________________\n");
                break;
            default:
                loopInput = false;
                break;
            }
        }

        System.out.println("    _________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________________");
        input.close();
    }

    public static void addTask(String desc, char type, String time) {
        tasks[idx] = new Task(desc, false, type, time);
        idx++;
    }

    public static void mark(int i) {
        tasks[i].setIsDone(true);
    }

    public static void unmark(int i) {
        tasks[i].setIsDone(false);
    }

    public static void printTasks() {
        for (int i = 0; i < idx; i++) {
            System.out.println("    " + (i + 1) + " " + tasks[i]);
        }
    }

}
