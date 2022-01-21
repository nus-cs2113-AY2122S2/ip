import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void listToDo(List<Task> tdList){
        System.out.println("Here are the tasks in your list:");
        int idx = 1;
        for(Task todo: tdList){
            System.out.println(String.format("%d. %s",idx,todo.getStatus()));
            idx += 1;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm \n" + logo);
        System.out.println("What can I do for you?\n");

        List<Task> toDoList = new ArrayList<Task>();

        Scanner in = new Scanner(System.in);
        while(true){
            String input = in.nextLine();
            String[] cmd = input.split(" ");
            if (cmd.length == 0){
                System.out.println("Please type something\n");
                continue;
            }
            if (cmd[0].equals("bye")){
                break;
            } else if (cmd[0].equals("list")){
                listToDo(toDoList);
            } else if (cmd[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done: ");
                int idx = Integer.parseInt(cmd[1]) - 1;
                // add try except later
                toDoList.get(idx).markDone();
                System.out.println(toDoList.get(idx).getStatus());
            } else if (cmd[0].equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int idx = Integer.parseInt(cmd[1]) - 1;
                toDoList.get(idx).unmarkDone();
                System.out.println(toDoList.get(idx).getStatus());

            } else {
                toDoList.add(new Task(cmd[0]));
                System.out.println(String.format("added: %s",cmd));
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
