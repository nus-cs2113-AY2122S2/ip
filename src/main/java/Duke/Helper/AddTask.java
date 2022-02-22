package Duke.Helper;

import Duke.Duke;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.util.ArrayList;

public class AddTask {

        public static void addTodo (ArrayList<Task> listArray, String message) throws DukeException {
            boolean isDone = false;
            String[] splitMessage = message.split(" ", 2);
            if (splitMessage.length != 2) {
                throw new DukeException(DisplayMessageCommand.displayTodoMessage());
            }
            String getDescription = splitMessage[1];
            if (getDescription.equals("")) {
                throw new DukeException(DisplayMessageCommand.displayTodoMessage());
            } else {
                Task taskItem = new Todo(getDescription, isDone);
                listArray.add(taskItem);
                System.out.println(Duke.DISPLAY_LINE + System.lineSeparator() + "Okay! I've added this task:");
                System.out.println(taskItem);
                System.out.println("Now you have " + listArray.size() + " tasks in the list.\n" + Duke.DISPLAY_LINE);
            }
        }

        public static String[] splitLongMessage (String message, String regex, String type) throws DukeException {
            String[] splitMessage = message.split(" ", 2);
            if (splitMessage.length != 2) {
                if (type.equals("deadline")) {
                    throw new DukeException(DisplayMessageCommand.displayDeadlineMessage());
                } else {
                    throw new DukeException(DisplayMessageCommand.displayEventMessage());
                }
            }
            String getSecondPart = splitMessage[1];
            return getSecondPart.split(regex, 2);
        }

        public static void addDeadline(ArrayList<Task> listArray, String message) throws DukeException {
            boolean isDone = false;
            String[] splitSecondPart = splitLongMessage(message, " /by ", "deadline");
            if (splitSecondPart.length != 2) {
                throw new DukeException(DisplayMessageCommand.displayDeadlineMessage());
            }
            String getDescription = splitSecondPart[0];
            String getDate = splitSecondPart[1];
            if (getDescription.equals("") | getDate.equals("")) {
                throw new DukeException(DisplayMessageCommand.displayDeadlineMessage());
            } else {
                Task taskItem = new Deadline(getDescription, isDone, getDate);
                listArray.add(taskItem);
                System.out.println(Duke.DISPLAY_LINE  + System.lineSeparator() + "Okay! I've added this task:");
                System.out.println(taskItem);
                System.out.println("Now you have " + listArray.size() + " tasks in the list.\n" + Duke.DISPLAY_LINE);
            }
        }

        public static void addEvent(ArrayList<Task> listArray, String message) throws DukeException {
            boolean isDone = false;
            String[] splitSecondPart = splitLongMessage(message, " /at ","event");
            if (splitSecondPart.length != 2) {
                throw new DukeException(DisplayMessageCommand.displayEventMessage());
            }
            String getDescription = splitSecondPart[0];
            String getDate = splitSecondPart[1];
            if (getDescription.equals("") | getDate.equals("")) {
                throw new DukeException(DisplayMessageCommand.displayEventMessage());
            } else {
                Task taskItem = new Event(getDescription, isDone, getDate);
                listArray.add(taskItem);
                System.out.println(Duke.DISPLAY_LINE + System.lineSeparator() + "Okay! I've added this task:");
                System.out.println(taskItem);
                System.out.println("Now you have " + listArray.size() + " tasks in the list.\n" + Duke.DISPLAY_LINE);
            }
        }

}
