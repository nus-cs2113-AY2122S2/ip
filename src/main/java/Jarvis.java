import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        DisplayMessages.startingMessage();
        Scanner in = new Scanner(System.in);
        while (true) {
            Formatter.inputHandler(in);
        }
    }
}
