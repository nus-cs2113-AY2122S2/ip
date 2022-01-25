public class DialogGenerator {
    public void printLine(){
        System.out.println("\t____________________________________________________________");
    }

    public void greeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(""+logo);
        this.printLine();
        System.out.println("\tHello I'm Duke.");
        System.out.println("\tWhat can I do for you?");
        this.printLine();
        System.out.println();
    }

    public void sayGoobye(){
        this.printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        this.printLine();
    }

    public void displayListWithStatus(Task[] allAbilities, int abilitiesCount){
        this.printLine();
        System.out.println("Nice! I've marked this task as done:");
        for(int i = 1; i <= abilitiesCount; i++){
            System.out.print(i);
            System.out.print(". ");
            allAbilities[i-1].printTask();
        }
        this.printLine();
    }

    public void markTask(Task t){
        t.markAsDone();
        this.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("\t\t");
        t.printTask();
        this.printLine();
    }

    public void unmarkTask(Task t){
        t.markAsUnDone();
        this.printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("\t\t");
        t.printTask();
        this.printLine();
    }
}
