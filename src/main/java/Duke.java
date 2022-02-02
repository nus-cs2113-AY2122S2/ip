import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
         System.out.println("---------------------");
        System.out.println(("Hello! I'm Duke"));
        System.out.println(("What can i do for you?"));
        System.out.println("---------------------");
        String[] strings = new String[100];

        Boolean run = true; 
        int arrayIndex = 0; 
        while (run) { 
            Scanner sc = new Scanner(System.in);
            String anyString = sc.nextLine();

            if (anyString.equals("bye")) { 
                System.out.println("Bye! Hope to see you again.");
                run = false; 
            }

            else if (anyString.equals("list")){
                for (int i = 0; i<arrayIndex; i++) {
                    System.out.println(strings[i]);
                }
            }

            else {
                strings[arrayIndex] = anyString; 
                arrayIndex++; 
            }
        }
        
           
      
    }
}
