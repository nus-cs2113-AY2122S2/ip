package Duke.Helper;

import Duke.Duke;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.util.List;

public class AddTask {

        public static void addTodo (List<Task> array, String message) throws DukeException {
            int status = 0;
            String[] splitMessage = message.split(" ", 2);
            String getDescription = splitMessage[1];
            if (getDescription.equals("")) {
                throw new DukeException();
            } else {
                Task taskItem = new Todo(getDescription, status);
                array.add(taskItem);
                System.out.println(Duke.DISPLAY_LINE + "Okay! I've added this task:");
                System.out.println(taskItem);
                System.out.print("Now you have " + array.size() + " tasks in the list.\n" + Duke.DISPLAY_LINE);
            }
        }

        public static String[] splitLongMessage (String message, String regex) {
            String[] splitMessage = message.split(" ", 2);
            String getSecondPart = splitMessage[1];
            return getSecondPart.split(regex, 2);
        }

        public static void addDeadline(List<Task> array, String message) throws DukeException {
            int status = 0;
            String[] splitSecondPart = splitLongMessage(message, " /by ");
            String getDescription = splitSecondPart[0];
            String getDate = splitSecondPart[1];
            if (getDate.equals("")) {
                throw new DukeException();
            } else {
                Task taskItem = new Deadline(getDescription, status, getDate);
                array.add(taskItem);
                System.out.println(Duke.DISPLAY_LINE + "Okay! I've added this task:");
                System.out.println(taskItem);
                System.out.print("Now you have " + array.size() + " tasks in the list.\n" + Duke.DISPLAY_LINE);
            }
        }

        public static void addEvent(List<Task> array, String message) throws DukeException {
            int status = 0;
            String[] splitSecondPart = splitLongMessage(message, " /at ");
            String getDescription = splitSecondPart[0];
            String getDate = splitSecondPart[1];
            if (getDate.equals("")) {
                throw new DukeException();
            } else {
                Task taskItem = new Event(getDescription, status, getDate);
                array.add(taskItem);
                System.out.println(Duke.DISPLAY_LINE + "Okay! I've added this task:");
                System.out.println(taskItem);
                System.out.print("Now you have " + array.size() + " tasks in the list.\n" + Duke.DISPLAY_LINE);
            }
        }

}
