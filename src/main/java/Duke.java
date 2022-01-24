import java.util.Scanner;

public class Duke {

    //method prints a horizontal line.
    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    //method prints Duke greeting.
    public static void greeting(){
        System.out.println("Hey there! Duke here!");
        System.out.println("How can I serve you today?");
        displayLine();
    }

    //method prints Duke termination.
    public static void goodbye(){
        System.out.println("Goodbye. See you in the funny papers.");
        displayLine();
    }

    //method runs main echo functionality of duke.
    public static void echo(){
        Scanner in = new Scanner(System.in);
        while(true){
            //read input.
            String input = in.nextLine();
            displayLine();

            //check for 'bye'. Exit if so.
            if(input.equals("bye")){
                return;
            }

            //print input if not bye.
            System.out.println(input);
            displayLine();
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("You are entering the\n" + logo + "\nZone...\n");

        //opening sequence
        displayLine();
        greeting();

        //echo loop
        echo();

        //ending sequence
        goodbye();

    }
}
