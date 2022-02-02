import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String greet = "Hello! I'm Duke\n" +
                "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";
        String bye = "bye";
        String list = "list";
        System.out.println(greet);

        String instruction;
        String[] instructions = new String[100];

        for (int i = 0; i < 100; i++) { // can have 100 tasks
            Scanner in = new Scanner(System.in);
            //System.out.print("Type something: ");
            instruction = in.nextLine();

            boolean isBye = instruction.equals(bye); //if true then exit
            boolean isList = instruction.equals(list);
            instructions[i] = instruction;
            String instructionNum = "";

            Task t = new Task("read book");
            String[] arrOfStr = instruction.split(" ", 2);


            if (isBye) {
                System.out.println(exit);
                break;
            } else if (isList) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < 100 && instructions[j + 1] != null; j++) {
                    /*if(Integer.parseInt(instructionNum) == (j + 1)){
                        t.setStatusIcon(true);
                    }*/
                    System.out.println((j + 1) + ". " + "[" + t.getStatusIcon() + "]" + instructions[j]);
                }
            } else if (arrOfStr[0] == "mark") {
                System.out.println("Nice! I've marked this task as done:");
                instructionNum = arrOfStr[1];
                t.setStatusIcon(true);

            } else {
                //instructions[i] = instruction;
                System.out.println("added: " + instruction);
            }
        }
    }
}
