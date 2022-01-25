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

        String[] tasks = new String[100];
        int i = 0;
        String userInput;
        do{
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if(userInput.equals("bye")){
                System.out.println("================================================");
                break;
            }else if(userInput.equals("list")){
                System.out.println("================================================");
                for(int j=0; j<i; j++){
                    System.out.println((j+1) + ". " + tasks[j]);
                }
                System.out.println("================================================");
            }else{
                tasks[i] = userInput;
                i++;
                System.out.println("================================================");
                System.out.println("added: " + userInput);
                System.out.println("================================================");
            }
        }while(true);


        System.out.println("Bye. Hope to see you again soon!");
    }
}
