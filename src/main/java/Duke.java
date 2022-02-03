import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
         System.out.println("---------------------");
        System.out.println(("Hello! I'm Duke"));
        System.out.println(("What can i do for you?"));
        System.out.println("---------------------");
        Task[] taskList = new Task[100];
        Boolean run = true; 
        int arrayIndex = 0; 
        while (run) { 
            Scanner sc = new Scanner(System.in);
            String anyString = sc.nextLine();

            if (anyString.equals("bye")) { 
                System.out.println("Bye! Hope to see you again.");
                run = false; 
            }
            else if (anyString.matches("mark \\d+")){
                String[] arrOfStr = anyString.split(" ", 0);
                String indexValue = arrOfStr[1]; 
                int indexValue2 = Integer.parseInt(indexValue) - 1;
                taskList[indexValue2].markTask(); 
                
            }

            else if (anyString.startsWith("todo")) {
                String desc = anyString.substring(5);
                taskList[arrayIndex] = new Task(desc);
                arrayIndex++; 
            }

            else if (anyString.startsWith("deadline")){
                int index = anyString.indexOf("/"); 
                String desc = anyString.substring(9,index);
                index++;
                String by = anyString.substring(index);
                taskList[arrayIndex] = new Deadline(desc,by); 
                arrayIndex++;
            }

            else if (anyString.startsWith("event")){
                int index = anyString.indexOf("/"); 
                String desc = anyString.substring(6,index);
                index++;
                String by = anyString.substring(index);
                taskList[arrayIndex] = new Event(desc,by); 
                arrayIndex++;
            }
            
            else if (anyString.equals("list")){
                System.out.println("Here are the tasks in your list : ");
                for (int i = 0; i<arrayIndex; i++) {
                    System.out.println(i+1 + "." + taskList[i].toString());
                }
            }

        

           
        }
        
           
      
    }
}
