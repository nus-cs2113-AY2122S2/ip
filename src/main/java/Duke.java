import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printFormat(" Hey there! I'm Duke\n" +
                " What can I do for you? uwu");

        int num = 1;
        String line;
        Scanner in = new Scanner(System.in);

        Task[] list = new Task[100];
        int i = 0;

        while (num == 1) {
            line = in.nextLine();
            switch (line) {
            case "bye":
                num = 0;
                printFormat(" Aw, are you leaving now?\n" +
                        " Hope to see you again soon!\n");
                break;
            case "list":
                String listAsString = "";
                for (int k = 0; k < i; k++) {
                    Task curr = list[k];
                    listAsString += (" " + Integer.toString(k + 1) + ". " +
                            "[" + curr.getStatusIcon() + "] " + curr.description + "\n");
                    printFormat(listAsString);
                }
                break;
            default:
                if (line.contains("unmark")) {
                    markStatus(false, line, list);
                } else if (line.contains("mark")) {
                    markStatus(true, line, list);
                } else {
                    list[i] = new Task(line);
                    i++;
                    printFormat(" added: " + line);
                }
            }
        }
    }

    public static void printFormat(String s) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ ⸙ ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                s + "\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ ⸙ ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }

    public static void markStatus(Boolean shouldMark, String line, Task[] list) {
        try {
            int taskNum = Integer.parseInt(line.split(" ", 0)[1]);
            Task curr = list[taskNum - 1];
            if (shouldMark) {
                curr.setDone(true);
                printFormat(" Nice! I've marked this task as done:\n" +
                        " [" + curr.getStatusIcon() + "] " + curr.description);
            } else {
                curr.setDone(false);
                printFormat(" OK, I've marked this task as not done yet:\n" +
                        " [" + curr.getStatusIcon() + "] " + curr.description);
            }
        } catch (Exception exception) {
            printFormat(" Please mark / unmark a number that's in the list :')");
        }
    }
}
