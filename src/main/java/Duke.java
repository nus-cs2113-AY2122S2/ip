public class Duke {
    public static void main(String[] args) {
        userGreet();

        userFarewell();
    }

    public static void userGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------------");
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");

    }

    public static void userFarewell() {
        System.out.println("Bye. Hope to see you soon!");
    }
}
