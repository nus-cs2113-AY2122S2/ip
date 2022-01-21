import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void listToDo(List<String> tdList){
        int idx = 1;
        for(String todo: tdList){
            System.out.println(String.format("%d. %s",idx,todo));
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

        List<String> toDoList = new ArrayList<String>();

        Scanner in = new Scanner(System.in);
        while(true){
            String cmd = in.nextLine();
            if (cmd.equals("bye")){
                break;
            } else if (cmd.equals("list")){
                listToDo(toDoList);
            } else {
                toDoList.add(cmd);
                System.out.println(String.format("added: %s",cmd));
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
