import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    static int idx = 0;
    static String[] list = new String[100];
    static boolean[] marked = new boolean[100];

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
        int separator;
        int index;
        while (true) {
            ans = input.nextLine();
            separator = ans.indexOf(' ');
            if (separator == -1) {
                command = ans;
            } else {
                command = ans.substring(0, separator);
            }
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println("    _________________________________________");
                System.out.println("    Here the task you've written m'lord:");
                printList();
                System.out.println("    _________________________________________\n");
            } else if (command.equals("mark")) {
                ans = ans.substring((separator == -1) ? 0 : separator + 1);
                index = Integer.parseInt(ans) - 1;
                mark(index);
                System.out.println("    _________________________________________");
                System.out.println("    I've marked the task as done m'lord:");
                printList();
                System.out.println("    _________________________________________\n");
            } else if (command.equals("unmark")) {
                ans = ans.substring((separator == -1) ? 0 : separator + 1);
                index = Integer.parseInt(ans) - 1;
                unmark(index);
                System.out.println("    _________________________________________");
                System.out.println("    I've revert the task to active m'lord:");
                printList();
                System.out.println("    _________________________________________\n");
            } else {
                System.out.println("    _________________________________________");
                add(ans);
                System.out.println("     added: " + ans);
                System.out.println("    _________________________________________\n");
            }
        }
        System.out.println("    _________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________________");
        input.close();
    }

    public static void add(String s) {
        list[idx] = s;
        idx++;
    }

    public static void mark(int i) {
        marked[i] = true;
    }

    public static void unmark(int i) {
        marked[i] = false;
    }

    public static void printList() {
        String marker = "";
        for (int i = 0; i < idx; i++) {
            if (marked[i]) {
                marker = "X";
            } else {
                marker = " ";
            }
            System.out.println("    " + (i + 1) + " [" + marker + "] " + list[i]);
        }
    }
}
