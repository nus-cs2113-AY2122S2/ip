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
        String[] userinput = new String[100];
        int inputCount = 0;
        while(true) {
            String line;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            if (line.equals("list")){
                for (int i = 0; i < inputCount; i++) {
                    System.out.println((i+1) + ". " + userinput[i]);
                }
                continue;
            }
            userinput[inputCount] = line;
            inputCount++;
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println("Add: " + line);
        }
    }
}
