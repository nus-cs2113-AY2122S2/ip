import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringParser sp = new StringParser();
        TaskList taskList = new TaskList();
        String userInput;

        System.out.println(Ui.drawBorder(Ui.greet()));
        userInput = sc.nextLine().trim();
        sp.parseString(userInput);

        while (!sp.isExiting()) {
            if (sp.isListingTasks()) {
                Ui.print(Ui.drawBorder(taskList.toString()));
            } else if (sp.isMarkingTask()) {
                Ui.print(Ui.drawBorder(
                        taskList.markTask(sp.getMarkedTask()[0],
                                Integer.valueOf(sp.getMarkedTask()[1]))
                ));
            } else if (sp.isAddingTask()) {
                Ui.print(Ui.drawBorder(taskList.addTask(sp.getAddedTask())));
            }
            userInput = sc.nextLine().trim();
            sp.parseString(userInput);
        }

        Ui.print(Ui.drawBorder(Ui.exit()));
    }
}
