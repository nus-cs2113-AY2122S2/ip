import Tasks.*;
import java.util.ArrayList;

/**
 * Interactions with the user.
 */
public class Ui {
    public void greetings(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

   public void goodBye() {
       System.out.println("Bye.Have a nice day!");
   }

    /**
     * Display the information regarding tasks.
     * @param taskList Arraylist containing task information.
     */
   public void displayTasks(ArrayList<Task> taskList){
       System.out.println("Here are the tasks in your list:");
       for (int i = 0; i < taskList.size(); i++)
           System.out.println((i+1)+". "+taskList.get(i).toString());
   }

    /**
     * Display the incomplete information message given the input type.
     * @param type Type of instruction given by the user.
     */
   public void incompleteMessage(String type) {
       switch (type) {
           case "mark":
               System.out.println("☹ OOPS!!! Please tell me the task you want to mark.");
               break;
           case "unmark":
               System.out.println("☹ OOPS!!! Please tell me the task you want to unmark.");
               break;
           case "todo":
               System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
               break;
           case "deadline":
               System.out.println("OOPS!!! The information about the deadline is incomplete.");
               break;
           case "event":
               System.out.println("☹ OOPS!!! The information about the event is incomplete.");
               break;
           case "delete":
               System.out.println("☹ OOPS!!! Please tell me the task you want to delete.");
               break;
           case "find":
               System.out.println("☹ OOPS!!! Please tell me the task you want to find.");
               break;
       }
   }

   public void addTaskMessage(ArrayList<Task> taskList){
       System.out.println("Got it. I've added this task:");
       System.out.println("  " + taskList.get(taskList.size() - 1).toString());
       System.out.println("Now you have " + taskList.size() + " tasks in the list.");
   }

    /**
     * Mark the corresponding task as done and display the successful message.
     * @param taskList Arraylist containing task information.
     * @param index Index of the task which will be marked as done.
     */
   public void markAndDisplayTask(ArrayList<Task> taskList, int index){
       taskList.get(index-1).markAsDone();
       System.out.println("Nice! I've marked this task as done:");
       System.out.println((index)+". "+taskList.get(index-1).toString());
   }

    /**
     * Mark the corresponding task as undone and display the successful message.
     * @param taskList Arraylist containing task information.
     * @param index Index of the task which will be marked as undone.
     */
   public void unmarkAndDisplayTask(ArrayList<Task> taskList, int index){
       taskList.get(index-1).markAsUndone();
       System.out.println("OK, I've marked this task as not done yet:");
       System.out.println((index)+". "+taskList.get(index-1).toString());
   }

    /**
     * Add the task as ToDo.
     * @param taskList Arraylist containing task information.
     * @param todo Description of the task which will be added as ToDo.
     */
   public void addToDo(ArrayList<Task> taskList, String todo){
       taskList.add(new ToDo(todo));
       addTaskMessage(taskList);
   }

    /**
     * Add the task as deadline.
     * @param taskList Arraylist containing task information.
     * @param deadline Description of the task which will be added as Deadline.
     */
   public void addDeadline(ArrayList<Task> taskList, String deadline){
       String[] deadline1 = deadline.split("/by", 2);
       taskList.add(new Deadline(deadline1[0], deadline1[1]));
       addTaskMessage(taskList);
   }

    /**
     * Add the task as event.
     * @param taskList Arraylist containing task information.
     * @param event Description of the task which will be added as Event.
     */
   public void addEvent(ArrayList<Task> taskList, String event){
       String[] event1 = event.split("/at", 2);
       taskList.add(new Event(event1[0], event1[1]));
       addTaskMessage(taskList);
   }

    /**
     * Delete the corresponding task.
     * @param taskList Arraylist containing task information.
     * @param index Index of the task which will be deleted.
     */
   public void deleteTask(ArrayList<Task> taskList, int index){
       System.out.println("Noted. I've removed this task:");
       System.out.println((index)+". "+taskList.get(index-1).toString());
       taskList.remove(index-1);
       System.out.println("Now you have " + taskList.size() + " tasks in the list.");
   }

   public void displayDefaultMessage(){
       System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
   }

   public void showNumberError(){
       System.out.println("☹ OOPS!!! Please tell me the task number you want to mark/unmark/delete.");
   }

   public void showFormattingError(String type){
        switch(type){
            case "deadline":
                System.out.println("Could you type in the deadline with format:" +
                        " deadline {your task} /by {your deadline}?");
                break;
            case "event":
                System.out.println("Could you type in the event with format:" +
                        " event {your task} /at {your event time}?");
                break;
       }
   }

   public void showLoadingError(){
        System.out.println("The loading process is unsuccessful.");
   }

   public void displayFoundTasks(ArrayList<Task> taskList, String keyword){
       int count=1;
       System.out.println("Here are the matching tasks in your list:");
       for(Task t: taskList){
           if(t.getDescription().toUpperCase().contains(keyword.toUpperCase())){
               System.out.println(count+". "+ t);
               count++;
           }
       }
       if(count==1)
           System.out.println("No matched tasks!");
   }
}

