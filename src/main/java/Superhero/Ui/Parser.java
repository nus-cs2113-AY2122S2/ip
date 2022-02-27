package Superhero.ui;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Serves to reformat the user input to be suitable for use by other classes
 */
public class Parser {
    /**
     * Splits user input into 2 halves, namely type of vocabulary (tolearn, deadline, event)
     * and the name of the vocabulary (and time for deadline and event)
     * @param input User input
     * @return string ArrayList of size 2 that contains type of vocabulary and its name
     */
    public static ArrayList<String> readTask(String input) {
        ArrayList<String> returnArray = new ArrayList<String>(Arrays.asList(input.split(" ", 2)));
        return returnArray;
    }

    /**
     * Splits deadline and event into 2 halves, namely the name of the vocabulary and the time
     * @param inputArray String ArrayList from readTask method
     * @return string ArrayList of size 2 that contains name of vocabulary and time
     */
    public static ArrayList<String> readTaskDate(ArrayList<String> inputArray) {
        ArrayList<String> returnArray = new ArrayList<String>(Arrays.asList(inputArray.get(1).split("/", 2)));
        return returnArray;
    }
}
