import java.util.ArrayList;

public class IOMethods {
    public static ArrayList<String> splitToTwo(String line, String delimiter) {
        ArrayList<String> words = new ArrayList<>();
        int divider = line.indexOf(delimiter);

        words.add(line);

        if (divider != -1) {
            words.set(0, line.substring(0, divider));
            words.add(line.substring(divider+1));
        }
        return words;
    }

    public static void printWithDivider(String stringWithinDivider) {
        String breakLine = "\t____________________________________________________________";
        System.out.println(breakLine);
        stringWithinDivider = stringWithinDivider.replace("\n", "\n\t");
        System.out.println("\t" + stringWithinDivider);
        System.out.println(breakLine);
    }

}
