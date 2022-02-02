package Ui;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public static ArrayList<String> readTask(String input) {
        ArrayList<String> returnArray = new ArrayList<String>(Arrays.asList(input.split(" ", 2)));
        return returnArray;
    }

    public static ArrayList<String> readTaskDate(ArrayList<String> inputArray) {
        ArrayList<String> returnArray = new ArrayList<String>(Arrays.asList(inputArray.get(1).split("/", 2)));
        return returnArray;
    }
}
