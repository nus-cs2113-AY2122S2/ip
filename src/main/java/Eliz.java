import java.util.Scanner;

public class Eliz {
    public static void main(String[] args) {
        String logo = " ____    __       __     ______ \n"
                    + "|  __|  |  |     |  |   |___  /\n"
                    + "| |__   |  |     |  |      / /  \n"
                    + "| |__|  |  |     |  |     / /  \n"
                    + "| |__   |  |__   |  |    / /__\n"
                    + "|____|  |_____|  |__|   |_____|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eliz");
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String bye = "bye";
        while (!line.equals(bye)) { //while command to end is not entered
            System.out.println(line);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
