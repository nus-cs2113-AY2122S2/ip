public class Ui {
    public static String drawBorder(String text) {
        return "____________________________________________________________\n"
                + text + "\n"
                + "____________________________________________________________\n";
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static String greet() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
