public class Duke {
    private static void printGreetings() {
        String logo = "     _______.  ______   .______          ___      \n"
                + "    /       | /  __  \\  |   _  \\        /   \\     \n"
                + "   |   (----`|  |  |  | |  |_)  |      /  ^  \\    \n"
                + "    \\   \\    |  |  |  | |      /      /  /_\\  \\   \n"
                + ".----)   |   |  `--'  | |  |\\  \\----./  _____  \\  \n"
                + "|_______/     \\______/  | _| `._____/__/     \\__\\ \n";

        String line = "____________________________________________________________";

        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Sora");
        System.out.println("What can I do for you?");
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void main(String[] args) {
        printGreetings();


    }
}
