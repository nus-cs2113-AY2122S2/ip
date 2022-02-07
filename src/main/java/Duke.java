import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.print("____________________\n"+
                          "Hello! I'm Duke\n"+
                          "What can I do for you?\n");
        boolean isToQuit = false;
        String input;

        while(!isToQuit){
            input = sc.nextLine();
            String commandWord = getCommand(input);
            String info = getInfo(input);

            switch(commandWord){
            case "todo":
                addTodo(info, tasks);
                break;
            case "deadline":
                addDeadline(info, tasks);
                break;
            case "event":
                addEvent(info, tasks);
                break;
            case "mark":
                mark(Integer.parseInt(info), tasks);
                break;
            case "unmark":
                unmark(Integer.parseInt(info), tasks);
                break;
            case "list":
                list(tasks);
                break;
            case "bye":
                isToQuit = true;
                break;
            default:
                System.out.println("Not a valid command");
                break;
            }
        }

        System.out.print("____________________\n"+
                "Bye. Hope to see you again soon!\n"+
                "____________________\n");
    }

    private static void addTodo(String info, ArrayList<Task> list){
        Task newTask = new Task(info);
        list.add(newTask);
        System.out.printf("Got it. I've added this task:\n" +
                "%s" +
                "Now you have %d in the list.\n", newTask, list.size());
    }

    private static void addDeadline(String input, ArrayList<Task> list){
        String by = input.substring(input.indexOf("/by")+4);
        String task = input.substring(0, input.indexOf("/by")-1);

        Deadline newDeadline = new Deadline(task, by);
        list.add(newDeadline);
        System.out.printf("Got it. I've added this task:\n" +
                "%s" +
                "Now you have %d in the list.\n", newDeadline, list.size());
    }

    private static void addEvent(String input, ArrayList<Task> list){
        String at = input.substring(input.indexOf("/at")+4);
        String task = input.substring(0, input.indexOf("/at")-1);

        Event newEvent = new Event(task, at);
        list.add(newEvent);
        System.out.printf("Got it. I've added this task:\n" +
                "%s" +
                "Now you have %d in the list.\n", newEvent, list.size());
    }

    private static void list(ArrayList<Task> list){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i< list.size(); i++){
            System.out.printf("%d. %s", i+1, list.get(i).toString());
        }
    }

    private static void mark(int index, ArrayList<Task> list){
        list.get(index-1).setDone(true);
        System.out.printf("Nice! I've marked this task as done:\n [X] %s\n", list.get(index-1).getDescription());
    }

    private static void unmark(int index, ArrayList<Task> list){
        list.get(index-1).setDone(false);
        System.out.printf("OK, I've marked this task as not done yet:\n [ ] %s\n", list.get(index-1).getDescription());
    }

    private static String getCommand(String input){
        if(input.contains(" ")){
            return input.substring(0, input.indexOf(" "));
        }else{
            return input;
        }
    }

    private static String getInfo(String input){
        if(input.contains(" ")){
            return input.substring(input.indexOf(" ")+1);
        }else{
            return null;
        }
    }
}
