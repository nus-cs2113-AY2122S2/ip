package superhero.ui;

import java.util.Scanner;
import java.util.ArrayList;

import superhero.wordlist.VocabList;
import superhero.storage.FileReadWrite;

public class Superhero {


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
        inputList = FileReadWrite.loadFile();
        SuperheroManager.printWelcomeMessage();
        do {
            input = choice.nextLine();
            inputArray = Parser.readTask(input);
            switch (inputArray.get(0)) {
            case "bye":
                SuperheroManager.printByeMessage();
                FileReadWrite.saveFile(inputList);
                break;
            case "list":
                inputList.printList();
                break;
            case "tolearn":
                SuperheroManager.addToLearn(inputArray, inputList);
                break;
            case "deadline":
                SuperheroManager.addDeadline(inputArray, inputList);
                break;
            case "event":
                SuperheroManager.addEvent(inputArray, inputList);
                break;
            case "mark":
                SuperheroManager.mark(inputArray, inputList);
                break;
            case "unmark":
                SuperheroManager.unmark(inputArray, inputList);
                break;
            case "delete":
                SuperheroManager.delete(inputArray, inputList);
                break;
            default:
                SuperheroManager.printDefaultMessage();
            }
        } while (!inputArray.get(0).equals("bye"));
    }
}
