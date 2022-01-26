import java.util.Scanner;
public class Duke {
    public static void listTasks(Task[] list, int i){
        System.out.println("____________________________________________________________");
        for (int j = 0; j < i; j++) {
            if (list[j].getMarked()){
                System.out.println(j + 1 + ". [X] " + list[j].getName());
            }else{
                System.out.println(j + 1 + ". [ ] " + list[j].getName());
            }
        }
        System.out.println("____________________________________________________________");
    }
    public static void updateMarkTask(Task[] list, int i, String line, boolean mark){
        int index;
        try{
            index = Integer.parseInt(line.split(" ")[1]) - 1;
        }catch (NumberFormatException e){
            System.out.println("____________________________________________________________");
            System.out.println(" Error: Invalid index (Not an integer).");
            System.out.println("____________________________________________________________");
            return;
        }
        if (index >= i){
            System.out.println("____________________________________________________________");
            System.out.println(" Error: Invalid index (Out of bounds).");
            System.out.println("____________________________________________________________");
            return;
        }
        System.out.println("____________________________________________________________");
        if (mark){
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("  [X] " + list[index].getName());
        }else{
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + list[index].getName());
        }
        System.out.println("____________________________________________________________");
        list[index].setMarked(mark);
    }
    public static int addNewTask(Task[] list, int i, String line){
        list[i] = new Task(line, false);
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + line);
        System.out.println("____________________________________________________________");
        return i + 1;
    }
    public static void waitForInput(Task[] list, Scanner in){
        int i = 0;
        while (true){
            String line = in.nextLine();
            if (line.equals("bye")){
                break;
            }
            if (line.equals("list")) {
                listTasks(list, i);
            }else if (line.split(" ")[0].equals("mark")){
                updateMarkTask(list, i, line, true);
            }else if (line.split(" ")[0].equals("unmark")){
                updateMarkTask(list, i, line, false);
            }else{
                i = addNewTask(list, i, line);
            }

        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke\n What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] list = new Task[100];
        Scanner in = new Scanner(System.in);
        waitForInput(list, in);
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
