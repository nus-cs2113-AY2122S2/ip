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
                    addItem(input, taskList);
                    break;
            }
        }

        System.out.print("____________________\n"+
                "Bye. Hope to see you again soon!\n"+
                "____________________\n");
    }

    private static void addItem(String input, ArrayList<Task> list){
        Task newTask = new Task(input);
        list.add(newTask);
        System.out.printf("added: %s\n", input);
    }

    private static void list(ArrayList<Task> list){
        for(int i = 0; i< list.size(); i++){
            if(list.get(i).isDone()){
                System.out.printf("%d.[X] %s\n", i+1, list.get(i).getTask());
            }else{
                System.out.printf("%d.[ ] %s\n", i+1, list.get(i).getTask());
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
