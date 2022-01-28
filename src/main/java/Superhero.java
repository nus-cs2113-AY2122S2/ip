import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Superhero {

    DictStorage inputDict = new DictStorage();
    Scanner choice = new Scanner(System.in);
    String input;
    ArrayList<String> inputArray;

    public void startVocabCheck() {
        this.printWelcomeMessage();
        do {
            input = choice.nextLine();
            inputArray = new ArrayList<String>(Arrays.asList(input.split(" ")));
            switch (inputArray.get(0)) {
            case "bye":
                this.printByeMessage();
                break;
            case "dict":
                inputDict.printDict();
                break;
            case "mark":
                try {
                    int index = Integer.parseInt(inputArray.get(1));
                    inputDict.getDict()[index - 1].setConfirmed(true);
                    this.printMarkMessage(index - 1);
                } catch (NumberFormatException e){
                    System.out.println("Second word in input is not a number!\n" +
                            "____________________________________________________________");
                } catch (NullPointerException e){
                    System.out.println("Your index is out of bounds!\n" +
                            "____________________________________________________________");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(inputArray.get(1));
                    inputDict.getDict()[index - 1].setConfirmed(false);
                    this.printUnmarkMessage(index - 1);
                } catch (NumberFormatException e){
                    System.out.println("Second word in input is not a number!\n" +
                            "____________________________________________________________");
                } catch (NullPointerException e){
                    System.out.println("Your index is out of bounds!\n" +
                            "____________________________________________________________");
                }
                break;
            default:
                printInput(input);
                inputDict.appendDict(input);
                break;
            }
        }while(!inputArray.get(0).equals("bye")) ;
    }

    private void printWelcomeMessage(){
        System.out.println("____________________________________________________________\n" +
                " *Flies in*\n" +
                " Hello! I'm your friendly neighbourhood vocabulary superhero!\n" +
                " Come spelling bee tell me what words/phrases you know?\n" +
                "____________________________________________________________");
    }

    private void printByeMessage(){
        System.out.println("____________________________________________________________\n" +
                " Bye. I'm off saving people's vocabulary again!\n" +
                " *Flies away*\n" +
                "____________________________________________________________");
    }

    private void printMarkMessage(int index){
        System.out.println("____________________________________________________________\n" +
                " We've just confirmed you have understood this word:\n" +
                " [x] " + inputDict.getDict()[index].getWord() + "\n" +
                " Now go learn more new words!\n" +
                "____________________________________________________________");
    }

    private void printUnmarkMessage(int index){
        System.out.println("____________________________________________________________\n" +
                " We've just confirmed you forgot this word:\n" +
                " [ ] " + inputDict.getDict()[index].getWord() + "\n" +
                " What happened???\n" +
                "____________________________________________________________");
    }

    private void printInput(String input){
        System.out.println("____________________________________________________________\n" +
                " Congratulations you have just come across a new word: " + input +
                "\n -from your neighbourhood vocabulary superhero\n" +
                "____________________________________________________________");
    }
}
