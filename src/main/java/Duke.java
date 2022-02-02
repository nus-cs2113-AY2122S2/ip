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
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";

        System.out.println(greet);

        String instruction;
        String[] instructions = new String[100];

        for (int i = 0; i < 100; i++) { // can have 100 tasks
            Scanner in = new Scanner(System.in);
            //System.out.print("Type something: ");
            instruction = in.nextLine();

            Task t = new Task("read book");
            String[] arrOfStr = instruction.split(" ", 50);

            Task[] tasks = new Task[100];
            tasks[0] = new Deadline("return book", "Monday");
            Deadline d = new Deadline("return book", "holi");

            boolean isBye = instruction.equals(bye); //if true then exit
            boolean isList = instruction.equals(list);
            boolean isMark = arrOfStr[0].equals(mark);
            boolean isTodo = arrOfStr[0].equals(todo);
            boolean isDeadline = arrOfStr[0].equals(deadline);
            boolean isEvent = arrOfStr[0].equals(event);

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

            } else if (isTodo){
                System.out.println("Got it. I've added this task: ");
                System.out.print("  [T][ ] ");
                for(int k = 1; k < arrOfStr.length; k++){
                    System.out.print(arrOfStr[k] + " ");
                }
                System.out.println("");
                System.out.println("Now you have " + tasks.length + " tasks in the list.");
            } else if (isDeadline){
                System.out.println("Got it. I've added this task: ");
                d.getBy();
                System.out.println(d);
            } else {
                System.out.println("added: " + instruction);
            }
        }
    }
}
