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
        while (num == 1) {
            line = in.nextLine();
            if (line.equals("bye")) {
                num = 0;
            } else {
                System.out.println("____________________________________________________________\n" +
                        line + "\n" +
                        "____________________________________________________________");
            }
        }

        System.out.println(bye);
    }
}
