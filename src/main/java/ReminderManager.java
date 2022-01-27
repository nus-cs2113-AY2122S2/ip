import java.util.ArrayList;
import java.util.List;

public class ReminderManager {
    /** list to store all reminders */
    private static List<Reminder> reminders = new ArrayList<Reminder>();

    /**
     * Add all the reminders in args
     *
     * @param args reminders to be added
     */
    public static void add(String[] args) {
        if(!args[0].equals(args[0])) {
            // there must be some error
            // TODO exception handle
            return;
        }
        for(int i = 1; i < args.length; i++) {
            reminders.add(new Reminder(args[i]));
            System.out.println("Added: " + args[i]);
        }
    }

    /**
     * Return the corresponding reminder index by its array index.
     * By conventional reminder index == array index + 1
     *
     * @param arrayID
     * @return
     */
    private static int getReminderID(int arrayID) {
        return arrayID+1;
    }

    /**
     * Return the corresponding reminder index by its array index.
     * By conventional array index == reminder index - 1
     *
     * @param reminderID
     * @return
     */
    private static int getArrayID(int reminderID) {
        return reminderID-1;
    }

    /**
     * List all the reminders
     *
     * @param args dump variable, no use
     */
    public static void list(String[] args) {
        // TODO args can be used to control the style of output
        if(!args[0].equals(args[0])) {
            // there must be some error
            // TODO exception handle
            return;
        }
        for(int i = 0; i < reminders.size(); i++) {
            System.out.printf("%d. %s\n", getReminderID(i), reminders.get(i));
        }
    }

    /**
     * Mark every reminder whose is in args[1..n) as done
     * @param args the name of function and those reminder ID to be marked as done
     */
    public static void mark(String[] args) {
        if(!args[0].equals(args[0])) {
            // there must be some error
            // TODO exception handle
            return;
        }
        System.out.println("Nice! I've marked these tasks as done\n");
        for(int i = 1; i < args.length; i++) {
            int reminderID = Integer.parseInt(args[i]);
            int arrayID = getArrayID(reminderID);
            reminders.set(arrayID, reminders.get(arrayID).setDone());
            System.out.println(reminders.get(arrayID));
        }
    }


    /**
     * Mark every reminder whose is in args[1..n) as not done yet
     * @param args the name of function and those reminder ID to be marked as not done yet
     */
    public static void unmark(String[] args) {
        if(!args[0].equals(args[0])) {
            // there must be some error
            // TODO exception handle
            return;
        }
        System.out.println("Nice! I've marked these tasks as not done yet\n");
        for(int i = 1; i < args.length; i++) {
            int reminderID = Integer.parseInt(args[i]);
            int arrayID = getArrayID(reminderID);
            reminders.set(arrayID, reminders.get(arrayID).clearDone());
            System.out.println(reminders.get(arrayID));
        }
    }
}
