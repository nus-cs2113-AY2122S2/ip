import java.util.Scanner;

public class Superhero {
    public static void main(String[] args) {
        DictStorage dict = new DictStorage();
        Scanner choice = new Scanner(System.in);

        printWelcomeMessage();
        String input;
        do {
            input = choice.nextLine();
            switch (input) {
            case "bye":
                printByeMessage();
                break;
            case "dict":
                dict.printDict();
                break;
            default:
                printInput(input);
                dict.appendDict(input);
                break;
            }
        }while(!input.equals("bye")) ;
    }

    public static void printWelcomeMessage(){
        System.out.println("____________________________________________________________\n" +
                " *Flies in*\n" +
                " Hello! I'm your friendly neighbourhood vocabulary superhero!\n" +
                " Come spelling bee tell me what words/phrases you know?\n" +
                "____________________________________________________________");
    }

    public static void printByeMessage(){
        System.out.println("____________________________________________________________\n" +
                " Bye. I'm off saving the day again!\n" +
                " *Flies away*\n" +
                "____________________________________________________________");
    }

    public static void printInput(String input){
        System.out.println("____________________________________________________________\n" +
                " Congratulations you have just shown me that you know the word: " + input +
                "\n -from your sarcastic neighbourhood superhero\n" +
                "____________________________________________________________");
    }
}
