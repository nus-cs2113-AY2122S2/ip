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

    public static void sayGoobye(){
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

    //level 3

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;

            System.out.println("\t____________________________________________________________");
            System.out.println("\tadded: " + description);
            System.out.println("\t____________________________________________________________");
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone(){
            this.isDone = true;
        }

        public void markAsUnDone(){
            this.isDone = false;
        }

        public void printTask(){
            System.out.println("[" + getStatusIcon() + "] " + this.description);
        }
    }

    public static void displayListWithStatus(Task[] allAbilities, int abilitiesCount){
        System.out.println("\t____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        for(int i = 1; i <= abilitiesCount; i++){
            System.out.print(i);
            System.out.print(". ");
            allAbilities[i-1].printTask();
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {

        String command;
        String[] abilities = new String[100];
        Task[] allAbilities = new Task[100];
        int abilitiesCount = 0;
        boolean notQuit = true;

        greeting();

        while (notQuit) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();

            switch (command.split(" ")[0]) {
                case "bye": {
                    sayGoobye();
                    notQuit = false;
                }
                    break;

                case "mark": {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task t = allAbilities[index];
                    t.markAsDone();
                    System.out.println("\t____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.print("\t\t");
                    t.printTask();
                    System.out.println("\t____________________________________________________________");
                }
                    break;

                case "unmark": {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task t = allAbilities[index];
                    t.markAsUnDone();
                    System.out.println("\t____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.print("\t\t");
                    t.printTask();
                    System.out.println("\t____________________________________________________________");
                }
                    break;

                case "list": {
                    displayListWithStatus(allAbilities, abilitiesCount);
                }
                    break;

                default: {
                    Task t = new Duke().new Task(command);
                    allAbilities[abilitiesCount] = t;
                    abilitiesCount += 1;
                }
                    break;
            }

            
        }

    }
}
