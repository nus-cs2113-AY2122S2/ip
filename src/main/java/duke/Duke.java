package duke;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) throws IndexOutOfBoundsException{
        System.out.println("---------------------");
        System.out.println(("Hello! I'm Duke"));
        System.out.println(("What can i do for you?"));
        System.out.println("---------------------");
<<<<<<< Updated upstream
        Task[] taskList = new Task[100];
        Boolean continueProgram = true; 
        int arrayIndex = 0; 
=======
        ArrayList<Task> taskList = new ArrayList<Task>(); 
        //file organization 
        try {
            File myTasks = new File("tasks.txt"); 
            if (myTasks.createNewFile()){
                System.out.println("File is created!");
               }else{
                System.out.println("File exists, reading it to taskList!");
                Scanner s = new Scanner(myTasks); // create a Scanner using the File as the source
                while (s.hasNext()){
                    String nextString = s.nextLine();
                    String[] splitString = nextString.split("\\|");
                    String typeTask = splitString[0].replaceAll(" ","");
                    String marked = splitString[1].replaceAll(" ","");
                    String desc = splitString[2];
                    System.out.println(typeTask);
                    System.out.println(marked);
                    System.out.println(desc);
                    String todo = "T"; 
                    String deadline = "D";   
                    String event = "E";      
                    String toMark = "1";          
                    if (typeTask.equals(todo)) { 
                        taskList.add(new Todo(desc));
                        if (marked.equals(toMark)) { 
                            taskList.get(taskList.size()-1).markTask();
                        }
                    }

                    else if (typeTask.equals(deadline)) { 
                        String deadlineDate = splitString[3];
                        taskList.add(new Deadline(desc,deadlineDate));
                        if (marked.equals(toMark)) { 
                            taskList.get(taskList.size()-1).markTask();
                        }
                    }

                    else if (typeTask.equals(event)) { 
                        String eventDate = splitString[3];
                        taskList.add(new Event(desc,eventDate)); 
                        if (marked.equals(toMark)) { 
                            taskList.get(taskList.size()-1).markTask();
                        }
                    }
                }
                s.close();
               }        

        } catch (IOException e) {
            e.printStackTrace();
        }




        // Task[] taskList = new Task[100];
        //using Arraylist instead. 
        // int arrayIndex = 0; 
        String filePath = "tasks.txt";
        boolean continueProgram = true; 
>>>>>>> Stashed changes
        DukeException dukeEx = new DukeException();
        while (continueProgram) { 
            Scanner sc = new Scanner(System.in);
            String inputString = sc.nextLine();
<<<<<<< Updated upstream

=======
            updateFile(filePath,taskList); 
            // terminating sequence 
>>>>>>> Stashed changes
            if (inputString.equals("bye")) { 
                System.out.println("Bye! Hope to see you again.");
                continueProgram = false; 
            }
<<<<<<< Updated upstream
=======

            //deleting task
            else if (inputString.matches("delete \\d+")){
                try {
                    String[] arrOfStr = inputString.split(" ", 0);
                    String indexValue = arrOfStr[1]; 
                    int indexValue2 = Integer.parseInt(indexValue) - 1;
                    if (indexValue2 > taskList.size()-1 || indexValue2 < 0){
                        throw new IndexOutOfBoundsException();
                    }

                    Task removedTask = taskList.get(indexValue2);
                    taskList.remove(indexValue2);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    updateFile(filePath,taskList);

                }

                catch (IndexOutOfBoundsException e){
                    dukeEx.wrongIndex();
                }
            }

            //marking a task by index 
>>>>>>> Stashed changes
            else if (inputString.matches("mark \\d+")){

                try {
                    String[] arrOfStr = inputString.split(" ", 0);
                    String indexValue = arrOfStr[1]; 
                    int indexValue2 = Integer.parseInt(indexValue) - 1;
                    //index given is out of array size
                    if (indexValue2 > arrayIndex || indexValue2 < 0){
                        throw new IndexOutOfBoundsException();
                    }
<<<<<<< Updated upstream
                    taskList[indexValue2].markTask(); 
=======
                    // taskList[indexValue2].markTask(); 
                    taskList.get(indexValue2).markTask();
                    updateFile(filePath,taskList);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    taskList[indexValue2].unmarkTask(); 
=======
                    // taskList[indexValue2].unmarkTask(); 
                    taskList.get(indexValue2).unmarkTask();
                    updateFile(filePath,taskList);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    taskList[arrayIndex] = new Todo(desc);
                    System.out.println(taskList[arrayIndex].toString());
                    arrayIndex++; 
                    System.out.println("Now you have " + arrayIndex + " tasks in the list.");
=======
                    taskList.add(new Todo(desc));
                    System.out.println(taskList.get(taskList.size()-1).toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    String textToAdd = "T" + "|" + " 0 " + "|" + desc;
                    try {
                        appendData(filePath,textToAdd);
                    }
                    catch (IOException e) {
                        System.out.println("Failed to write event data to text file.");
                    }
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    System.out.println(taskList[arrayIndex].toString());
                    arrayIndex++;
                    System.out.println("Now you have " + arrayIndex + " tasks in the list.");
=======
                    System.out.println(taskList.get(taskList.size()-1).toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    String textToAdd = "D" + "|" + " 0 " + "|" + desc + "|" + by;
                    try {
                        appendData(filePath,textToAdd);
                    }
                    catch (IOException e) {
                        System.out.println("Failed to write deadline data to text file.");
                    }
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    System.out.println(taskList[arrayIndex].toString()); 
                    arrayIndex++;
                    System.out.println("Now you have " + arrayIndex + " tasks in the list.");
=======
                    System.out.println(taskList.get(taskList.size()-1).toString()); 
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");

                    String textToAdd = "E" + "|" + " 0 " + "|" + desc + "|" + by;
                    try {
                        appendData(filePath,textToAdd);
                    }
                    catch (IOException e) {
                        System.out.println("Failed to write event data to text file.");
                    }

>>>>>>> Stashed changes
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
    public static void appendData(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath,true); 
        fw.write(System.getProperty( "line.separator" ));
        fw.write(textToAppend); 
        fw.close();
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void updateFile(String filePath, ArrayList<Task> taskList) {
        for (int i = 0; i<taskList.size(); i++) {
            System.out.println(taskList.get(i).toString());
            String taskListLine = taskList.get(i).toString();
            String marked = "0";
            String textToAdd = "";
            if ( taskListLine.charAt(4)==('X') ) {
                marked = "1";
            }

            String typeTask = Character.toString(taskListLine.charAt(1));
            if (typeTask.equals("T")) {
                textToAdd = typeTask + "|" + marked + "|" + taskListLine.substring(6);
            }

            else {
                int index = taskListLine.indexOf("("); 
                int index2 = taskListLine.indexOf(")"); 
                textToAdd = typeTask + "|" + marked + "|" + taskListLine.substring(6,index) + "|" + taskListLine.substring(index+1,index2);
            }

            if (i==0){
                try {
                    writeToFile(filePath,textToAdd);
                } catch (IOException e) {
                    System.out.println("Failed to update first line.");
                }
            }

            else { 
                try {
                    appendData(filePath,textToAdd);
                } catch (IOException e) {
                    System.out.println("Failed to append line.");
                }
            }
            
        }
    }




}
