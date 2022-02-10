import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetStr = "\t" + "-".repeat(60) + "\n" +
                "\t Hello! I'm Maahes\n" +
                "\t What can I do for you?\n" +
                "\t" + "-".repeat(60);
        String byeStr = "\t" + "-".repeat(60) + "\n" +
                "\t Bye. Hope to see you again soon!\n" +
                "\t" + "-".repeat(60);

        System.out.println(greetStr);
        TaskManager taskManager = new TaskManager();
        taskManager.start();
        System.out.println(byeStr);
    }

}
