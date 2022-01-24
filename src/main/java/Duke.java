import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KaiKai.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            System.out.println(str);
            System.out.println("______________________________________");
            str = sc.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");
    }
}
