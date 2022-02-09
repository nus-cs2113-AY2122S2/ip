import java.util.Scanner;
public class Duke {
    private static final int MAX_TASK_SIZE = 100;
    private static Task[] userInput;
    private static int valIndex;
    private static int inputCount = 0;
    private static String line;
    public static void main(String[] args) {
        printWelcomeMessage();
        userInput = new Task[MAX_TASK_SIZE];
        while(true) {
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            if (line.equals("list")){
                printList(inputCount, userInput);
                continue;
            }

            if (line.contains(" ")){
                if (isMark()) {
                    if (checkValidity()){
                        markAsDone();
                    } else {
                        System.out.println("Invalid Command!");
                    } continue;
                } else if (isUnmark()) {
                    if (checkValidity()) {
                        markAsUndone();
                    } else {
                        System.out.println("Invalid Command!");
                    } continue;
                } else if (isTodo()){
                    addTodo();
                    continue;
                } else if (isDeadline()){
                    addDeadline();
                    continue;
                } else if (isEvent()){
                    addEvent();
                    continue;
                }
            }
            if (isBye()) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            userInput[inputCount] = new Task(line);
            inputCount++;
            System.out.println("Add: " + line);
        }
    }

    public static void printWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printList(int inputCount, Task[] userInput){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputCount; i++) {
            System.out.println((i+1) +  ".[" +  userInput[i].getIcon() +"] " + "[" +  userInput[i].getStatusIcon() +"] "+ userInput[i].description);
        }
    }

    public static boolean isUnmark(){
        return (line.substring(0, line.indexOf(" "))).equals("unmark");
    }

    public static boolean isMark(){
        return (line.substring(0, line.indexOf(" "))).equals("mark");
    }

    public static void markAsDone(){
        valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
        userInput[valIndex - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + userInput[valIndex - 1].getStatusIcon() + "] " + userInput[valIndex - 1].description);
    }

    public static void markAsUndone(){
        valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
        userInput[valIndex - 1].markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + userInput[valIndex - 1].getStatusIcon() + "] " + userInput[valIndex - 1].description);
    }

    public static boolean isTodo() {
        return (line.substring(0, line.indexOf(" "))).equals("Todo");
    }

    public static boolean isDeadline() {
        return (line.substring(0, line.indexOf(" "))).equals("Deadline");
    }

    public static boolean isEvent(){
        return (line.substring(0, line.indexOf(" "))).equals("Event");
    }

    public static boolean isBye(){
        return line.equals("bye");
    }
    public static boolean checkValidity(){
        return Integer.parseInt(line.substring(line.indexOf(" ") + 1)) <= inputCount;
    }

    public static void addTodo(){
        userInput[inputCount] = new Todo((line.substring(1 + line.indexOf(" "))));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    public static void addDeadline(){
        userInput[inputCount] = new Deadline((line.substring(1 + line.indexOf(" "), line.indexOf("/by"))),line.substring(line.indexOf("/by") + 4));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description + "(by: " + userInput[inputCount - 1].getBy() + ")");
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    public static void addEvent(){
        userInput[inputCount] = new Event((line.substring(1 + line.indexOf(" "), line.indexOf("/at"))),line.substring(line.indexOf("/at") + 4));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description + "(at: " + userInput[inputCount - 1].getAt() + ")");
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }


}
