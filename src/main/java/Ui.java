import duke.exception.IncompleteCommandException;
import duke.exception.MissingIndexException;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String GREET_STRING = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
    private static final String BYE_STRING = "Bye. Hope to see you again soon!";
    private static final String PROMPT_GENERIC_INVALID_COMMAND = "I don't understand what you are saying. Please follow this syntax:";
    private static final String PROMPT_ALL_FEATURES = "Recognised Commands:" + System.lineSeparator() +
            "'list'     | show you all your tasks" + System.lineSeparator() +
            "'mark'     | mark tasks by index once completed" + System.lineSeparator() +
            "'unmark'   | unmark tasks by index to undo 'mark'" + System.lineSeparator() +
            "'delete'   | delete tasks by index (irreversible!)" + System.lineSeparator() +
            "'todo'     | create general task" + System.lineSeparator() +
            "'deadline' | create task with deadline: include '/by' for deadline" + System.lineSeparator() +
            "'event'    | create task as event: include '/at' for the date/time" + System.lineSeparator() +
            "'find'     | find tasks that include a short string" + System.lineSeparator() +
            "'bye'      | exit Duke";
    private static final String PROMPT_CORRECT_DEADLINE = "example: `deadline Rush CS2113 Assignment /by today`";
    private static final String PROMPT_CORRECT_EVENT = "example: `event Watch CS2113 Lecture /at 4-6pm on Friday`";
    private static final String PROMPT_CORRECT_TODO = "example: `todo Make sure that the user knows I need some text here!`";
    private static final String PROMPT_CORRECT_FIND = "example: `find try`";
    private static final String PROMPT_CORRECT_INDEX_USE = "when marking, unmarking or deleting, use syntax like: `delete 2` for task #2" + System.lineSeparator() + "you can do so for any of the tasks in `list`";

    private static void promptToDo() {
        System.out.println(PROMPT_CORRECT_TODO);
    }

    private static void promptCommandList() {
        System.out.println(PROMPT_ALL_FEATURES);
    }

    private static void promptDeadline() {
        System.out.println(PROMPT_CORRECT_DEADLINE);
    }

    private static void promptEvent() {
        System.out.println(PROMPT_CORRECT_EVENT);
    }

    private static void promptFind(){
        System.out.println(PROMPT_CORRECT_FIND);
    }

    private static void promptAgain() {
        System.out.println(PROMPT_GENERIC_INVALID_COMMAND);
    }

    private static void promptIndexSyntax() {
        System.out.println(PROMPT_CORRECT_INDEX_USE);
    }

    /**
     * Greet user upon startup
     */
    static void greet() {
        System.out.println(GREET_STRING);
    }

    /**
     * Print exit string when user exits
     */
    static void bye() {
        System.out.println(BYE_STRING);
    }

    /**
     * Continually get input from the user till user terminates program.
     * @param scanner
     * @param parser
     * @param storage
     * @param taskList
     */
    static void askForInput(Scanner scanner, Parser parser, Storage storage, TaskList taskList) {
        while (true) {

            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                return;
            } else if (input.equals("list")) {
                taskList.displayTasks();
            } else if (input.startsWith("unmark")) {
                try {
                    int index = parser.extractTaskIndexNo(input);
                    taskList.setTaskStatus(index, false);
                } catch (MissingIndexException | IndexOutOfBoundsException e) {
                    promptIndexSyntax();
                }
            } else if (input.startsWith("mark")) {
                try {
                    int index = parser.extractTaskIndexNo(input);
                    taskList.setTaskStatus(index, true);
                } catch (MissingIndexException | IndexOutOfBoundsException e) {
                    promptIndexSyntax();
                }
            } else if (input.startsWith("delete")) {
                try {
                    int index = parser.extractTaskIndexNo(input);
                    taskList.deleteTask(index);
                } catch (MissingIndexException | IndexOutOfBoundsException e) {
                    promptIndexSyntax();
                }
            } else if (input.startsWith("todo")) {
                try {
                    String toDoTask = parser.extractToDoTask(input);
                    taskList.addToDo(toDoTask);
                } catch (IncompleteCommandException e) {
                    promptAgain();
                    promptToDo();
                }
            } else if (input.startsWith("find")){
                try {
                    String toDoTask = parser.extractFindTerm(input);
                    taskList.showResults(toDoTask);
                } catch (IncompleteCommandException e) {
                    promptAgain();
                    promptFind();
                }
            } else if (input.startsWith("deadline")) {
                try {
                    String[] deadlineTask = parser.extractDeadlineTask(input);
                    taskList.addDeadline(deadlineTask[0], deadlineTask[1]);
                } catch (IncompleteCommandException e) {
                    promptAgain();
                    promptDeadline();
                }
            } else if (input.startsWith("event")) {
                try {
                    String[] eventTask = parser.extractEventTask(input);
                    taskList.addEvent(eventTask[0], eventTask[1]);

                } catch (IncompleteCommandException e) {
                    promptAgain();
                    promptEvent();
                }
            } else {
                promptAgain();
                promptCommandList();
                continue;
            }
            storage.writeToFile(taskList.getTaskList());

        }
    }
}
