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
        //String[] userinput = new String[100];
        Task[] userinput = new Task[100];
        int inputCount = 0;
        while(true) {
            String line;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            if (line.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputCount; i++) {
                    System.out.println((i+1) + ".[" +  userinput[i].getStatusIcon() +"] "+ userinput[i].description);
                }
                continue;
            }

            if (line.contains(" ")){
                if ((line.substring(0, line.indexOf(" "))).equals("mark")) {
                    if (Integer.parseInt(line.substring(line.indexOf(" ") + 1)) <= inputCount){
                        userinput[Integer.parseInt(line.substring(line.indexOf(" ") + 1)) - 1].markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("[" + userinput[Integer.parseInt(line.substring(line.indexOf(" ") + 1)) - 1].getStatusIcon() + "] " + userinput[Integer.parseInt(line.substring(line.indexOf(" ") + 1)) - 1].description);
                        continue;
                    }
                    else {
                        System.out.println("Invalid Command!");
                        continue;
                    }
                }
                else if ((line.substring(0, line.indexOf(" "))).equals("unmark")) {
                    if (Integer.parseInt(line.substring(line.indexOf(" ") + 1)) <= inputCount) {
                        userinput[Integer.parseInt(line.substring(line.indexOf(" ") + 1)) - 1].markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("[" + userinput[Integer.parseInt(line.substring(line.indexOf(" ") + 1)) - 1].getStatusIcon() + "] " + userinput[Integer.parseInt(line.substring(line.indexOf(" ") + 1)) - 1].description);
                        continue;
                    }
                    else {
                        System.out.println("Invalid Command!");
                        continue;
                    }
                }
            }
            userinput[inputCount] = new Task(line);
            inputCount++;
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println("Add: " + line);
        }
    }
}
