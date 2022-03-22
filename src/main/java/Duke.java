import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String fillerLine = "____________________________________________________________";

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(fillerLine);

    }


    public static void main(String[] args){
        //start the programme
        DukeList dukeList = new DukeList();
        greetings();

        //get the user input
        Scanner in = new Scanner(System.in);
        String userInput = "";
        userInput = in.nextLine();

        String fillerLine = "____________________________________________________________";

        while (!userInput.toLowerCase().equals("bye")) {

            //get the list and print it
            if (userInput.toLowerCase().equals("list")) {
                ArrayList<String> currentList = dukeList.getList();
                for (int i = 0; i < currentList.size(); i += 1) {
                    System.out.println("\t" + (i + 1) + ". " + currentList.get(i));
                }

                System.out.println(fillerLine);

            } else {
                // Add text to list
                boolean isSuccess = dukeList.addText(userInput);
                if(isSuccess){
                    System.out.println("\t" + userInput);
                    System.out.println();
                }else{
                    System.out.println("Oops sorry! Somehow I wasn't able to add your text to my list.");
                }

            }


            //get the next user input
            userInput = in.nextLine();
            System.out.println(fillerLine);
        }

        //if user says bye
        System.out.println("See you again soon!");

    }
}
