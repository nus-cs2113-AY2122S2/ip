import Tasks.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private ArrayList<Task> todolist = new ArrayList<Task>();
    private Ui ui;

    /**
     * Initialize the Duke.
     * @param filePath
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        Storage.create(filePath);
        try {
            todolist = new ArrayList<Task>(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            todolist = new ArrayList<Task>();
        }
    }

    public void run() throws IOException {
        String task;
        ui.greetings();
        Scanner in = new Scanner(System.in);
        task=in.nextLine();
        while(true){
            if(task.equalsIgnoreCase("bye"))
                break;
            String[] words = task.split(" ",2);
            switch (words[0].toLowerCase()){
                case "list":{
                   ui.displayTasks(todolist);
                   break;
                }
                case "mark":{
                    if (words.length==1)
                        ui.incompleteMessage("mark");
                    else{
                        try{
                            int index = Integer.parseInt(words[1]);
                            if(index>todolist.size()||index<=0) System.out.println("No task found.");
                            else ui.markAndDisplayTask(todolist,index);
                        }
                        catch (NumberFormatException ex){
                            ui.showNumberError();
                        }
                    }
                    break;
                }
                case "unmark":{
                    if (words.length==1)
                        ui.incompleteMessage("unmark");
                    else{
                        try{
                            int index = Integer.parseInt(words[1]);
                            if(index>todolist.size()||index<=0) System.out.println("No task found.");
                            else ui.unmarkAndDisplayTask(todolist,index);
                        }
                        catch (NumberFormatException ex){
                            ui.showNumberError();
                        }
                    }
                    break;
                }
                case "todo": {
                    if (words.length == 1) ui.incompleteMessage("todo");
                    else ui.addToDo(todolist,words[1]);
                    break;
                }
                case "deadline": {
                    if (words.length==1)
                        ui.incompleteMessage("deadline");
                    else{
                        try{
                            ui.addDeadline(todolist,words[1]);
                        }
                        catch(ArrayIndexOutOfBoundsException e){
                            ui.showFormattingError("deadline");
                        }
                    }
                    break;
                }
                case "event": {
                    if (words.length==1)
                        ui.incompleteMessage("event");
                    else{
                        try{
                            ui.addEvent(todolist,words[1]);
                        }
                        catch(ArrayIndexOutOfBoundsException e){
                            ui.showFormattingError("event");
                        }
                    }
                    break;
                }
                case "delete":{
                    if (words.length==1)
                        ui.incompleteMessage("delete");
                    else{
                        try{
                            int index = Integer.parseInt(words[1]);
                            if(index>todolist.size()||index<=0) System.out.println("No task found.");
                            else ui.deleteTask(todolist,index);
                        }
                        catch (NumberFormatException ex){
                            ui.showNumberError();
                        }
                    }
                    break;
                }
                default:
                    ui.displayDefaultMessage();
            }
            task=in.nextLine();
        }
        Storage.save(Storage.format(todolist),"data.txt");
        ui.goodBye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data.txt").run();
    }
}

