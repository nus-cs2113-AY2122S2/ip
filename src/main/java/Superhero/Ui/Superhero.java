package superhero.Ui;

import java.util.Scanner;
import java.util.ArrayList;

import superhero.WordList.ToLearn;
import superhero.WordList.VocabList;
import superhero.WordList.Vocabulary;
import superhero.WordList.Deadline;
import superhero.WordList.Event;

public class Superhero {

    private final String dottedLine = "________________________________________________________________________________";
    VocabList inputList = new VocabList();
    Scanner choice = new Scanner(System.in);
    String input;
    ArrayList<String> inputArray;

    /**
     * This method is the main method for the Superhero class
     * and it is responsible for displaying messages (by calling other methods)
     * and receiving input from user
     */
    public void startVocabCheck() {
        this.printWelcomeMessage();
        do {
            input = choice.nextLine();
            inputArray = Parser.readTask(input);
            switch (inputArray.get(0)) {
            case "bye":
                this.printByeMessage();
                break;
            case "list":
                inputList.printList();
                break;
            case "tolearn":
                try {
                    Vocabulary newTolearnWord = new ToLearn(inputArray.get(1));
                    printInput(newTolearnWord.getWord());
                    inputList.appendList(newTolearnWord);
                } catch (IndexOutOfBoundsException e) {
                    printIndexOutOfBoundsExceptionMessage("tolearn");
                }
                break;
            case "deadline":
                try {
                    ArrayList<String> deadlineArray = Parser.readTaskDate(inputArray);
                    Vocabulary newDeadlineWord = new Deadline(deadlineArray.get(0), deadlineArray.get(1));
                    printInput(newDeadlineWord.getWord());
                    inputList.appendList(newDeadlineWord);
                } catch (IndexOutOfBoundsException e) {
                    printIndexOutOfBoundsExceptionMessage("deadline");
                }
                break;
            case "event":
                try {
                    ArrayList<String> eventArray = Parser.readTaskDate(inputArray);
                    Vocabulary newEventWord = new Event(eventArray.get(0), eventArray.get(1));
                    printInput(newEventWord.getWord());
                    inputList.appendList(newEventWord);
                } catch (IndexOutOfBoundsException e) {
                    printIndexOutOfBoundsExceptionMessage("event");
                }
                break;
            case "mark":
                try {
                    int index = Integer.parseInt(inputArray.get(1));
                    inputList.getList()[index - 1].setDone(true);
                    this.printMarkMessage(index - 1);
                } catch (NumberFormatException e) {
                    printNumberFormatExceptionMessage();
                } catch (NullPointerException e) {
                    printNullPointerExceptionMessage();
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(inputArray.get(1));
                    inputList.getList()[index - 1].setDone(false);
                    this.printUnmarkMessage(index - 1);
                } catch (NumberFormatException e) {
                    printNumberFormatExceptionMessage();
                } catch (NullPointerException e) {
                    printNullPointerExceptionMessage();
                }
                break;
            default:
                printDefaultMessage();
            }
        } while(!inputArray.get(0).equals("bye"));
    }

    private void printWelcomeMessage() {
        System.out.println(dottedLine + "\n" +
                " *Flies in*\n" +
                " Hello! I'm your friendly neighbourhood vocabulary superhero!\n" +
                " Come spelling bee tell me what words/phrases you know?\n" +
                dottedLine);
    }

    private void printByeMessage() {
        System.out.println(dottedLine + "\n" +
                " Bye. I'm off saving people's vocabulary again!\n" +
                " *Flies away*\n" +
                dottedLine);
    }

    private void printMarkMessage(int index) {
        System.out.println(dottedLine + "\n" +
                " We've just confirmed you have understood this word:\n" +
                " [x] " + inputList.getList()[index].getWord() + "\n" +
                " Now go learn more new words!\n" +
                dottedLine);
    }

    private void printUnmarkMessage(int index) {
        System.out.println(dottedLine + "\n" +
                " We've just confirmed you forgot this word:\n" +
                " [ ] " + inputList.getList()[index].getWord() + "\n" +
                " What happened???\n" +
                dottedLine);
    }

    private void printInput(String input) {
        System.out.println(dottedLine + "\n" +
                " You have a new word to learn: " + input + "\n" +
                " -from your neighbourhood vocabulary superhero\n" +
                dottedLine);
    }

    /**
     * Method to print message when user input does not contain keyword
     */
    private void printDefaultMessage() {
        System.out.println(" Please use keyword - bye, list, tolearn, deadline, event, mark, unmark!\n" +
                dottedLine);
    }

    /**
     * Method to print message when user wants to mark/unmark a vocabulary but the second word is not a number
     */
    private void printNumberFormatExceptionMessage() {
        System.out.println("Second word in input is not a number!\n" +
                dottedLine);
    }

    /**
     * Method to print message when user inputs index of vocabulary that does not exist
     */
    private void printNullPointerExceptionMessage() {
        System.out.println("There is no word with this index in the list!\n" +
                dottedLine);
    }

    /**
     * Method to print message when user has missing fields when adding a vocabulary
     * @param taskName Type of task is needed to identify what message to print
     */
    public void printIndexOutOfBoundsExceptionMessage(String taskName){
        if (taskName.equals("tolearn")) {
            System.out.println("Wrong format! Format should be as such: tolearn (vocab)\n" +
                    dottedLine);
        }
        else {
            System.out.println("Wrong format! Format should be as such: " + taskName + " (vocab)/(time)\n" +
                    dottedLine);
        }
    }
}
