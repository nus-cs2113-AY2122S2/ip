package Duke.Helper;

import Duke.Duke;
import Duke.Tasks.Task;

import java.util.ArrayList;

public class DeleteTask {
    public static void deleteTask(ArrayList<Task> listArray, String message) throws DukeException {
        String[] splitMessage = message.split(" ");
        String getNumber = splitMessage[1];
        int positionToDelete = Integer.parseInt(getNumber) - 1;
        if (positionToDelete + 1 == 0 | positionToDelete + 1 > listArray.size()) {
            throw new DukeException(DisplayMessageCommand.displayDeleteMessage(listArray));
        }
        if (listArray.isEmpty()) {
            System.out.print(Duke.DISPLAY_LINE + System.lineSeparator() + "Please add something to the list first:)"
                    + System.lineSeparator() + Duke.DISPLAY_LINE);
        } else {
            System.out.println(Duke.DISPLAY_LINE + System.lineSeparator()
                    + "Okay! I have removed this task from the list!");
            System.out.println(listArray.get(positionToDelete));
            listArray.remove(positionToDelete);
            System.out.println("You have " + listArray.size() + " items left in the list:)");
            System.out.println(Duke.DISPLAY_LINE);
        }
    }
}
