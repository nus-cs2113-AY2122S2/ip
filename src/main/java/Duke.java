public class Duke {

    //method prints a horizontal line
    public static void dispLine(){
        System.out.println("____________________________________________________________");
    }

    //method prints Duke greeting
    public static void greeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        dispLine();
    }

    //method prints Duke termination
    public static void goodbye(){
        System.out.println("Bye. Hope to see you again soon!");
        dispLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        dispLine();

        greeting();
        goodbye();

    }
}
