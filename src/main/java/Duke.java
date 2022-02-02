import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
         System.out.println("---------------------");
        System.out.println(("Hello! I'm Duke"));
        System.out.println(("What can i do for you?"));
        System.out.println("---------------------");
        Boolean run = true;
        while (run) {
            Scanner sc = new Scanner(System.in);
            String anyString = sc.nextLine();
            if (anyString.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                run = false;
            }


            else {
                System.out.println(anyString);
            }

        }        
    }
}
