import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        startDuke();

        Scanner userInput = new Scanner(System.in);
        String line;
        ArrayList<Task> tasks = new ArrayList<Task>();

        line = userInput.nextLine();
        while (!"bye".equals(line)) {
            Parse parsedInput = new Parse(line);
            if (parsedInput.getIsKeyword()) {
                switch (parsedInput.getParsedInput()[0]) {
                    case "list":
                        String allTasks = "";
                        for (int i = 0; i < tasks.size(); i++) {
                            allTasks = allTasks + " " + (i + 1) + ". " + tasks.get(i).toString();
                        }
                        System.out.println(wrapText(allTasks));
                        break;
                    case "mark":
                        tasks.get(Integer.parseInt(parsedInput.getParsedInput()[1]) - 1).completeTask();
                        System.out.println(wrapText(
                                "Marked this task as done!\n"
                                + tasks.get(Integer.parseInt(parsedInput.getParsedInput()[1]) - 1).toString()
                        ));
                        break;
                    case "unmark":
                        tasks.get(Integer.parseInt(parsedInput.getParsedInput()[1]) - 1).revertCompletedTask();
                        System.out.println(wrapText(
                                "Guess you messed up huh? Reverted that task!\n"
                                + tasks.get(Integer.parseInt(parsedInput.getParsedInput()[1]) - 1).toString()
                        ));
                        break;
                }
            } else {
                Task newTask = new Task(line);
                tasks.add(newTask);
                System.out.println(wrapText("Added to list: " + line));
            }
            System.out.println();
            line = userInput.nextLine();
        }
        endDuke();
    }

    public static void startDuke() {
        String startDuke =
                          " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n"
                        + "___________________________________\n"
                        + "Hello! Duke here!\n"
                        + "How can I help?\n"
                        + "___________________________________\n";

        System.out.println(startDuke);
    }

    public static void endDuke() {
        String endDuke =
                          "___________________________________\n"
                        + "Goodbye. See you next time!\n"
                        + "___________________________________\n";

        System.out.println(endDuke);
    }

    public static String wrapText(String text) {
        return    "___________________________________\n"
                + text + "\n"
                + "___________________________________\n";
    }
}
