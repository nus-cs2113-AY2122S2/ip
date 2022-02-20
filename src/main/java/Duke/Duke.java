package Duke;

public class Duke {
    public static void welcomeMessage() {
        String welcome= "____________________________________________________________\n" +
                " Hello! I'm Duke.Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(welcome);
    }

    public static void byeMessage() {
        String goodBye = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(goodBye);
    }

    public static void main(String[] args) throws DukeException {
        welcomeMessage();
        UserInterface.userInterface();
        byeMessage();
    }
}
