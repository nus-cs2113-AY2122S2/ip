package superhero.ui;

import java.util.Scanner;
import java.util.ArrayList;

import superhero.wordlist.ToLearn;
import superhero.wordlist.VocabList;
import superhero.wordlist.Vocabulary;
import superhero.wordlist.Deadline;
import superhero.wordlist.Event;

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
                    inputList.getList().get(index - 1).setDone(true);
                    Vocabulary markedVocab = inputList.getList().get(index - 1);
                    this.printMarkMessage(markedVocab);
                } catch (NumberFormatException e) {
                    printNumberFormatExceptionMessage();
                } catch (NullPointerException e) {
                    printNullPointerExceptionMessage();
                } catch (IndexOutOfBoundsException e) {
                    printIndexOutOfBoundsExceptionMessage("general");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(inputArray.get(1));
                    inputList.getList().get(index - 1).setDone(false);
                    Vocabulary unmarkedVocab = inputList.getList().get(index - 1);
                    this.printUnmarkMessage(unmarkedVocab);
                } catch (NumberFormatException e) {
                    printNumberFormatExceptionMessage();
                } catch (NullPointerException e) {
                    printNullPointerExceptionMessage();
                } catch (IndexOutOfBoundsException e) {
                    printIndexOutOfBoundsExceptionMessage("general");
                }
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(inputArray.get(1));
                    Vocabulary deleteVocab = inputList.getList().get(index - 1);
                    inputList.getList().remove(index - 1);
                    this.printDeleteMessage(deleteVocab);
                } catch (NumberFormatException e) {
                    printNumberFormatExceptionMessage();
                } catch (NullPointerException e) {
                    printNullPointerExceptionMessage();
                } catch (IndexOutOfBoundsException e) {
                    printIndexOutOfBoundsExceptionMessage("general");
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

    private void printMarkMessage(Vocabulary markedVocab) {
        System.out.println(dottedLine + "\n" +
                " We've just confirmed you have understood this word:\n" +
                " " + markedVocab + "\n" +
                " Now go learn more new words!\n" +
                dottedLine);
    }

    private void printUnmarkMessage(Vocabulary unmarkedVocab) {
        System.out.println(dottedLine + "\n" +
                " We've just confirmed you forgot this word:\n" +
                " " + unmarkedVocab + "\n" +
                " What happened???\n" +
                dottedLine);
    }

    private void printDeleteMessage(Vocabulary deleteVocab) {
        System.out.println(dottedLine + "\n" +
                " You have deleted this word:\n" +
                " " + deleteVocab + "\n" +
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
     * Method to print message when user inputs wrong index/has missing fields when adding a vocabulary
     * @param taskName Type of task is needed to identify what message to print
     */
    public void printIndexOutOfBoundsExceptionMessage(String taskName) {
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
