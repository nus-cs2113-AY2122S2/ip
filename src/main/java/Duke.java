import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String indentation = "._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._._.._._.";
        System.out.println(indentation);
        System.out.println("Hello! I'm Tom");
        System.out.println("What can I do for you?");
        System.out.println(indentation);

        //public static void listItems(String [100] listName) {
            String list[]=new String[100];
            for(int i = 0; i < 100; i++){
                Scanner input1 = new Scanner(System.in);
                String command = input1.nextLine();
                list[i] = command;
                if (command.equals("list")){
                    for(int j = 0; j < i; j++ ) {
                        System.out.println((j + 1) + "." + "[ ]" + list[j]);
                    }
                    if(command.equals("mark")) {
                        String indexPositioning = command.substring(4);
                        int index  = Integer.parseInt(indexPositioning);

                    }
                    }else if(command.equals("bye")){
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    }else{
                        //continue;
                        System.out.println("added:" + command);
                    }

                }
                }
            }
        /*
        while(true) {
            Scanner input1 = new Scanner(System.in);
            String command = input1.nextLine();
            System.out.println(indentation);
            if (command.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            break;
        }else{
                    System.out.println(command);
                    System.out.println(indentation);
            }
        }
    }
        //System.out.println(indentation);
}

         */
