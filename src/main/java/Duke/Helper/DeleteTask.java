package Duke.Helper;

import Duke.Duke;
import Duke.Tasks.Task;

import java.util.ArrayList;

public class DeleteTask {
    public static void deleteTask(ArrayList<Task> array, String message) {
        String[] splitMessage = message.split(" ");
        String getNumber = splitMessage[1];
        int positionToDelete = Integer.parseInt(getNumber) - 1;
        if (array.size() == 0) {
            System.out.print(Duke.DISPLAY_LINE + "Please add something to the list first:)" + System.lineSeparator()
                    + Duke.DISPLAY_LINE);
        } else {
            System.out.println(Duke.DISPLAY_LINE + "Okay! I have removed this task from the list!");
            System.out.println(array.get(positionToDelete));
            array.remove(positionToDelete);
            System.out.println("You have " + array.size() + " items left in the list:)");
            System.out.print(Duke.DISPLAY_LINE);
        }
    }
}
