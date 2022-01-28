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

    public static void addList() {
        String word;
        String[] tasks = new String[100];
        int index = 0;

        do {
            Scanner in = new Scanner(System.in);
            word = in.nextLine();
            if (word.equals("list")) {
                System.out.println("-------------------------------------------");
                System.out.println("Hemre is your list: ");
                for (int i = 0; i < index; i++) {
                    System.out.println("  - " + tasks[i]);
                }
                System.out.println("-------------------------------------------");
            } else if (word.equals("bye")){
                System.out.println("-------------------------------------------");
                break;
            } else {
                System.out.println("-------------------------------------------");
                System.out.println("I hamve added: " + word);
                System.out.println("-------------------------------------------");
                tasks[index] = word;
                index++;
            }

        } while (!word.equals("bye"));

        farewell();
    }

    public static void main(String[] args) {
        greet();
        addList();
    }

}
