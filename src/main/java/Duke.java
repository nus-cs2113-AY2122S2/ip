import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        startDuke();

        Scanner userInput = new Scanner(System.in);
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        Storage.storageSetup(tasks);
        line = userInput.nextLine();
        while (!"bye".equals(line)) {
            try {
                Parser parsedInput = new Parser(line);
                switch (parsedInput.getParsedInput()[0]) {
                case "todo":
                    line = line.replace("todo","").strip();
                    Todo newTodo = new Todo(line);
                    tasks.add(newTodo);
                    wrapAndPrintText("Added to new thing to do for ya!\n " + newTodo);
                    break;
                case "deadline":
                    line = line.replace("deadline", "");
                    String deadlineTask = line.substring(0, line.indexOf("/by")).strip();
                    String by = line.substring(line.indexOf("/by") + 4);
                    Deadline newDeadline = new Deadline(deadlineTask, by);
                    tasks.add(newDeadline);
                    wrapAndPrintText("Added to thing for ya, and ya gotta do it soon!\n " + newDeadline);
                    break;
                case "event":
                    String eventTask = line.substring(6, line.indexOf("/") - 1);
                    String at = line.substring(line.indexOf("/at") + 4);
                    Event newEvent = new Event(eventTask, at);
                    tasks.add(newEvent);
                    wrapAndPrintText("Added to thing for ya at some place and time!\n " + newEvent);
                    break;
                case "list":
                    String allTasks = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        allTasks = allTasks + " " + (i + 1) + ". " + tasks.get(i).toString();
                    }
                    wrapAndPrintText(allTasks);
                    break;
                case "mark":
                    int taskIndex = Integer.parseInt(parsedInput.getParsedInput()[1]) - 1;
                    tasks.get(taskIndex).setCompleted(true);
                    wrapAndPrintText("Marked this task as done!\n " + tasks.get(taskIndex).toString());
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(parsedInput.getParsedInput()[1]) - 1;
                    tasks.get(taskIndex).setCompleted(false);
                    wrapAndPrintText("Guess you messed up huh? Reverted that task!\n " + tasks.get(taskIndex).toString());
                    break;
                case "delete":
                    taskIndex = Integer.parseInt(parsedInput.getParsedInput()[1]) - 1;
                    wrapAndPrintText("Either you're done with that task or gave up. Anyways its gone!\n " + tasks.get(taskIndex).toString());
                    tasks.remove(taskIndex);
                    break;
                }
            } catch (InvalidCommandException e) {
                wrapAndPrintText("Whoopsies! I dont know what you're talking about! Try again!\n");
            } catch (MissingDescriptionException e) {
                wrapAndPrintText("I think you forgot some stuff there for that command! Try again!\n");
            }
            System.out.println();
            line = userInput.nextLine();
        }
        Storage.writeTasksToFile(tasks);
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

    /**
     * Prints out text wrapped between two lines for readability
     * @param text Text to be wrapped
     */
    public static void wrapAndPrintText(String text) {
        String wrappedText =      "___________________________________\n"
                                + text
                                + "___________________________________\n";

        System.out.println(wrappedText);
    }
}
