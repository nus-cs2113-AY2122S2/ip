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
            String main_cmd = cmd[0];
            if (cmd.length == 0){
                System.out.println("Please type something\n");
                continue;
            }
            if (main_cmd.equals("bye")){
                break;
            } else if (main_cmd.equals("list")){
                listToDo(toDoList);
            } else if (main_cmd.equals("mark")) {
                System.out.println("Nice! I've marked this task as done: ");
                int idx = Integer.parseInt(cmd[1]) - 1;
                // add try except later
                toDoList.get(idx).markDone();
                System.out.println(toDoList.get(idx).getStatus());
            } else if (main_cmd.equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int idx = Integer.parseInt(cmd[1]) - 1;
                toDoList.get(idx).unmarkDone();
                System.out.println(toDoList.get(idx).getStatus());

            } else {
                System.out.println("Got it. I've added this task: ");
                Task newTask;
                String params = input.substring(main_cmd.length());
                if (main_cmd.equals("todo")) {
                    newTask = new ToDo(params);
                } else if (main_cmd.equals("deadline")) {
                    String[] params2 = params.split("/");
                    newTask = new Deadline(params2[0],params2[1]);
                } else {
                    String[] params2 = params.split("/");
                    newTask = new Event(params2[0],params2[1]);
                }
                toDoList.add(newTask);
                System.out.println(newTask.getStatus());
//                System.out.println(String.format("added task: %s",cmd));
                System.out.println(String.format("Now you have %d tasks in the list.",toDoList.size()));
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
