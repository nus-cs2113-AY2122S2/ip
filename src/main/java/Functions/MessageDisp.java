package Functions;

import static Constants.BaoConstants.LINE_BREAK;

public class MessageDisp {
    public static void printWithLine(String... msgs) {
        System.out.print(LINE_BREAK);
        for (String s : msgs) {
            System.out.println(s);
        }
        System.out.print(LINE_BREAK);
    }
}
