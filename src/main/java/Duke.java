import java.util.Scanner;

public class Duke {

    public static void printList(String[] list, int counter){
        for(int i = 0; i < counter; i++){
            System.out.println(i + 1 + ". " + list[i]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String[] list=new String[100];
        int listCounter = 0;

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        while(!input.equalsIgnoreCase("bye")){
            // print list
            if (input.equalsIgnoreCase("list")){
                printList(list,listCounter);
            }
            else {
                list[listCounter] = input;
                listCounter++;
                System.out.println("added: "+input);
            }
            input = sc.nextLine();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
