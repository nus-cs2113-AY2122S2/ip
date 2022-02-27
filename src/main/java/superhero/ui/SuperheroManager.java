package Superhero.ui;

import Superhero.wordlist.Vocabulary;
import Superhero.wordlist.VocabList;
import Superhero.wordlist.ToLearn;
import Superhero.wordlist.Deadline;
import Superhero.wordlist.Event;

import java.util.ArrayList;

/**
 * Serves as a manager class with various methods to process the information from Superhero class
 */
public class SuperheroManager {

    private static final String dottedLine = "________________________________________________________________________________";

    /**
     * Adds a ToLearn object into the inputList
     * @param inputArray new input from user
     * @param inputList existing vocab list
     */
    public static void addToLearn(ArrayList<String> inputArray, VocabList inputList) {
        try {
            Vocabulary newTolearnWord = new ToLearn(inputArray.get(1));
            printInput(newTolearnWord.getWord());
            inputList.appendList(newTolearnWord);
        } catch (IndexOutOfBoundsException e) {
            printIndexOutOfBoundsExceptionMessage("tolearn");
        }
    }

    /**
     * Adds a Deadline object into the inputList
     * @param inputArray new input from user
     * @param inputList existing vocab list
     */
    public static void addDeadline(ArrayList<String> inputArray, VocabList inputList) {
        try {
            ArrayList<String> deadlineArray = Parser.readTaskDate(inputArray);
            Vocabulary newDeadlineWord = new Deadline(deadlineArray.get(0), deadlineArray.get(1));
            printInput(newDeadlineWord.getWord());
            inputList.appendList(newDeadlineWord);
        } catch (IndexOutOfBoundsException e) {
            printIndexOutOfBoundsExceptionMessage("deadline");
        }
    }

    /**
     * Adds an Event object into the inputList
     * @param inputArray new input from user
     * @param inputList existing vocab list
     */
    public static void addEvent(ArrayList<String> inputArray, VocabList inputList) {
        try {
            ArrayList<String> eventArray = Parser.readTaskDate(inputArray);
            Vocabulary newEventWord = new Event(eventArray.get(0), eventArray.get(1));
            printInput(newEventWord.getWord());
            inputList.appendList(newEventWord);
        } catch (IndexOutOfBoundsException e) {
            printIndexOutOfBoundsExceptionMessage("event");
        }
    }

    /**
     * Mark a Vocab in inputList
     * @param inputArray new input from user
     * @param inputList existing vocab list
     */
    public static void mark(ArrayList<String> inputArray, VocabList inputList) {
        try {
            int index = Integer.parseInt(inputArray.get(1));
            inputList.getList().get(index - 1).setDone(true);
            Vocabulary markedVocab = inputList.getList().get(index - 1);
            printMarkMessage(markedVocab);
        } catch (NumberFormatException e) {
            printNumberFormatExceptionMessage();
        } catch (NullPointerException e) {
            printNullPointerExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            printIndexOutOfBoundsExceptionMessage("general");
        }
    }

    /**
     * Unmark a Vocab in inputList
     * @param inputArray new input from user
     * @param inputList existing vocab list
     */
    public static void unmark(ArrayList<String> inputArray, VocabList inputList) {
        try {
            int index = Integer.parseInt(inputArray.get(1));
            inputList.getList().get(index - 1).setDone(false);
            Vocabulary unmarkedVocab = inputList.getList().get(index - 1);
            printUnmarkMessage(unmarkedVocab);
        } catch (NumberFormatException e) {
            printNumberFormatExceptionMessage();
        } catch (NullPointerException e) {
            printNullPointerExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            printIndexOutOfBoundsExceptionMessage("general");
        }
    }

    /**
     * Delete a Vocab in inputList
     * @param inputArray new input from user
     * @param inputList existing vocab list
     */
    public static void delete(ArrayList<String> inputArray, VocabList inputList) {
        try {
            int index = Integer.parseInt(inputArray.get(1));
            Vocabulary deleteVocab = inputList.getList().get(index - 1);
            inputList.getList().remove(index - 1);
            printDeleteMessage(deleteVocab);
        } catch (NumberFormatException e) {
            printNumberFormatExceptionMessage();
        } catch (NullPointerException e) {
            printNullPointerExceptionMessage();
        } catch (IndexOutOfBoundsException e) {
            printIndexOutOfBoundsExceptionMessage("general");
        }
    }

    /**
     * Find a Vocab in inputList using a keyword
     * @param inputArray new input from user
     * @param inputList existing vocab list
     */
    public static void find(ArrayList<String> inputArray, VocabList inputList) {
        int match = 0;
        String targetWord = inputArray.get(1);
        for (int i = 0; i < inputList.getList().size(); i++) {
            if (inputList.getList().get(i).getWord().contains(targetWord)) {
                System.out.println(inputList.getList().get(i).toString());
                match++;
            }
        }
        if (match == 0) {
            System.out.println(" No Vocab found that matches your search!");
        }
        System.out.println(dottedLine);
    }

    /**
     * Print welcome message
     */
    public static void printWelcomeMessage() {
        System.out.println(dottedLine + "\n" +
                " *Flies in*\n" +
                " Hello! I'm your friendly neighbourhood vocabulary superhero!\n" +
                " Come spelling bee tell me what words/phrases you know?\n" +
                dottedLine);
    }

    /**
     * Print bye message
     */
    public static void printByeMessage() {
        System.out.println(dottedLine + "\n" +
                " Bye. I'm off saving people's vocabulary again!\n" +
                " *Flies away*\n" +
                dottedLine);
    }

    /**
     * Method to print message when user input does not contain keyword
     */
    public static void printDefaultMessage() {
        System.out.println(" Please use keyword - bye, list, tolearn, deadline, event, mark, unmark, delete, find!\n" +
                dottedLine);
    }

    private static void printMarkMessage(Vocabulary markedVocab) {
        System.out.println(dottedLine + "\n" +
                " We've just confirmed you have understood this word:\n" +
                " " + markedVocab + "\n" +
                " Now go learn more new words!\n" +
                dottedLine);
    }

    private static void printUnmarkMessage(Vocabulary unmarkedVocab) {
        System.out.println(dottedLine + "\n" +
                " We've just confirmed you forgot this word:\n" +
                " " + unmarkedVocab + "\n" +
                " What happened???\n" +
                dottedLine);
    }


    private static void printDeleteMessage(Vocabulary deleteVocab) {
        System.out.println(dottedLine + "\n" +
                " You have deleted this word:\n" +
                " " + deleteVocab + "\n" +
                dottedLine);
    }

    private static void printInput(String input) {
        System.out.println(dottedLine + "\n" +
                " You have a new word to learn: " + input + "\n" +
                " -from your neighbourhood vocabulary superhero\n" +
                dottedLine);
    }

    private static void printNumberFormatExceptionMessage() {
        System.out.println("Second word in input is not a number!\n" +
                dottedLine);
    }

    private static void printNullPointerExceptionMessage() {
        System.out.println("There is no word with this index in the list!\n" +
                dottedLine);
    }

    private static void printIndexOutOfBoundsExceptionMessage(String taskName) {
        if (taskName.equals("general")) {
            System.out.println("Index out of bounds!\n" +
                    dottedLine);
        }
        else if (taskName.equals("tolearn")) {
            System.out.println("Wrong format! Format should be as such: tolearn (vocab)\n" +
                    dottedLine);
        }
        else {
            System.out.println("Wrong format! Format should be as such: " + taskName + " (vocab)/(time)\n" +
                    dottedLine);
        }
    }
}
