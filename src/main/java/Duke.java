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
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.print("____________________\n"+
                          "Hello! I'm Duke\n"+
                          "What can I do for you?\n");
        boolean userToQuit = false;
        String input, startOfInput;
        while(!userToQuit){
            input = sc.nextLine();
            if(input.contains(" ")){
                startOfInput = input.substring(0, input.indexOf(" "));
            }else{
                startOfInput = input;
            }
            switch(startOfInput){
                case "todo":
                    addTodo(input, taskList);
                    break;
                case "deadline":
                    addDeadline(input, taskList);
                    break;
                case "event":
                    addEvent(input, taskList);
                    break;
                case "mark":
                    mark(Integer.parseInt(input.substring(input.indexOf(" ")+1)), taskList);
                    break;
                case "unmark":
                    unmark(Integer.parseInt(input.substring(input.indexOf(" ")+1)), taskList);
                    break;
                case "list":
                    list(taskList);
                    break;
                case "bye":
                    userToQuit = true;
                    break;
                default:
                    // add default prompt
                    break;
            }
        }

        System.out.print("____________________\n"+
                "Bye. Hope to see you again soon!\n"+
                "____________________\n");
    }

    private static void addTodo(String input, ArrayList<Task> list){
        Task newTask = new Task(input);
        list.add(newTask);
        System.out.printf("Got it. I've added this task:\n" +
                "%s" +
                "Now you have %d in the list.\n", newTask, list.size());
    }

    private static void addDeadline(String input, ArrayList<Task> list){
        String by = input.substring(input.indexOf("/by")+4);
        String task = input.substring(input.indexOf(" "), input.indexOf("/by")-1);

        Deadline newDeadline = new Deadline(task, by);
        list.add(newDeadline);
        System.out.printf("Got it. I've added this task:\n" +
                "%s" +
                "Now you have %d in the list.\n", newDeadline, list.size());
    }

    private static void addEvent(String input, ArrayList<Task> list){
        String at = input.substring(input.indexOf("/at")+4);
        String task = input.substring(input.indexOf(" "), input.indexOf("/at")-1);

        Event newEvent = new Event(task, at);
        list.add(newEvent);
        System.out.printf("Got it. I've added this task:\n" +
                "%s" +
                "Now you have %d in the list.\n", newEvent, list.size());
    }

    private static void list(ArrayList<Task> list){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i< list.size(); i++){
            if(list.get(i).isDone()){
                System.out.printf("%d. %s", i+1, list.get(i).toString());
            }else{
                System.out.printf("%d. %s", i+1, list.get(i).toString());
            }
        }
    }

    private static void mark(int index, ArrayList<Task> list){
        list.get(index-1).setDone(true);
        System.out.printf("Nice! I've marked this task as done:\n [X] %s\n", list.get(index-1).getTask());
    }

    private static void unmark(int index, ArrayList<Task> list){
        list.get(index-1).setDone(false);
        System.out.printf("OK, I've marked this task as not done yet:\n [ ] %s\n", list.get(index-1).getTask());
    }
}
