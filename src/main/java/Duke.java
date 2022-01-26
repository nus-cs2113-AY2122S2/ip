public class Duke {

    public static void printLine(){
        System.out.println("-----------------------------------------");
    }

    public static void greeting(){
        System.out.println("Hi! This is Duke!");
        System.out.println("I'm glad to be at your service.");
        System.out.println("What can I help you with?");
        printLine();
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();
        greeting();

        bye();

    }
}
