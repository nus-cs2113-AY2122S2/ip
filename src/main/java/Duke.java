import java.util.Scanner;

public class Duke {

    static Task[] taskList = new Task[100];
    static int taskCount=0;

    // method prints a horizontal line.
    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    // method prints Duke greeting.
    public static void greeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("You are entering the\n" + logo + "\nZone...\n");

        displayLine();
        System.out.println("Hey there! Duke here!");
        System.out.println("How can I serve you today?");
        displayLine();
    }

    // method prints Duke termination.
    public static void goodbye(){
        System.out.println("Goodbye. See you in the funny papers.");
        displayLine();
    }


    // method prints taskList.
    public static void printList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.print(String.format("%d. ", i + 1));
            System.out.println(taskList[i]);
        }
        displayLine();
    }

    public static boolean isDeadline(String userInput){
        if(userInput.length()>=8){
            // return true if first 8 letters of userInput spell "deadline", else false
            return userInput.substring(0,8).equals("deadline");
        }
        return false;
    }

    public static boolean isTodo(String userInput){
        if(userInput.length()>=4){
            // return true if first 4 letters of userInput spell "todo", else false
            return userInput.substring(0,4).equals("todo");
        }
        return false;
    }

    public static boolean isEvent(String userInput){
        if(userInput.length()>=5){
            // return true if first 5 letters of userInput spell "event", else false
            return userInput.substring(0,5).equals("event");
        }
        return false;
    }

    public static String getTodoFromUserInput(String userInput){
        return userInput.substring(5,userInput.length());
    }

    public static String getEventFromUserInput(String userInput, int eventTimeIdx){
        return userInput.substring(6,eventTimeIdx);
    }

    public static String getDeadlineFromUserInput(String userInput, int dueDateIdx){
        return userInput.substring(9,dueDateIdx);
    }

    public static String getEventTimeFromUserInput(String userInput, int eventTimeIdx){
        // ignore /by in the userInput
        return userInput.substring(eventTimeIdx+4,userInput.length());
    }

    public static String getDueDateFromUserInput(String userInput, int dueDateIdx){
        // ignore /at in the userInput
        return userInput.substring(dueDateIdx+4,userInput.length());
    }

    private static void printAddedTask() {
        System.out.println("Noted. I've added:");
        System.out.println(taskList[taskCount-1]);
        System.out.println("Now you have "+Integer.toString(taskCount)+" tasks in the list.");
        displayLine();
    }

    public static void addTodoToList(String todoDescription){
        //create a new Todo object.
        Todo newTodo = new Todo(todoDescription);
        //add the new object to taskList.
        taskList[taskCount] = newTodo;
        //keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    public static void addEventToList(String eventDescription, String eventTime){
        // create a new Event object.
        Event newEvent = new Event(eventDescription, eventTime);
        // add the new object to taskList.
        taskList[taskCount] = newEvent;
        // keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    public static void addDeadlineToList(String deadlineDescription, String dueDate){
        // create a new Deadline object.
        Deadline newDeadline = new Deadline(deadlineDescription, dueDate);
        // add the new object to taskList.
        taskList[taskCount] = newDeadline;
        // keep track of the number of tasks in taskList.
        taskCount++;

        printAddedTask();
    }

    // method adds task to task_list.
    public static void addTaskToList(String userInput){
        if(isTodo(userInput)){
            String todoDescription = getTodoFromUserInput(userInput);

            // add Todo task to List
            addTodoToList(todoDescription);
        }
        else if(isEvent(userInput)){
            int eventTimeIdx = userInput.indexOf("/");
            String eventDescription = getEventFromUserInput(userInput,eventTimeIdx);
            String eventTime = getEventTimeFromUserInput(userInput,eventTimeIdx);

            // add Event task to List
            addEventToList(eventDescription,eventTime);
        }
        else if(isDeadline(userInput)){
            int dueDateIdx = userInput.indexOf("/");
            String deadlineDescription = getDeadlineFromUserInput(userInput,dueDateIdx);
            String dueDate = getDueDateFromUserInput(userInput,dueDateIdx);

            // add Deadline task to List
            addDeadlineToList(deadlineDescription,dueDate);
        }
        // If not a recognizable command, inform user
        else{
            System.out.println("OOPS! I'm sorry but I don't know what you mean :(");
        }
    }

    //method marks task in list with taskNumber as done.
    public static void markTaskAsDone(int taskNumber){
        // mark task with taskNumber as done.
        taskList[taskNumber-1].markAsDone();
        System.out.println("Great Job! I've marked the following task as done:");
        // display updated task entry in list.
        System.out.println(taskList[taskNumber-1]);
        displayLine();
    }

    //method marks task in list with taskNumber as not yet done.
    public static void unmarkTaskAsDone(int taskNumber){
        // mark task with taskNumber as yet to be done.
        taskList[taskNumber-1].unmarkAsDone();
        System.out.println("Ok, I've marked the following task as yet to be done:");
        // display updated task entry in list.
        System.out.println(taskList[taskNumber-1]);
        displayLine();
    }

    public static boolean isMarkCommand(String userInput){
        if(userInput.length()>=4){
            // return true if first 4 letters of userInput spell "mark", else false
            return userInput.substring(0,4).equals("mark");
        }
        return false;
    }

    public static boolean isUnmarkCommand(String userInput){
        if(userInput.length()>=6){
            // return true if first 6 letters of userInput spell "mark", else false
            return userInput.substring(0,6).equals("unmark");
        }
        return false;
    }

    public static boolean isListCommand(String userInput){
        // return true if command is list, else false
        return userInput.equals("list");
    }

    public static boolean isByeCommand(String userInput){
        // return true if command is list, else false
        return userInput.equals("bye");
    }


    public static int getTaskNumber(String userInput){
        return Integer.parseInt(userInput.split(" ")[1]);
    }

    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        displayLine();
        return userInput;
    }

    // method runs main echo functionality of duke.
    public static void echo(){
        while(true){
            //read input from user.
            String userInput = getUserInput();

            // Check userInput for respective command.

            // Exit if "bye" is entered by user.
            if(isByeCommand(userInput)) {
                return;
            }
            else if(isListCommand(userInput)){
                printList();
            }
            else if (isMarkCommand(userInput)){
                int taskNumber=getTaskNumber(userInput);
                markTaskAsDone(taskNumber);
            }
            else if (isUnmarkCommand(userInput)){
                int taskNumber=getTaskNumber(userInput);
                unmarkTaskAsDone(taskNumber);
            }
            // else it is an addition command or some unknown command
            else{
                addTaskToList(userInput);
            }
        }
    }

    public static void main(String[] args) {
        // opening sequence.
        greeting();

        // echo loop between user and Dukebot.
        echo();

        // ending sequence.
        goodbye();
    }
}
