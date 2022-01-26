import java.util.Scanner;

public class Main {

    public static void greet() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Hello! I'm Alexis, your trusty helper");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------------------------");
    }

    public static void exit() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-----------------------------------------------------");
    }

    public static void displayList(Task[] list, int listSize) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listSize; i++) {
            System.out.println((i+1) + ".[" + list[i].getStatusIcon() + "] " + list[i].returnDescription());
        }
        System.out.println("-----------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int listCounter = 0;
        Task[] list = new Task[100];

        greet();
        String input = in.nextLine();
        while (!input.equals("bye")) {

            String[] arrOfInput = input.split(" ");

            if (input.equals("list")) {
                displayList(list, listCounter);

            }else if (arrOfInput[0].equals("mark")) {
                int taskNumber = Integer.parseInt(arrOfInput[1]) - 1;
                list[taskNumber].markAsDone();

            }else if (arrOfInput[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(arrOfInput[1]) - 1;
                list[taskNumber].markAsUndone();

            }else{
                list[listCounter] = new Task(input);
                listCounter++;
            }
            input = in.nextLine();
        }
        exit();

    }
}
