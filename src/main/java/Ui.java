public class Ui {
    private static String drawBorder(String text) {
        return "____________________________________________________________\n"
                + text + "\n"
                + "____________________________________________________________\n";
    }

    public static void print(String text) {
        System.out.println(drawBorder((text)));
    }

    public static String addTaskMsg(Task task, int size) {
        return String.format("Got it. I've added this task:\n%s\n" +
                        "Now you have %d tasks in list.",
                        task, size);
    }

    public static String markTaskMsg(Task task, boolean command) {
        if (command) {
            return String.format("Nice! I've marked this task as done:\n%s", task);
        }
        return String.format("OK, I've marked this task as not done yet:\n%s", task);
    }
}
