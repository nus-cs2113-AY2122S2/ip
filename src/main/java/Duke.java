import java.util.Scanner;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    private static final int MARK_NUMBER_INDEX = 5;
    private static final int NEWLINE_STRIP = 2;
    private static final int UNMARK_NUMBER_INDEX = 7;

    public static void main(String[] args) {
        botResponse("Hello! I'm Boba\n\tWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                // StringBuilder to reduce constructing a new string due to concatenation
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < taskCount; i++) {
                    sb.append(i + 1); // Post-increment to keep index up to date
                    sb.append(". ");
                    sb.append(taskList[i].toString());
                    sb.append("\n\t");
                }
                // substring used to ignore the last newline character
                botResponse(sb.substring(0, sb.length() - NEWLINE_STRIP));
            } else if (input.contains("unmark")) {
                // based on our current implementation unmark must appear before mark
                // similar reasoning to "mark <number>"
                int index = Integer.parseInt(input.substring(UNMARK_NUMBER_INDEX)) - 1;
                Task unmarkedTask = taskList[index];
                unmarkedTask.markAsNotDone();
                String unmarkedResponse = "Boop beep! I've marked this task as not done:\n\t\t";
                botResponse(unmarkedResponse + unmarkedTask.getStatusIcon() + unmarkedTask.description);
            } else if (input.contains("mark")) {
                // The number starts at index 5 assuming the command is "mark <number>"
                int index = Integer.parseInt(input.substring(MARK_NUMBER_INDEX)) - 1;
                Task markedTask = taskList[index];
                markedTask.markAsDone();
                String markedResponse = "Beep boop! I've marked this task as done:\n\t\t";
                botResponse(markedResponse + markedTask.getStatusIcon() + markedTask.description);
            } else if (taskCount == taskList.length) {
                // no more than 100 tasks
                botResponse("Sorry. No more tasks can be added.");
            } else if (input.contains("todo")) {
                String description = input.substring(input.indexOf(" ") + 1);
                Task newTask = new Todo(description);
                addTask(newTask);
            } else if (input.contains("deadline")) {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1);
                String deadline = input.substring(input.indexOf("/") + 4);
                Task newTask = new Deadline(description, deadline);
                addTask(newTask);
            } else if (input.contains("event")) {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/at") - 1);
                String event = input.substring(input.indexOf("/") + 4);
                Task newTask = new Event(description, event);
                addTask(newTask);
            }
            input = scan.nextLine();
        }
        botResponse("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task newTask) {
        taskList[taskCount] = newTask;
        taskCount++;
        System.out.println("............................................................");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + newTask.toString());
        System.out.printf("\tNow you have %d tasks in your list.%n", taskCount);
        System.out.println("............................................................");
    }

    private static void botResponse(String response) {
        System.out.println("............................................................");
        System.out.println("\t" + response);
        System.out.println("............................................................");
    }
}
