import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Task[] userInput = new Task[100];
        int inputCount = 0;
        while(true) {
            String line;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            if (line.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputCount; i++) {
                    System.out.println((i+1) +  ".[" +  userInput[i].getIcon() +"] " + "[" +  userInput[i].getStatusIcon() +"] "+ userInput[i].description);
                }
                continue;
            }

            if (line.contains(" ")){
                //int valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                if ((line.substring(0, line.indexOf(" "))).equals("mark")) {
                    int valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                    if (valIndex <= inputCount){
                        userInput[valIndex - 1].markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("[" + userInput[valIndex - 1].getStatusIcon() + "] " + userInput[valIndex - 1].description);
                    }
                    else {
                        System.out.println("Invalid Command!");
                    }
                    continue;
                }
                else if ((line.substring(0, line.indexOf(" "))).equals("unmark")) {
                    int valIndex = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                    if (valIndex <= inputCount) {
                        userInput[valIndex - 1].markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("[" + userInput[valIndex - 1].getStatusIcon() + "] " + userInput[valIndex - 1].description);
                    }
                    else {
                        System.out.println("Invalid Command!");
                    }
                    continue;
                }
                else if ((line.substring(0, line.indexOf(" "))).equals("Todo")){
                    userInput[inputCount] = new Todo((line.substring(1 + line.indexOf(" "))));
                    inputCount++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description);
                    System.out.println("Now you have " + inputCount + " tasks in the list.");
                    continue;
                }
                else if ((line.substring(0, line.indexOf(" "))).equals("Deadline")){
                    userInput[inputCount] = new Deadline((line.substring(1 + line.indexOf(" "), line.indexOf("/by"))),line.substring(line.indexOf("/by") + 4));
                    inputCount++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description + "(by: " + userInput[inputCount - 1].getBy() + ")");
                    System.out.println("Now you have " + inputCount + " tasks in the list.");
                    continue;
                }
                else if ((line.substring(0, line.indexOf(" "))).equals("Event")){
                    userInput[inputCount] = new Event((line.substring(1 + line.indexOf(" "), line.indexOf("/at"))),line.substring(line.indexOf("/at") + 4));
                    inputCount++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("[" + userInput[inputCount - 1].getIcon() + "]" + "[" + userInput[inputCount - 1].getStatusIcon() + "] " + userInput[inputCount - 1].description + "(at: " + userInput[inputCount - 1].getAt() + ")");
                    System.out.println("Now you have " + inputCount + " tasks in the list.");
                    continue;
                }
            }
            userInput[inputCount] = new Task(line);
            inputCount++;
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println("Add: " + line);
        }
    }
}
