package Duke.Helper;

import Duke.Duke;
import Duke.Tasks.Task;

import java.util.ArrayList;

public class MarkUnmarkItem {

    public static void markItem (ArrayList<Task> listArray, String message) throws DukeException {
        if (listArray.isEmpty()) {
            System.out.print(Duke.DISPLAY_LINE + System.lineSeparator() + "Please add something to the list first:)"
                    + System.lineSeparator() + Duke.DISPLAY_LINE);
        } else {
            String[] splitMessage = message.split(" ");
            if (splitMessage.length != 2) {
                throw new DukeException(Duke.DISPLAY_LINE + System.lineSeparator() + "Please input a number\n" +
                        "For eg. 'mark 2'\n" + Duke.DISPLAY_LINE);
            }
            String getNumber = splitMessage[1];
            int positionToMark = Integer.parseInt(getNumber) - 1;
            if (positionToMark + 1 == 0 | positionToMark + 1 > listArray.size()) {
                throw new DukeException(DisplayMessageCommand.displayMarkMessage(listArray));
            }
            listArray.get(positionToMark).markAsDone();
            System.out.println(Duke.DISPLAY_LINE + System.lineSeparator() + "Nice! I've marked this as done:");
            System.out.println(listArray.get(positionToMark));
            System.out.println(Duke.DISPLAY_LINE);
        }
    }

    public static void unMarkItem(ArrayList<Task> listArray, String message) throws DukeException {
        if (listArray.isEmpty()) {
            System.out.print(Duke.DISPLAY_LINE + System.lineSeparator() + "Please add something to the list first:)"
                    + System.lineSeparator() + Duke.DISPLAY_LINE);
        } else {
            String[] splitMessage = message.split(" ");
            if (splitMessage.length != 2) {
                throw new DukeException(Duke.DISPLAY_LINE + System.lineSeparator() + "Please input a number\n" +
                        "For eg. 'unmark 2'\n" + Duke.DISPLAY_LINE);
            }
            String getNumber = splitMessage[1];
            int positionToUnMark = Integer.parseInt(getNumber) - 1;
            if (positionToUnMark + 1 == 0 | positionToUnMark + 1 > listArray.size()) {
                throw new DukeException(DisplayMessageCommand.displayUnmarkMessage(listArray));
            }
            listArray.get(positionToUnMark).unMark();
            System.out.println(Duke.DISPLAY_LINE + System.lineSeparator() + "OK, I've marked this task as not done yet:");
            System.out.println(listArray.get(positionToUnMark));
            System.out.println(Duke.DISPLAY_LINE);
        }
    }
}
