import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
         String greet = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
         System.out.println(greet);

        String bye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        int num = 1;
        String line;
        Scanner in = new Scanner(System.in);

        String[] list = new String[100];
        int i = 0;

        while (num == 1) {
            line = in.nextLine();
            if (line.equals("bye")) {
                num = 0;
            } else {
                if (line.equals("list")) {
                    System.out.println("____________________________________________________________");
                    for (int k = 0; k < i; k++) {
                        System.out.println(Integer.toString(k+1) + ". " + list[k]);
                    }
                    System.out.println("____________________________________________________________\n");
                } else {
                    list[i] = line;
                    i++;
                    printFormat("added: " + line);
                }
            }
        }

        System.out.println(bye);
    }

    public static void printFormat(String s) {
        System.out.println("____________________________________________________________\n" +
                s + "\n" +
                "____________________________________________________________");
    }
}
