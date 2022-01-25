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
        do{
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if(userInput.startsWith("mark")){
                marker = Integer.parseInt(userInput.substring(5));
                tasks[marker-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[marker-1].getStatusIcon() + tasks[marker-1].getDescription());
            }else if(userInput.startsWith("unmark")){
                marker = Integer.parseInt(userInput.substring(7));
                tasks[marker-1].markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[marker-1].getStatusIcon() + tasks[marker-1].getDescription());
            }else{
                switch(userInput){
                case "bye":
                    System.out.println("================================================");
                    break;
                case "list":
                    System.out.println("================================================");
                    System.out.println("Here are the tasks in your list:");
                    for(int j=0; j<i; j++){
                        System.out.println((j+1) + "." + tasks[j].getStatusIcon() + tasks[j].getDescription());
                    }
                    System.out.println("================================================");
                    break;
                default:
                    tasks[i] = new Task(userInput);
                    i++;
                    System.out.println("================================================");
                    System.out.println("added: " + userInput);
                    System.out.println("================================================");
                    break;
                }
            }


        }while(!userInput.equals("bye"));


        System.out.println("Bye. Hope to see you again soon!");
    }
}
