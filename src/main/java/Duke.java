public class Duke {
    public static void partitionPrint(String input) {
        String partition = "____________________________________________________________";
        System.out.println(input);
        System.out.println(partition);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello! I'm Duke\n" +
                "What can I do for you?";
        String partition = "____________________________________________________________";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(partition);
        System.out.println("Hello from\n" + logo);
        partitionPrint(greet);
        partitionPrint(goodbye);
    }
}
