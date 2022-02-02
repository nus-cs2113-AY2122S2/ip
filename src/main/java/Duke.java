import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final int SECTION_LIMIT = 2;

    private static ArrayList<String> responses = new ArrayList<>();
    private static int taskCount = 0;
    private static Task[] taskList = new Task[100];

    public static void main(String[] args) {
        responses.add("Hello! I'm Boba.");
        responses.add("I am a bot 'tasked' to manage your tasks");
        responses.add("What can I do for you?");
        responses.add("Type 'help' to get the list commands I response to");
        botResponse();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Command operation = getCommand(input);
        String[] sections = parseInput(operation, input);
        while (operation != Command.EXIT) {
            if (checkNull(sections)) {
                printInvalidCommand(operation);
            } else {
                switch (operation) {
                case LIST:
                    printTasks();
                    break;
                case MARK:
                    markTask(true, sections[0], operation);
                    break;
                case UNMARK:
                    markTask(false, sections[0], operation);
                    break;
                case TODO:
                    addTask(new Todo(sections[0]));
                    break;
                case DEADLINE:
                    addTask(new Deadline(sections[0], sections[1]));
                    break;
                case EVENT:
                    addTask(new Event(sections[0], sections[1]));
                    break;
                case HELP:
                    printHelp();
                    break;
                case NONE:
                    printInvalidCommand(operation);
                }
            }
            input = scan.nextLine();
            operation = getCommand(input);
            sections = parseInput(operation, input);
        }
        responses.add("Bye. Hope to see you again soon!");
        botResponse();
    }

    private static Command getCommand(String input) {
        // ternary operation for one word commands
        int index = input.indexOf(" ");
        String command = index == -1 ? input : input.substring(0, index) ;
        switch (command) {
        case ("bye"):
            return Command.EXIT;
        case ("list"):
            return Command.LIST;
        case ("todo"):
            return Command.TODO;
        case ("deadline"):
            return Command.DEADLINE;
        case ("event"):
            return Command.EVENT;
        case ("mark"):
            return Command.MARK;
        case ("unmark"):
            return Command.UNMARK;
        case ("help"):
            return Command.HELP;
        default:
            return Command.NONE;
        }
    }

    private static String[] parseInput(Command operation, String input) {
        String[] sections = new String[SECTION_LIMIT];
        switch (operation) {
        case DEADLINE:
        case EVENT:
            int slashIndex = input.indexOf("/");
            if (slashIndex == -1) {
                break;
            }
            sections[1] = input.substring(input.indexOf(" ", slashIndex) + 1);
            input = input.substring(0, slashIndex - 1);
        case TODO:
        case MARK:
        case UNMARK:
            int spaceIndex = input.indexOf(" ");
            if (spaceIndex == -1) {
                break;
            }
            sections[0] = input.substring(spaceIndex + 1);
        case LIST:
        case EXIT:
        case HELP:
            sections[0] = sections[0] == null ? "valid" : sections[0];
            sections[1] = sections[1] == null ? "valid" : sections[1];
        }
        return sections;
    }

    private static boolean checkNull(String[] sections) {
        for (String section : sections) {
            if (section == null) {
                return true;
            }
        }
        return false;
    }

    private static void printInvalidCommand(Command operation) {
        responses.add("Invalid Command. Please try again.");
        switch (operation) {
        case TODO:
            responses.add("Description is required");
            break;
        case DEADLINE:
            responses.add("Remember to include the /by command!");
            break;
        case EVENT:
            responses.add("Remember to include the /at command!");
            break;
        case MARK:
        case UNMARK:
            responses.add("Make sure to include a valid number");
        }
        botResponse();
    }

    private static void printTasks() {
        if (taskCount == 0){
            responses.add("The list empty!");
        }
        for (int i = 0; i < taskCount; i++) {
            responses.add(i + 1 + ". " + taskList[i]);
        }
        botResponse();
    }

    private static void markTask(boolean isDone, String taskIndex, Command operation) {
        int index = Integer.parseInt(taskIndex) - 1;
        if (index >= 0 && index < taskCount) {
            Task selectedTask = taskList[index];
            if (isDone) {
                selectedTask.markAsDone();
                responses.add("Beep boop! I've marked this task as done:");
            } else {
                selectedTask.markAsNotDone();
                responses.add("Boop beep! I've marked this task as not done:");
            }
            responses.add(selectedTask.toString());
            botResponse();
        } else {
            printInvalidCommand(operation);
        }
    }

    private static void addTask(Task newTask) {
        taskList[taskCount] = newTask;
        taskCount++;
        responses.add("Got it. I've added this task:");
        responses.add("\t" + newTask.toString());
        responses.add("Now you have " + taskCount +" tasks in your list.");
        botResponse();
    }

    private static void printHelp() {
        responses.add("Here are all the possible commands:");
        responses.add("\t1. bye");
        responses.add("\t2. list");
        responses.add("\t3. todo <description>");
        responses.add("\t4. deadline <description> /by <time>");
        responses.add("\t5. event <description> /at <time>");
        responses.add("\t6. mark <number>");
        responses.add("\t7. unmark <number>");
        responses.add("\t8. help");
        botResponse();
    }

    private static void botResponse() {
        System.out.println("............................................................");
        for (String response : responses) {
            System.out.println("\t" + response);
        }
        System.out.println("............................................................");
        responses.clear();
    }
}
