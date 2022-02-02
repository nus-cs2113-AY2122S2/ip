import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("================================================");

        Task[] tasks = new Task[100];
        int i = 0;
        int marker;
        String userInput;
        Scanner in = new Scanner(System.in);
        do{
            userInput = in.nextLine();

            if(userInput.startsWith("mark")){
                marker = Integer.parseInt(userInput.substring(5));
                tasks[marker-1].markAsDone();
                System.out.println(tasks[marker-1].printTask());
            }else if(userInput.startsWith("unmark")){
                marker = Integer.parseInt(userInput.substring(7));
                tasks[marker-1].markAsUndone();
                System.out.println(tasks[marker-1].printTask());
            }else{
                switch(userInput){
                case "bye":
                    System.out.println("================================================");
                    break;
                case "list":
                    System.out.println("================================================");
                    System.out.println("Here are the tasks in your list:");
                    for(int j=0; j<i; j++){
                        System.out.println((j+1) + "." + tasks[j].printTask());
                    }
                    System.out.println("================================================");
                    break;
                default:
                    System.out.println("================================================");
                    tasks[i] = addTask(userInput);
                    System.out.println(tasks[i].printTask());
                    System.out.println("Now you have " + (i+1) + " tasks in the list.");
                    System.out.println("================================================");
                    i++;
                    break;
                }
            }
        }while(!userInput.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static Task addTask(String userInput) {
        if (userInput.startsWith("todo")) {
            return new Todo(userInput.substring(5));
        } else if (userInput.startsWith("deadline")) {
            return new Deadline(userInput.substring(9));
        }else{
            return new Event(userInput.substring(6));
        }
    }
}
