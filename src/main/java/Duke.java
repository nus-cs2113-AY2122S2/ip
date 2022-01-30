import java.awt.*;
import java.util.Scanner;

public class Duke {

    public static void addList(Task[] list, int listCounter, String line) {
        list[listCounter] = new Task(line);
    }

    public static void printList(Task[] list, int listCounter) {
        for (int i = 0; i < listCounter; i++) {
            int index = i + 1;
            System.out.println(index + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?\n");
        Task[] list = new Task[100];
        int listCounter = 0;
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String exitMessage = "bye";
            String printMessage = "list";
            String markMessage = "mark";
            String unmarkMessage = "unmark";
            if(line.equals(exitMessage)){
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }
            if(line.equals(printMessage)){
                printList(list, listCounter);
                continue;
            }
            if(line.split(" ")[0].equals(markMessage)) {
                list[Integer.parseInt(line.split(" ")[1]) - 1].markAsDone();
                continue;
            }
            if(line.split(" ")[0].equals(unmarkMessage)) {
                list[Integer.parseInt(line.split(" ")[1]) - 1].markAsUndone();
                continue;
            }
            addList(list, listCounter, line);
            listCounter++;
            System.out.println("added: " + line);
        }
    }
}
