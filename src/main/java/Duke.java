import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String greet = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";
        String bye = "bye";
        String list = "list";
        String mark = "mark";
        System.out.println(greet);

        String instruction;
        String[] instructions = new String[100];

        for (int i = 0; i < 100; i++) { // can have 100 tasks
            Scanner in = new Scanner(System.in);
            //System.out.print("Type something: ");
            instruction = in.nextLine();

            Task t = new Task("read book");
            String[] arrOfStr = instruction.split(" ", 2);

            boolean isBye = instruction.equals(bye); //if true then exit
            boolean isList = instruction.equals(list);
            boolean isMark = arrOfStr[0].equals(mark);
            instructions[i] = instruction;
            String instructionNum = "";

            if (isBye) {
                System.out.println(exit);
                break;
            } else if (isList) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < 100 && instructions[j + 1] != null; j++) {
                    System.out.println((j + 1) + ". " + "[" + t.getStatusIcon() + "]" + instructions[j]);
                }
            } else if (isMark) {
                System.out.println("Nice! I've marked this task as done:");
                instructionNum = arrOfStr[1];
                t.setStatusIcon(true);
                System.out.println("   [" + t.getStatusIcon() + "]" + instructions[Integer.parseInt(instructionNum) - 1]);

            } else {
                System.out.println("added: " + instruction);
            }
        }
    }
}
