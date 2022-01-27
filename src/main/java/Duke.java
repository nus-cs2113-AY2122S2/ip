import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
/*         String greet = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
         System.out.println(greet);*/

        printFormat(" Hello! I'm Duke\n" +
                " What can I do for you?");

/*        String bye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";*/

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
                printFormat(" Bye. Hope to see you again soon!\n");
                break;
            case "list":
                System.out.println("____________________________________________________________");
                for (int k = 0; k < i; k++) {
                    Task curr = list[k];
                    System.out.println(Integer.toString(k+1) + ". " +
                            "[" + curr.getStatusIcon() + "] " + curr.description);
                }
                System.out.println("____________________________________________________________\n");
                break;
            default:
                if (line.contains("unmark")) {
                    markStatus(false, line, list);
                } else if (line.contains("mark")) {
                    markStatus(true, line, list);
                } else {
                    list[i] = new Task(line);
                    i++;
                    printFormat("added: " + line);
                }
            }
        }
    }

    public static void printFormat(String s) {
        System.out.println("____________________________________________________________\n" +
                s + "\n" +
                "____________________________________________________________");
    }

    public static void markStatus(Boolean mark, String line, Task[] list) {
        try {
            int taskNum = Integer.parseInt(line.split(" ", 0)[1]);
            Task curr = list[taskNum - 1];
            if (mark) {
                curr.setDone(true);
                printFormat(" Nice! I've marked this task as done:\n" +
                        "[" + curr.getStatusIcon() + "] " + curr.description);
            } else {
                curr.setDone(false);
                printFormat(" OK, I've marked this task as not done yet:\n" +
                        "[" + curr.getStatusIcon() + "] " + curr.description);
            }
        } catch (Exception e) {
            printFormat("Please mark / unmark a number that's in the list :')");
        }
    }
}
