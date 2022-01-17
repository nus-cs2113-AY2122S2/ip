public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greet = " Hello! I'm Duke\n" +
                " What can I do for you?";

        String bye = " Bye. Hope to see you again soon!";

        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello from\n" + logo);
        System.out.println(greet + "\n" + line + "\n" + bye + "\n" + line);
    }
}
