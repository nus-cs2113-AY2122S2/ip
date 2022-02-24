public class Ui {

    public static void printHorizontalLine() {
        System.out.println(Message.HORIZONTAL_LINE);
    }

    public static void printTextBetweenLines(String text) {
        printHorizontalLine();
        System.out.print(text);
        printHorizontalLine();
    }

    public static void startDuke() {
        System.out.println(Message.GREET_MESSAGE);
    }

    public static void endDuke() {
        printHorizontalLine();
        System.out.println(Message.GOODBYE_MESSAGE);
        printHorizontalLine();
    }
}
