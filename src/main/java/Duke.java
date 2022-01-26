import java.util.Scanner;

public class Duke {

    static Task[] taskList = new Task[100];
    static int taskCount=0;

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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.println(String.format("%d. ", i + 1) + taskList[i].getTaskEntryString());
        }
        displayLine();
    }

    //method adds task to task_list.
    public static void addTaskToList(String task){
        Task newTask = new Task(task);                  //create a new Task object.
        taskList[taskCount] = newTask;                //add the new object to task_list.
        taskCount++;                                   //keep track of the number of tasks in task_list.
        System.out.println("added: " + task);
        displayLine();
    }

    //method marks task in list with taskNumber as done.
    public static void markTaskAsDone(int taskNumber){
        taskList[taskNumber-1].markAsDone();           //mark task with taskNumber as done.
        System.out.println("Great Job! I've marked the following task as done:");
        System.out.println(taskList[taskNumber-1].getTaskEntryString());     //display updated task entry in list.
        displayLine();
    }

    //method marks task in list with taskNumber as not yet done.
    public static void unmarkTaskAsDone(int taskNumber){
        taskList[taskNumber-1].unmarkAsDone();         //mark task with taskNumber as yet to be done.
        System.out.println("Ok, I've marked the following task as yet to be done:");
        System.out.println(taskList[taskNumber-1].getTaskEntryString());     //display updated task entry in list.
        displayLine();
    }

    //method runs main echo functionality of duke.
    public static void echo(){
        Scanner in = new Scanner(System.in);
        while(true){

            //read input from user.
            String userInput = in.nextLine();
            displayLine();

            if(userInput.equals("bye")) {                   //check for 'bye'. Exit if so.
                return;
            }
            else if(userInput.equals("list")){
                printList();
            }
            else if ((userInput.length()>=4)&&(userInput.substring(0,4).equals("mark"))){       //check for mark command.
                markTaskAsDone(Integer.parseInt(userInput.split(" ")[1]));
            }
            else if ((userInput.length()>=6)&&(userInput.substring(0,6).equals("unmark"))){     //check for unmark command.
                unmarkTaskAsDone(Integer.parseInt(userInput.split(" ")[1]));
            }
            else{
                addTaskToList(userInput);
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

        //opening sequence.
        greeting();

        //echo loop between user and Dukebot.
        echo();

        //ending sequence.
        goodbye();
    }
}
