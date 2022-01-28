import java.util.Scanner;

public class Duke {
    public static void greet() {
        String logo = "   ________                            \n"
                + "  / ____/ /_  ___  ___  ____ ___  _____\n"
                + " / /   / __ \\/ _ \\/ _ \\/ __ `__ \\/ ___/\n"
                + "/ /___/ / / /  __/  __/ / / / / (__  ) \n"
                + "\\____/_/ /_/\\___/\\___/_/ /_/ /_/____/  \n"
                + "                                       \n"
                + "-------------------------------------------\n";
        String greet = "Hemlo from\n" + logo + "Whamt cam cheems do for you?\n"
                + "-------------------------------------------";
        System.out.println(greet);
    }

    public static void farewell() {
        String farewell = "Goodbye. See you next time frem!\n" + "-------------------------------------------";
        System.out.println(farewell);
    }

    public static void echo() {
        String word;
        
        do {
            Scanner in = new Scanner(System.in);
            word = in.nextLine();
            System.out.println("-------------------------------------------");
            System.out.println(word);
            System.out.println("-------------------------------------------");

        } while (!word.equals("bye"));

        farewell();
    }

    public static void main(String[] args) {
        greet();
        echo();
    }

}
