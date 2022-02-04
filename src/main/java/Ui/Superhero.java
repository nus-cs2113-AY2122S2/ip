package Ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import WordList.ToLearn;
import WordList.VocabList;
import WordList.Vocabulary;
import WordList.Deadline;
import WordList.Event;

public class Superhero {

    private final String dottedLine = "____________________________________________________________";
    VocabList inputList = new VocabList();
    Scanner choice = new Scanner(System.in);
    String input;
    ArrayList<String> inputArray;

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
            case "todo":
                Vocabulary newTodoWord = new ToLearn(inputArray.get(1));
                printInput(newTodoWord.getWord());
                inputList.appendList(newTodoWord);
                break;
            case "deadline":
                ArrayList<String> deadlineArray = Parser.readTaskDate(inputArray);
                Vocabulary newDeadlineWord = new Deadline(deadlineArray.get(0), deadlineArray.get(1));
                printInput(newDeadlineWord.getWord());
                inputList.appendList(newDeadlineWord);
                break;
            case "event":
                ArrayList<String> eventArray = Parser.readTaskDate(inputArray);
                Vocabulary newEventWord = new Event(eventArray.get(0), eventArray.get(1));
                printInput(newEventWord.getWord());
                inputList.appendList(newEventWord);
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
        } while(!inputArray.get(0).equals("bye")) ;
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

    private void printDefaultMessage() {
        System.out.println(dottedLine + "\n" +
                " Please use keyword - bye, todo, list, todo, deadline, event, mark, unmark!\n" +
                dottedLine);
    }

    private void printNumberFormatExceptionMessage() {
        System.out.println("Second word in input is not a number!\n" +
                dottedLine);
    }

    private void printNullPointerExceptionMessage() {
        System.out.println("Your index is out of bounds!\n" +
                dottedLine);
    }
}
