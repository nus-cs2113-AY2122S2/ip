import java.util.Scanner;

public class Duke {
    public static void linePrinter() {
        System.out.print("\t");
        for(int i = 0; i < 70; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2500");
    }

    public static void greeting() {
        linePrinter();
        System.out.println("\t" + " I have been waiting for you, Mister Winters");
        System.out.println("\t" + " Forgive my manners, call me the Duke. Now to business. " +
                "Weapons, ammunition, healing salves – anything you desire, I can provide.");
        linePrinter();

    }

    public static void printList(String[] items) {
        linePrinter();

        for (String item : items) {
            System.out.println("\t" + " " + item );
        }

        linePrinter();
    }

    public static void printMark(String markedItem) {
        if (markedItem == null) {
            echo("Ah...The item you choose doesn't exist on your list.");
        } else {
            linePrinter();
            System.out.println("\t" + " Aha! An interesting selection!");
            System.out.println("\t" + "   " + markedItem);
            linePrinter();
        }
    }

    public static void printUnmark(String unmarkedItem) {
        if (unmarkedItem == null) {
            echo("Ah...The item you choose doesn't exist on your list.");
        } else {
            linePrinter();
            System.out.println("\t" + " Ah, if only you had the funds...");
            System.out.println("\t" + "   " + unmarkedItem);
            linePrinter();
        }
    }

    public static void exitLine() {
        linePrinter();
        System.out.println("\t" + " Good day, then!");
        linePrinter();
    }

    public static void echo (String line) {
        linePrinter();
        System.out.println("\t" + " " + line);
        linePrinter();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;
        List list = new List();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        greeting();
        line = input.nextLine();

        while(!line.equalsIgnoreCase("bye")) {
            if (line.startsWith("Add ")) {
                list.addToList(line);
                echo(line.replace("Add", "Added") + " to the cart");
            } else if (line.startsWith("Mark ")) {
                String markedItem = list.mark(line);
                printMark(markedItem);
            } else if (line.startsWith("Unmark ")){
                String unmarkedItem = list.unmark(line);
                printUnmark(unmarkedItem);
            } else if (line.equals("List")){
                printList(list.listTheList());
            } else {
                echo(line);
            }

            line = input.nextLine();
        }

        exitLine();
    }
}
