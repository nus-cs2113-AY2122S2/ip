public class Duke {
    public static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String fillerLine = "____________________________________________________________";

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(fillerLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(fillerLine);

    }
    public static void main(String[] args){
        greetings();
    }
}
