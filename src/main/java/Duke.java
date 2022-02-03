import java.util.Scanner;

public class Duke {

    public static Task[] list = new Task[100];
    public static int taskCounter = 0;



    public static void printList(Task[] list, int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.print(i + 1 + ".");
            printTask(list[i]);
        }
        printNumberOfTasksInList();
    }

    public static void printTask(Task t) {
        System.out.println(t.toString());
        //System.out.print("[" + t.getType() + "]");
        //System.out.println("[" + t.getStatusIcon() + "] " + t.taskName);
    }

    public static void printNumberOfTasksInList() {
        System.out.println("Now you have " + taskCounter + " tasks in the list.");
    }

    public static String getFirstWordOfCommand(String s) {
        int spaceIndex = s.indexOf(" ");
        if (spaceIndex == -1) {
            return s;
        }
        String firstWord = s.substring(0, spaceIndex);
        System.out.println("First Word: " + firstWord);
        return firstWord;
    }

    public static String getToDoTask(String s) {
        int spaceIndex = s.indexOf(" ");
        String firstWord = s.substring(spaceIndex);
        System.out.println("ToDo Task is: " + firstWord);
        return firstWord.trim();
    }

    public static int getTaskNumberArgument(String input) {
        int spaceIndex = input.indexOf(" ");
        String taskNum = input.substring(spaceIndex + 1);
        System.out.println(taskNum);
        return Integer.parseInt(taskNum.trim());
    }

    public static String getDeadlineDate(String input) {
        int byIndex = input.indexOf("/by");
        int deadlineIndex = input.indexOf(" ",byIndex);
        String deadlineDate = input.substring(deadlineIndex + 1);
        System.out.println(deadlineDate);
        return deadlineDate.trim();
    }
    public static String getDeadlineTask(String input) {
        //first space
        int firstSpaceIndex = input.indexOf(" ");
        int byIndex = input.indexOf("/by");
        String deadlineTask = input.substring(firstSpaceIndex+1,byIndex-1);
        System.out.println(deadlineTask);
        return deadlineTask.trim();
    }
    public static void addToDoTask(String input){

        String taskDescription = getToDoTask(input);
        Todo t = new Todo(taskDescription);
        list[taskCounter] = t;
        taskCounter++;
        System.out.println("Got it. I've added this task:");
        printTask(t);
        printNumberOfTasksInList();
    }
    public static void addDeadlineTask(String input){
        Deadline d = new Deadline(getDeadlineTask(input),getDeadlineDate(input));
        list[taskCounter] = d;
        taskCounter++;
        System.out.println("Got it. I've added this task:");
        printTask(d);
        printNumberOfTasksInList();
    }

    public static String getEventTask(String input) {
        //first space
        int firstSpaceIndex = input.indexOf(" ");
        int byIndex = input.indexOf("/at");
        String deadlineTask = input.substring(firstSpaceIndex+1,byIndex-1);
        System.out.println(deadlineTask);
        return deadlineTask;
    }

    public static String getEventDateTime(String input) {
        int atIndex = input.indexOf("/at");
        int eventDateTimeIndex = input.indexOf(" ",atIndex);
        String eventDateTime = input.substring(eventDateTimeIndex + 1);
        System.out.println(eventDateTime);
        return eventDateTime;
    }
    public static void addEventTask(String input){
        Event e = new Event(getEventTask(input),getEventDateTime(input));
        list[taskCounter] = e;
        taskCounter++;
        System.out.println("Got it. I've added this task:");
        printTask(e);
        printNumberOfTasksInList();

    }
    public static void markTaskAsComplete(String input){
        int taskNum = getTaskNumberArgument(input);
        System.out.println(taskNum);
        list[taskNum - 1].setDone(true);
        System.out.println(list[taskNum-1].isDone());
        printTask(list[taskNum - 1]);
        System.out.println("Nice! I've marked this task as done:\n");
    }



    public static void unmarkTaskAsIncomplete(String input){
        int taskNum = getTaskNumberArgument(input);
        System.out.println(taskNum);
        list[taskNum - 1].setDone(false);
        System.out.println(list[taskNum-1].isDone());
        printTask(list[taskNum - 1]);
        System.out.println("Ok I have marked this task as incomplete:\n");

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            String command = getFirstWordOfCommand(input);
            switch (command) {
            case "list":
                printList(list, taskCounter);
                break;
            case "mark":
                markTaskAsComplete(input);
                break;
            case "unmark":
                unmarkTaskAsIncomplete(input);
                break;
            case "todo":
                addToDoTask(input);
                break;
            case "deadline":
                addDeadlineTask(input);
                break;
            case "event":
                addEventTask(input);
                break;
            }
            input = sc.nextLine();
        }



        System.out.println("Bye. Hope to see you again soon!");
    }
}
