import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void linePrinter() {
        for(int i = 0; i < 70; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2500");
    }

    public static void greeting() {
        linePrinter();
        System.out.println(" I have been waiting for you, Mister Winters");
        System.out.println(" Forgive my manners, call me the Duke. Now to business. " +
                "Weapons, ammunition, healing salves – anything you desire, I can provide.");
        linePrinter();

    }

    public static void printList(String[] items) {
        linePrinter();

        for (String item : items) {
            System.out.println(item);
        }

        linePrinter();
    }

    public static void exitLine() {
        linePrinter();
        System.out.println(" Good day, then!");
        linePrinter();
    }

    public static void echo (String line) {
        linePrinter();
        System.out.println(line);
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
            if (line.startsWith("Add")) {
                list.addToList(line);
                echo(line.replace("Add", "Added") + " to the cart");
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
