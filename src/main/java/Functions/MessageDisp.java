package Functions;

import static Constants.BaoConstants.LINE_BREAK;

/**
 * Message formatter for display to user.
 */
public class MessageDisp {
    /**
     * Formats displayed messages between 2 line dividers.
     *
     * @param msgs Strings to be displayed to user.
     */
    public static void printWithLine(String... msgs) {
        System.out.print(LINE_BREAK);
        for (String s : msgs) {
            System.out.println(s);
        }
        System.out.print(LINE_BREAK);
    }
}
