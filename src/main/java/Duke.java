import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";

        System.out.println(Ui.drawBorder(Ui.greet()));
        userInput = sc.nextLine().trim();

        while (!userInput.equals("bye")) {
            System.out.println(Ui.drawBorder(userInput));
            userInput = sc.nextLine().trim();
        }

        System.out.println(Ui.drawBorder(Ui.exit()));
    }
}
