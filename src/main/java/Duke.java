import java.util.Scanner;

public class Duke {

    static String[] task_list = new String[100];
    static int task_count=0;

    //method prints a horizontal line.
    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    //method prints Duke greeting.
    public static void greeting(){
        displayLine();
        System.out.println("Hey there! Duke here!");
        System.out.println("How can I serve you today?");
        displayLine();
    }

    //method prints Duke termination.
    public static void goodbye(){
        System.out.println("Goodbye. See you in the funny papers.");
        displayLine();
    }

    //method prints task_list.
    public static void printList(){
        for (int i=0;i<task_count;i++){
            System.out.println(String.format("%d. ",i+1)+task_list[i]);
        }
        displayLine();
    }

    //method adds task to task_list.
    public static void addTaskToList(String task){
        task_list[task_count]=task;
        task_count++;
        System.out.println("added: "+task);
        displayLine();
    }

    //method runs main echo functionality of duke.
    public static void echo(){
        Scanner in = new Scanner(System.in);
        while(true){

            //read input from user.
            String input = in.nextLine();
            displayLine();

            //check for 'bye'. Exit if so.
            if(input.equals("bye")) {
                return;
            }
            else if(input.equals("list")){
                printList();
            }
            else{
                addTaskToList(input);
            }
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
        greeting();

        //echo loop
        echo();

        //ending sequence
        goodbye();
    }
}
