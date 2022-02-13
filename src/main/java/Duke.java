import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<Task> userInput;
    private static int valIndex;
    private static int inputCount = 0;
    private static String line;
    public static void main(String[] args) {
        printWelcomeMessage();
        userInput = new ArrayList<>();
        while(true) {
            Scanner input = new Scanner(System.in);
            try{
                checkCommandValidity(input);
            } catch (InvalidInputException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (isList()){
                printList();
                continue;
            } else if (isBye()) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.contains(" ")){
                if (isMark()) {
                    try {
                        if (checkValidity()) {
                            markAsDone();
                        } else {
                            System.out.println("Invalid Index!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter the index of task you want to mark!");
                    }
                    continue;
                } else if (isUnmark()) {
                    try {
                        if (checkValidity()) {
                            markAsUndone();
                        } else {
                            System.out.println("Invalid Index!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter the index of task you want to unmark!");
                    }
                    continue;
                } else if (isTodo()){
                    try{
                    addTodo();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    continue;
                } else if (isDeadline()){
                    try {
                        addDeadline();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } catch (NumberFormatException e) {
                        System.out.println("☹ OOPS!!! Please set the deadline using 'deadline /by time' format!!!");
                    }
                    continue;
                } else if (isEvent()){
                    try {
                        addEvent();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    } catch (NumberFormatException e) {
                        System.out.println("☹ OOPS!!! Please set the event time using 'event /at time'  format!!!");
                    }
                    continue;
                } else if (isDelete()){
                    try {
                        try {
                            if (!checkValidity())
                            System.out.println("☹ OOPS!!! You do not have this task.");
                        else
                            deleteTask();
                        } catch (NumberFormatException e) {
                            System.out.println("☹ OOPS!!! What exactly do you want to delete?");
                        }
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! What exactly do you want to delete?");
                    }
                }
            }
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

    public static void printList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputCount; i++) {
            System.out.println((i+1) +  ".[" +  userInput.get(i).getIcon() +"] " + "[" +  userInput.get(i).getStatusIcon() +"] "+ userInput.get(i).description + userInput.get(i).showDate());
        }
    }
    public static String getFirstWord() throws StringIndexOutOfBoundsException {
        return (line.substring(0, line.indexOf(" ")));
    }

    public static boolean isUnmark(){
        return getFirstWord().equalsIgnoreCase("unmark");
    }

    public static boolean isMark(){
        return getFirstWord().equalsIgnoreCase("mark");
    }

    public static void markAsDone(){
        valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
        userInput.get(valIndex - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + userInput.get(valIndex - 1).getIcon() + "]" +"[" + userInput.get(valIndex - 1).getStatusIcon() + "] " + userInput.get(valIndex - 1).description + userInput.get(valIndex -1).showDate());
    }

    public static void markAsUndone(){
        valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
        userInput.get(valIndex - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + userInput.get(valIndex - 1).getIcon() + "]" + "[" + userInput.get(valIndex - 1).getStatusIcon() + "] " + userInput.get(valIndex - 1).description + userInput.get(valIndex -1).showDate());
    }

    public static boolean isTodo() {
        return getFirstWord().equalsIgnoreCase("Todo");
    }

    public static boolean isDeadline() {
        return getFirstWord().equalsIgnoreCase("Deadline");
    }

    public static boolean isEvent(){
        return getFirstWord().equalsIgnoreCase("Event");
    }

    public static boolean isDelete(){
        return getFirstWord().equalsIgnoreCase("Delete");
    }

    public static boolean isBye(){
        return (line.trim()).equalsIgnoreCase("bye");
    }
    public static boolean checkValidity() throws NumberFormatException{
        return Integer.parseInt(line.substring(line.indexOf(" ") + 1)) <= inputCount;
    }
    public static boolean isList() {
        return (line.trim()).equalsIgnoreCase("list");
    }

    public static void deleteTask() throws InvalidInputException {
        var taskToBeDeleted = userInput.get(Integer.parseInt(line.substring(1 + line.indexOf(" "))) - 1);
        if ((line.substring(1 + line.indexOf(" "))).trim().isEmpty()) throw new InvalidInputException();
        System.out.println("Noted. I've removed this task: ");
        System.out.println("[" + taskToBeDeleted.getIcon() + "]" + "[" + taskToBeDeleted.getStatusIcon() + "] " + taskToBeDeleted.description + taskToBeDeleted.showDate());
        inputCount--;
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        userInput.remove(taskToBeDeleted);
    }

    public static void addTodo() throws InvalidInputException {
        if ((line.substring(1 + line.indexOf(" "))).trim().isEmpty()) throw new InvalidInputException();
        userInput.add(inputCount, new Todo((line.substring(1 + line.indexOf(" ")))));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput.get(inputCount - 1).getIcon() + "]" + "[" + userInput.get(inputCount - 1).getStatusIcon() + "] " + userInput.get(inputCount - 1).description);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    public static void addDeadline() throws InvalidInputException, NumberFormatException {
        if ((line.substring(1 + line.indexOf(" "))).trim().isEmpty()) throw new InvalidInputException();
        else if (!line.contains(" /by ")) throw new NumberFormatException();
        userInput.add(inputCount, new Deadline((line.substring(1 + line.indexOf(" "), line.indexOf("/by"))), line.substring(line.indexOf("/by") + 4)));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput.get(inputCount - 1).getIcon() + "]" + "[" + userInput.get(inputCount - 1).getStatusIcon() + "] " + userInput.get(inputCount - 1).description + "(by: " + userInput.get(inputCount - 1).getBy() + ")");
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    public static void addEvent() throws InvalidInputException, NumberFormatException {
        if (((line.substring(1 + line.indexOf(" "))).trim()).isEmpty()) throw new InvalidInputException();
        else if (!line.contains(" /at ")) throw new NumberFormatException();
        userInput.add(inputCount, new Event((line.substring(1 + line.indexOf(" "), line.indexOf("/at"))), line.substring(line.indexOf("/at") + 4)));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput.get(inputCount - 1).getIcon() + "]" + "[" + userInput.get(inputCount - 1).getStatusIcon() + "] " + userInput.get(inputCount - 1).description + "(at: " + userInput.get(inputCount - 1).getAt() + ")");
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }
    public static void checkCommandValidity(Scanner input) throws InvalidInputException {
        line = input.nextLine().trim();
        if (line.equalsIgnoreCase("todo") || line.equalsIgnoreCase("deadline") || line.equalsIgnoreCase("event") || line.equalsIgnoreCase("delete") || line.equalsIgnoreCase("unmark") || line.equalsIgnoreCase("mark")) {
                line += ' ';
        }
        else if (!isList() && !isBye()  && !line.contains(" ")) {
            throw new InvalidInputException();
        } else if (!isList() && !isBye()  && line.contains(" ")) {
            if (!validFirstWord())
                throw new InvalidInputException();
            else if (getFirstWord().equalsIgnoreCase("list")) System.out.println("there should not be any arguments after 'list' command!");
            else if (getFirstWord().equalsIgnoreCase("bye")) System.out.println("there should not be any arguments after 'bye' command!");
        }
    }
    public static boolean validFirstWord() {
        return  getFirstWord().equalsIgnoreCase("delete") || getFirstWord().equalsIgnoreCase("unmark") ||
                getFirstWord().equalsIgnoreCase("list") || getFirstWord().equalsIgnoreCase("bye")   ||
                getFirstWord().equalsIgnoreCase("mark") || getFirstWord().equalsIgnoreCase("deadline") ||
                getFirstWord().equalsIgnoreCase("event") || getFirstWord().equalsIgnoreCase("todo");
    }
}
