import duke.exception.IncompleteCommandException;
import duke.exception.MissingIndexException;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

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
            "'bye'      | exit Duke";
    private static final String PROMPT_CORRECT_DEADLINE = "example: `deadline Rush CS2113 Assignment /by today`";
    private static final String PROMPT_CORRECT_EVENT = "example: `event Watch CS2113 Lecture /at 4-6pm on Friday`";
    private static final String PROMPT_CORRECT_TODO = "example: `todo Make sure that the user knows I need some text here!`";
    private static final String PROMPT_CORRECT_INDEX_USE = "when marking, unmarking or deleting, use syntax like: `delete 2` for task #2" + System.lineSeparator() +
            "you can do so for any of the tasks in `list`";

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        InputReader inputReader = new InputReader();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();

        TaskList taskList = accessTaskHistory(inputReader, fileReaderWriter);

        greet();

        while (true) {

            String input = s.nextLine().trim();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                taskList.displayTasks();
            } else if (input.startsWith("unmark")) {
                try {
                    int index = inputReader.extractTaskIndexNo(input);
                    taskList.setTaskStatus(index, false);
                } catch (MissingIndexException | IndexOutOfBoundsException e){
                    promptIndexSyntax();
                }
            } else if (input.startsWith("mark")) {
                try {
                    int index = inputReader.extractTaskIndexNo(input);
                    taskList.setTaskStatus(index, true);
                } catch (MissingIndexException | IndexOutOfBoundsException e){
                    promptIndexSyntax();
                }
            } else if (input.startsWith("delete")) {
                try {
                    int index = inputReader.extractTaskIndexNo(input);
                    taskList.deleteTask(index);
                } catch (MissingIndexException | IndexOutOfBoundsException e){
                    promptIndexSyntax();
                }
            } else if (input.startsWith("todo")) {
                try {
                    String toDoTask = inputReader.extractToDoTask(input);
                    taskList.addToDo(toDoTask);
                } catch (IncompleteCommandException e) {
                    promptAgain();
                    promptToDo();
                }
            } else if (input.startsWith("deadline")) {
                try {
                    String[] deadlineTask = inputReader.extractDeadlineTask(input);
                    taskList.addDeadline(deadlineTask[0], deadlineTask[1]);
                } catch (IncompleteCommandException e) {
                    promptAgain();
                    promptDeadline();
                }
            } else if (input.startsWith("event")) {
                try {
                    String[] eventTask = inputReader.extractEventTask(input);
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
            fileReaderWriter.writeToFile(taskList.getTaskList());

        }
        bye();

    }

    private static TaskList accessTaskHistory(InputReader inputReader, FileReaderWriter fileReaderWriter) {
        TaskList taskList = new TaskList();
        ArrayList<String> oldTaskStrings = fileReaderWriter.readFromFile();
        for (String taskString: oldTaskStrings){
            ArrayList<String> splitString = inputReader.parseSavedString(taskString);
            if (splitString.size()==0){
                continue;
            }
            switch (splitString.get(0)){
            case "T":
                taskList.addToDo(splitString.get(2),Boolean.parseBoolean(splitString.get(1)));
                break;
            case "D":
                taskList.addDeadline(splitString.get(2), Boolean.parseBoolean(splitString.get(1)), splitString.get(3));
                break;
            case "E":
                taskList.addEvent(splitString.get(2), Boolean.parseBoolean(splitString.get(1)), splitString.get(3));
                break;
            default:
                System.out.println("not an acceptable task string!"+System.lineSeparator()+taskString);
            }
        }
        return taskList;
    }

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

    private static void promptAgain() {
        System.out.println(PROMPT_GENERIC_INVALID_COMMAND);
    }

    private static void promptIndexSyntax(){
        System.out.println(PROMPT_CORRECT_INDEX_USE);
    }

    private static void greet() {
        System.out.println(GREET_STRING);
    }

    private static void bye() {
        System.out.println(BYE_STRING);
    }


}
