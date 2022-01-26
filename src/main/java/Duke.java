import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final int MARK_NUMBER_INDEX = 5;
    private static final int NEWLINE_STRIP = 2;
    private static final int TASK_CAPACITY = 100;
    private static final int UNMARK_NUMBER_INDEX = 7;

    public static void main(String[] args) {
        botResponse("Hello! I'm Boba\n\tWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int index = 1;
                // StringBuilder to reduce constructing a new string due to concatenation
                StringBuilder sb = new StringBuilder();
                for (Task item : taskList) {
                    sb.append(index++); // Post-increment to keep index up to date
                    sb.append(". ");
                    sb.append(item.getStatusIcon());
                    sb.append(item.description);
                    sb.append("\n\t");
                }
                // substring used to ignore the last newline character
                botResponse(sb.substring(0, sb.length() - NEWLINE_STRIP));
            } else if (input.contains("unmark")) {
                // based on our current implementation unmark must appear before mark
                // similar reasoning to "mark <number>"
                int index = Integer.parseInt(input.substring(UNMARK_NUMBER_INDEX)) - 1;
                Task unmarkedTask = taskList.get(index);
                unmarkedTask.markAsNotDone();
                String unmarkedResponse = "Boop beep! I've marked this task as not done:\n\t\t";
                botResponse(unmarkedResponse + unmarkedTask.getStatusIcon() + unmarkedTask.description);
            } else if (input.contains("mark")) {
                // The number starts at index 5 assuming the command is "mark <number>"
                int index = Integer.parseInt(input.substring(MARK_NUMBER_INDEX)) - 1;
                Task markedTask = taskList.get(index);
                markedTask.markAsDone();
                String markedResponse = "Beep boop! I've marked this task as done:\n\t\t";
                botResponse(markedResponse + markedTask.getStatusIcon() + markedTask.description);
            } else if (taskList.size() == TASK_CAPACITY) {
                // no more than 100 tasks
                botResponse("Sorry. No more tasks can be added.");
            } else {
                botResponse("added: " + input);
                Task newTask = new Task(input);
                taskList.add(newTask);
            }
            input = scan.nextLine();
        }
        botResponse("Bye. Hope to see you again soon!");
    }

    private static void botResponse(String response) {
        System.out.println("............................................................");
        System.out.println("\t" + response);
        System.out.println("............................................................");
    }
}
