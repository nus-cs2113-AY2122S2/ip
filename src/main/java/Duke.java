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
        System.out.println(greet);

        String instruction;


        for(int i = 0; i < 99999; i++) { // can have 99999 echoes
            Scanner in = new Scanner(System.in);
            //System.out.print("Type something: ");
            instruction = in.nextLine();
            
            boolean isSame = instruction.equals(bye); //if true then exit

            if(isSame) {
                System.out.println(exit);
                break;
            } else {
                System.out.println(instruction);
            }
        }
    }
}
