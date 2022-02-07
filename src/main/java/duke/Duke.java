package duke;

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) throws IndexOutOfBoundsException{
        System.out.println("---------------------");
        System.out.println(("Hello! I'm Duke"));
        System.out.println(("What can i do for you?"));
        System.out.println("---------------------");
        Task[] taskList = new Task[100];
        Boolean continueProgram = true; 
        int arrayIndex = 0; 
        DukeException dukeEx = new DukeException();
        while (continueProgram) { 
            Scanner sc = new Scanner(System.in);
            String inputString = sc.nextLine();

            if (inputString.equals("bye")) { 
                System.out.println("Bye! Hope to see you again.");
                continueProgram = false; 
            }
            else if (inputString.matches("mark \\d+")){

                try {
                    String[] arrOfStr = inputString.split(" ", 0);
                    String indexValue = arrOfStr[1]; 
                    int indexValue2 = Integer.parseInt(indexValue) - 1;
                    //index given is out of array size
                    if (indexValue2 > arrayIndex || indexValue2 < 0){
                        throw new IndexOutOfBoundsException();
                    }
                    taskList[indexValue2].markTask(); 
                }

                catch (IndexOutOfBoundsException e){
                    dukeEx.wrongIndex();
                }
            }

            else if (inputString.matches("unmark \\d+")){

                try {
                    String[] arrOfStr = inputString.split(" ", 0);
                    String indexValue = arrOfStr[1]; 
                    int indexValue2 = Integer.parseInt(indexValue) - 1;
                    if (indexValue2 > arrayIndex || indexValue2 < 0){
                        throw new IndexOutOfBoundsException();
                    }
                    taskList[indexValue2].unmarkTask(); 
                }

                catch (IndexOutOfBoundsException e){
                    dukeEx.wrongIndex();
                }
            }

            else if (inputString.startsWith("todo")) {

                try { 
                    String desc = inputString.substring(5); 
                    if (desc.isEmpty() || desc.isBlank()) {
                        throw new IndexOutOfBoundsException();
                    }
                    System.out.println("Got it. I've added this task:");
                    taskList[arrayIndex] = new Todo(desc);
                    System.out.println(taskList[arrayIndex].toString());
                    arrayIndex++; 
                    System.out.println("Now you have " + arrayIndex + " tasks in the list.");
                }

                catch (IndexOutOfBoundsException e){
                    dukeEx.missingDescription("todo");
                }
            }

            else if (inputString.startsWith("deadline")){

                try {
                    
                    int index = inputString.indexOf("/"); 
                    String desc = inputString.substring(9,index);
                    if (desc.isEmpty() || desc.isBlank()) {
                        throw new IndexOutOfBoundsException();
                    }
                    index++;
                    String by = inputString.substring(index);
                    if (by.isEmpty() || by.isBlank()) {
                        throw new IndexOutOfBoundsException();
                    }
                    taskList[arrayIndex] = new Deadline(desc,by); 
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList[arrayIndex].toString());
                    arrayIndex++;
                    System.out.println("Now you have " + arrayIndex + " tasks in the list.");
                }

                catch (IndexOutOfBoundsException e){
                    dukeEx.missingDescription("deadline");
                }
                
            }

            else if (inputString.startsWith("event")){

                try {
                    
                    int index = inputString.indexOf("/"); 
                    String desc = inputString.substring(6,index);
                    if (desc.isEmpty() || desc.isBlank()) {
                        throw new IndexOutOfBoundsException();
                    }
                    index++;
                    String by = inputString.substring(index);
                    if (by.isEmpty() || by.isBlank()) {
                        throw new IndexOutOfBoundsException();
                    }
                    taskList[arrayIndex] = new Event(desc,by);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList[arrayIndex].toString()); 
                    arrayIndex++;
                    System.out.println("Now you have " + arrayIndex + " tasks in the list.");
                }

                catch (IndexOutOfBoundsException e){
                    dukeEx.missingDescription("event");
                }

                
                

            }
            
            else if (inputString.equals("list")){
                System.out.println("Here are the tasks in your list : ");
                for (int i = 0; i<arrayIndex; i++) {
                    System.out.println(i+1 + "." + taskList[i].toString());
                }
            }

            else { 
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                continue;
            }

        

           
        }
        
           
      
    }
}
