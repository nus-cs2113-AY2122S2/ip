public class Ui {
    private static String drawBorder(String text) {
        return "____________________________________________________________\n"
                + text + "\n"
                + "____________________________________________________________\n";
    }

    public static void print(String text) {
        System.out.println(drawBorder((text)));
    }
}
