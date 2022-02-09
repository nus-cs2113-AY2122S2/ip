import java.util.List;

public class MarkUnmarkItem {
    public static void markItem (List<Task> array, String message) {
        String[] splitMessage = message.split(" ");
        String getNumber = splitMessage[1];
        int positionToMark = Integer.parseInt(getNumber) - 1;
        array.get(positionToMark).markAsDone();
        System.out.println(Duke.DISPLAY_LINE + "Nice! I've marked this as done:");
        System.out.println("[" + array.get(positionToMark).getStatusIcon() + "] " + array.get(positionToMark).description);
        System.out.print(Duke.DISPLAY_LINE);
    }

    public static void unMarkItem(List<Task> array, String message) {
        String[] splitMessage = message.split(" ");
        String getNumber = splitMessage[1];
        int positionToUnMark = Integer.parseInt(getNumber) - 1;
        array.get(positionToUnMark).unMark();
        System.out.println(Duke.DISPLAY_LINE + "OK, I've marked this task as not done yet:");
        System.out.println("[" + array.get(positionToUnMark).getStatusIcon() + "] " + array.get(positionToUnMark).description);
        System.out.print(Duke.DISPLAY_LINE);
    }
}
