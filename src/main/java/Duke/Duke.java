package Duke;

import Duke.Helper.DisplayMessages;
import Duke.Helper.DukeException;
import Duke.Helper.AddTask;
import Duke.Tasks.MarkUnmarkItem;
import Duke.Tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> listArray = new ArrayList<>();
    public static int itemNumber;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String DISPLAY_LINE= "________________________________________________\n";

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listItems (List<Task> array, int itemNumber) throws DukeException {
        if (itemNumber == 0) {
            throw new DukeException();
        } else {
            System.out.println(DISPLAY_LINE + "Here are the tasks in your list: ");
            for (int i = 0; i < itemNumber; i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(array.get(i));
            }
            System.out.print(DISPLAY_LINE);
        }
    }


    public static void main(String[] args) {
        itemNumber = 0;
        DisplayMessages.displayGreetMessage();
        Scanner in = new Scanner(System.in);
        boolean isLoop = true;
        while (isLoop) {
            String message = in.nextLine();
            String messageLowerCase = message.toLowerCase();
            if (messageLowerCase.equals("bye")) {
                exit();
                isLoop = false;
            } else if (messageLowerCase.equals("list")) {
                try {
                    listItems(listArray, itemNumber);
                } catch (DukeException e) {
                    DisplayMessages.displayListMessage();
                }
            } else if (messageLowerCase.contains("unmark")) {
                try {
                    MarkUnmarkItem.unMarkItem(listArray, message);
                } catch (IndexOutOfBoundsException e) {
                    DisplayMessages.displayUnmarkMessage(itemNumber);
                }
            } else if (messageLowerCase.contains("mark")) {
                try {
                    MarkUnmarkItem.markItem(listArray, message);
                } catch (IndexOutOfBoundsException e) {
                    DisplayMessages.displayMarkMessage(itemNumber);
                }
            } else if (messageLowerCase.contains("todo")) {
                try {
                    AddTask.addTodo(listArray, message, itemNumber);
                    itemNumber++;
                } catch (IndexOutOfBoundsException e) {
                    DisplayMessages.displayTodoMessage();
                } catch (DukeException e) {
                    DisplayMessages.displayTodoMessage();
                }
            } else if (messageLowerCase.contains("deadline")) {
                try {
                    AddTask.addDeadline(listArray, message, itemNumber);
                    itemNumber++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    DisplayMessages.displayDeadlineMessage();
                } catch (DukeException e) {
                    DisplayMessages.displayDeadlineMessage();
                }
            } else if (messageLowerCase.contains("event")) {
                try {
                    AddTask.addEvent(listArray, message, itemNumber);
                    itemNumber++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    DisplayMessages.displayEventMessage();
                } catch (DukeException e) {
                    DisplayMessages.displayEventMessage();
                }
            } else {
                DisplayMessages.displayInvalidInputMessage();
            }
        }
    }
}
