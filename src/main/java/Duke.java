import java.util.Scanner;

public class Duke {

    private static String GREET_STRING = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
    private static String BYE_STRING = "Bye. Hope to see you again soon!";
    private static String PROMPT_GENERIC = "I don't understand what you are saying. Perhaps try again?";

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        InputReader inputReader = new InputReader();
        TaskList taskList = new TaskList();

        greet();

        String input = s.nextLine();

        while (true) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                taskList.displayTasks();
            } else if (input.startsWith("unmark")) {
                int index = inputReader.extractTaskIndexNo(input);
                taskList.setTaskStatus(index, false);
            } else if (input.startsWith("mark")) {
                int index = inputReader.extractTaskIndexNo(input);
                taskList.setTaskStatus(index, true);
            } else if (input.startsWith("todo")) {
                String toDoTask = inputReader.extractToDoTask(input);
                taskList.addToDo(toDoTask);
            } else if (input.startsWith("deadline")) {
                String[] deadlineTask = inputReader.extractDeadlineTask(input);
                taskList.addDeadline(deadlineTask[0], deadlineTask[1]);
            } else if (input.startsWith("event")) {
                String[] eventTask = inputReader.extractEventTask(input);
                taskList.addEvent(eventTask[0], eventTask[1]);
            } else {
                promptAgain();
            }
            input = s.nextLine();
        }
        bye();

    }

    private static void promptAgain() {
        System.out.println(PROMPT_GENERIC);
    }

    private static void greet() {
        System.out.println(GREET_STRING);
    }

    private static void bye() {
        System.out.println(BYE_STRING);
    }


}
