import java.util.Scanner;

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

    }


    public static void main(String[] args){
        //start the programme
        greetings();

        //get the user input
        Scanner in = new Scanner(System.in);
        String userInput = "";
        userInput = in.nextLine();

        String fillerLine = "____________________________________________________________";

        while (!userInput.toLowerCase().equals("bye")) {
            // Echo the user input
            System.out.println(fillerLine);
            System.out.println(userInput);
            System.out.println(fillerLine);


            //get the next user input
            userInput = in.nextLine();
            System.out.println(fillerLine);
        }

        //if user says bye
        System.out.println("See you again soon!");

    }
}
