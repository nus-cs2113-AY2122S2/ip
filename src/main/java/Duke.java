import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    private static final int MAX_TASK_SIZE = 100;
    private static Task[] userInput;
    private static int valIndex;
    private static int inputCount = 0;
    private static FileWriting fw;
    private static final String filePath = "data/data.txt";
    private static String line;
    public static void main(String[] args) throws IOException {
        printWelcomeMessage();
        userInput = new Task[MAX_TASK_SIZE];
        File f = new File(filePath);
        File folder = new File("data");
        if(!folder.exists())
            folder.mkdir();
        if(f.exists())
        fileReading(filePath);
        while(true) {
            Scanner input = new Scanner(System.in);
            //line = input.nextLine();
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
                    if (checkValidity()){
                        markAsDone();
                    } else {
                        System.out.println("Invalid Command!");
                    } //continue;
                } else if (isUnmark()) {
                    if (checkValidity()) {
                        markAsUndone();
                    } else {
                        System.out.println("Invalid Command!");
                    } //continue;
                } else if (isTodo()){
                    try{
                    addTodo();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    //continue;
                } else if (isDeadline()){
                    try {
                        addDeadline();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    //continue;
                } else if (isEvent()){
                    try {
                        addEvent();
                    } catch (InvalidInputException e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    //continue;
                }
            }
//            userInput[inputCount] = new Task(line);
//            inputCount++;
//            System.out.println("Add: " + line);
            File tempFile = new File("data/tempdata.txt");
            boolean a = tempFile.createNewFile();
            FileWriting fileWriting = new FileWriting();
            FileWriter writer;
            try {
                for (int i = 0; i < inputCount; i++) {
                    fileWriting.writeToFile("data/tempdata.txt", (i + 1) + ".[" + userInput[i].getIcon() + "] " + "[" + userInput[i].getStatusIcon() + "] " + userInput[i].description + System.lineSeparator());
                }
                f.delete();
                tempFile.renameTo(f);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
    public static void fileReading(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            var str = s.nextLine();
            if (str.charAt(3) == 'T') {
                userInput[inputCount] = new Todo(str.substring(11));
                if (str.charAt(7) == 'X')
                    userInput[inputCount].markAsDone();
            } else if (str.charAt(3) == 'D') {
                userInput[inputCount] = new Deadline(str.substring(11), str.substring(11));
                if (str.charAt(7) == 'X')
                    userInput[inputCount].markAsDone();
            } else if (str.charAt(3) == 'E') {
                userInput[inputCount] = new Deadline(str.substring(11), str.substring(11));
                if (str.charAt(7) == 'X')
                    userInput[inputCount].markAsDone();
            }
            inputCount++;
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
            System.out.println((i+1) +  ".[" +  userInput[i].getIcon() +"] " + "[" +  userInput[i].getStatusIcon() +"] "+ userInput[i].description);
        }
    }

    public static boolean isUnmark(){
        return (line.substring(0, line.indexOf(" "))).equalsIgnoreCase("unmark");
    }

    public static boolean isMark(){
        return (line.substring(0, line.indexOf(" "))).equalsIgnoreCase("mark");
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
        return (line.substring(0, line.indexOf(" "))).equalsIgnoreCase("Todo");
    }

    public static boolean isDeadline() {
        return (line.substring(0, line.indexOf(" "))).equalsIgnoreCase("Deadline");
    }

    public static boolean isEvent(){
        return (line.substring(0, line.indexOf(" "))).equalsIgnoreCase("Event");
    }

    public static boolean isBye(){
        return line.equalsIgnoreCase("bye");
    }
    public static boolean checkValidity(){
        return Integer.parseInt(line.substring(line.indexOf(" ") + 1)) <= inputCount;
    }
    public static boolean isList() {
        return line.equals("list");
    }
    public static void addTodo() throws InvalidInputException {
        if ((line.substring(1 + line.indexOf(" "))).trim().isEmpty()) throw new InvalidInputException();
        userInput[inputCount] = new Todo((line.substring(1 + line.indexOf(" "))));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    public static void addDeadline() throws InvalidInputException {
        if ((line.substring(1 + line.indexOf(" "))).trim().isEmpty()) throw new InvalidInputException();
        userInput[inputCount] = new Deadline((line.substring(1 + line.indexOf(" "), line.indexOf("/by"))),line.substring(line.indexOf("/by") + 4));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description + "(by: " + userInput[inputCount - 1].getBy() + ")");
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }

    public static void addEvent() throws InvalidInputException {
        if (((line.substring(1 + line.indexOf(" "))).trim()).isEmpty()) throw new InvalidInputException();
        userInput[inputCount] = new Event((line.substring(1 + line.indexOf(" "), line.indexOf("/at"))),line.substring(line.indexOf("/at") + 4));
        inputCount++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description + "(at: " + userInput[inputCount - 1].getAt() + ")");
        System.out.println("Now you have " + inputCount + " tasks in the list.");
    }
    public static void checkCommandValidity(Scanner input) throws InvalidInputException {
        line = input.nextLine();
        if (line.equalsIgnoreCase("todo") || line.equalsIgnoreCase("deadline") || line.equalsIgnoreCase("event"))
            line+= ' ';
        if (!isList() && !isBye() && !line.contains(" ")) {
            throw new InvalidInputException();
        }
    }


}
