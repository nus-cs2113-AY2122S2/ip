import java.util.Scanner;

public class Duke {

    public static void addList(String[] list, int listCounter, String line) {
        list[listCounter] = line;
    }

    public static void printList(String[] list, int listCounter) {
        for (int i = 0; i < listCounter; i++) {
            int index = i + 1;
            System.out.println(index + ". " + list[i]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?\n");
        String[] list = new String[100];
        int listCounter = 0;
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String exitMessage = "bye";
            String printMessage = "list";
            if(line.equals(exitMessage)){
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }
            if(line.equals(printMessage)){
                printList(list, listCounter);
                continue;
            }
            addList(list, listCounter, line);
            listCounter++;
            System.out.println("added: " + line);
        }
    }
}
