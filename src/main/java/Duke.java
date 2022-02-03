import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings =
              "______________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "______________________________________________________________";
        System.out.println(greetings);
        String echo = greetings;

        Scanner sc = new Scanner(System.in);

        while(true) {
            echo = sc.next();
            System.out.println("______________________________________________________________");
            if(echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________________________\n");
                break;
            } else {
                System.out.println(echo);
            }
            System.out.println("______________________________________________________________\n");
        }




        
    }
}