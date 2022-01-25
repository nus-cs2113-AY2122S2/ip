import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        TaskList taskList = new TaskList();

        System.out.println(Ui.drawBorder(Ui.greet()));
        userInput = sc.nextLine().trim();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(Ui.drawBorder(taskList.toString()));
            } else {
                System.out.println(Ui.drawBorder(taskList.addTask(userInput)));
            }
            userInput = sc.nextLine().trim();
        }

        System.out.println(Ui.drawBorder(Ui.exit()));
    }
}
