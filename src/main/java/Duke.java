public class Duke {
    public static void greeting() {
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");

    }

    public static void linePrinter() {
        System.out.println("______________________________________________");
    }

    public static void exitLine() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        linePrinter();
        greeting();
        linePrinter();
        exitLine();
        linePrinter();

    }
}
