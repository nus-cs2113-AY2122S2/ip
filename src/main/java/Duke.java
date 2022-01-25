public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greet and say good-bye
        String line = "_____________________________________________\n";
        String greeting = "Hello! I'm Duke \nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(line + greeting);
        System.out.println(line + goodbye + line);
    }
}
