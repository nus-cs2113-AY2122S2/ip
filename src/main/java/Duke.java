import java.util.Scanner;

public class Duke {

    //level 0

    public static void greeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(""+logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Duke.");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    //level 1

    public static void echoCommand(String command){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t"+command);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void sayGoobye(String command){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    //level 2

    public static String[] addAbility(String command, String[] abilities, int abilitiesCount){
        abilities[abilitiesCount] = command;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + command);
        System.out.println("\t____________________________________________________________");

        return abilities;
    }

    public static void displayList(String[] abilities, int abilitiesCount){
        System.out.println("\t____________________________________________________________");
        for(int i = 0; i < abilitiesCount; i++){
            System.out.println(String.valueOf(i + 1) + ". " + abilities[i]);
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {

        String command;
        String[] abilities = new String[100];
        int abilitiesCount = 0;

        greeting();
        while (true) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();

            if (command.contentEquals("bye")){
                sayGoobye(command);
                break;
            }
            else if (command.contentEquals("list")){
                displayList(abilities, abilitiesCount); //level 2
            }
            else {
                //echoCommand(command); //for level 1
                abilities = addAbility(command, abilities, abilitiesCount); //level 2
                abilitiesCount += 1;


            }

        }

    }
}
