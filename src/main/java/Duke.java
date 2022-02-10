import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static final String END_OF_SECTION = "___________________________________________________";

    static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo + "\nHello! I'm Duke\nWhat can I do for you?");
        System.out.println(END_OF_SECTION);
    }

    public static void main(String[] args) {
        ArrayList<Task> allTasks = new ArrayList<Task>();
        String line;
        Scanner in = new Scanner(System.in);

        printGreeting();

        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(END_OF_SECTION);
            String[] arrOfStr = line.split(" ", 2);
            if (arrOfStr[0].equals("list")) {
                if (allTasks.isEmpty()) {
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < allTasks.size(); ++i) {
                        System.out.println(String.format("%d.%s", i + 1, allTasks.get(i)));
                    }
                }
            } else if (arrOfStr[0].equals("todo")) {
                // toDo method
                try {
                    allTasks.add(new ToDo(arrOfStr[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!! The description of a todo cannot be empty");
                    System.out.println(END_OF_SECTION);
                    line = in.nextLine();
                    continue;
                }
                System.out.println(String.format("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                        allTasks.get(allTasks.size() - 1), allTasks.size()));
            } else if (arrOfStr[0].equals("deadline")) {
                // Deadline method
                String[] deadlineDescription = arrOfStr[1].split("/by");
                try {
                    allTasks.add(new Deadline(deadlineDescription[0], deadlineDescription[1]));
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Wrong input format. Please follow the following format:");
                    System.out.println("deadline [description] /by [date]");
                    System.out.println(END_OF_SECTION);
                    line = in.nextLine();
                    continue;
                }
                System.out.println(String.format("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                        allTasks.get(allTasks.size() - 1), allTasks.size()));
            } else if (arrOfStr[0].equals("event")) {
                // Event method
                String[] eventDescription = arrOfStr[1].split("/at");
                try {
                    allTasks.add(new Event(eventDescription[0], eventDescription[1]));
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Wrong input format. Please follow the following format:");
                    System.out.println("event [description] /at [time/date]");
                    System.out.println(END_OF_SECTION);
                    line = in.nextLine();
                    continue;
                }
                System.out.println(String.format("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                        allTasks.get(allTasks.size() - 1), allTasks.size()));
            } else if (arrOfStr[0].equals("mark")) {
                try {
                    allTasks.get(Integer.parseInt(arrOfStr[1]) - 1).markAsDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please input the correct index to mark it as done");
                    System.out.println(END_OF_SECTION);
                    line = in.nextLine();
                    continue;
                }
                System.out.println(String.format("Nice! I've marked this task as done:%n%s",
                        allTasks.get(Integer.parseInt(arrOfStr[1]) - 1)));
            } else if (arrOfStr[0].equals("unmark")) {
                try {
                    allTasks.get(Integer.parseInt(arrOfStr[1]) - 1).markAsDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please input the correct index to mark it as undone");
                    System.out.println(END_OF_SECTION);
                    line = in.nextLine();
                    continue;
                }
                System.out.println(String.format("Ok, I've marked this task as not done yet:%n%s",
                        allTasks.get(Integer.parseInt(arrOfStr[1]) - 1)));
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means");
            }
            System.out.println(END_OF_SECTION);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(END_OF_SECTION);
    }
}
