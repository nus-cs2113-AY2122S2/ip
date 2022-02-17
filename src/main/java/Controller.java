import java.io.File;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class Controller {
    //greeting msg and exit msg
    protected String HELLO_WORDS = "Hello! I'm Duke :P\nWhat can I do for you?";
    protected String GOODBYE_WORDS = "Bye. Hope to see you again soon! ;)";
    protected String recvMsg = "";
    protected String replyMsg = "";
    Chatbox chatbox = new Chatbox();
    TaskManager manager = new TaskManager();
    OperationAnalyst analyst;

    /**
     * Prints greeting msg
     */
    public void greet() {
        chatbox.setContent(HELLO_WORDS);
        chatbox.chatboxPrinter();
    }

    /**
     * Prints goodbye msg and exits
     */
    public void bye() {
        chatbox.setContent(GOODBYE_WORDS);
        chatbox.chatboxPrinter();
        System.exit(0);
    }

    /**
     * Unmarks task in the list
     */
    public void unmarkTask() throws DukeException{
        try {
            int index = Integer.parseInt(analyst.taskName);
            manager.unmarkTask(index);
        } catch (NumberFormatException e) {
            throw new IllegalTaskIndexException();
        }
    }

    /**
     * Marks task in the list
     */
    public void markTask() throws DukeException{
        try {
            int index = Integer.parseInt(analyst.taskName);
            manager.markTask(index);
        } catch (NumberFormatException e) {
            throw new IllegalTaskIndexException();
        }
    }

    /**
    public void deleteTask() throws DukeException{
        try {
            int index = Integer.parseInt(analyst.taskName);
            manager.deleteTask(index);
        } catch (NumberFormatException e) {
            throw new IllegalTaskIndexException();
        }
    }
     */

    /**
     * Listen the instruction and operate during the session
     */
    public void listen() throws DukeException {
        Scanner msg = new Scanner(System.in);
        this.recvMsg = msg.nextLine();
        try {
            analyst = new OperationAnalyst(this.recvMsg);
            String command = analyst.getCommand();
            switch (command) {
            case "bye":
                this.bye();
                break;
            case "list":
                manager.listTask();
                break;
            case "mark":
                this.markTask();
                break;
            case "unmark":
                this.unmarkTask();
                break;
            case "deadline":
                manager.addDeadline(analyst.taskName, analyst.time);
                break;
            case "event":
                manager.addEvent(analyst.taskName, analyst.time);
                break;
            case "todo":
                manager.addToDo(analyst.taskName);
                break;
            //case "delete":
                //this.deleteTask();
            }
            //manager.saveTask();
        } catch (IllegalInstructionException e){
            chatbox.setContent("Sorry, I don't understand your instruction :(");
            chatbox.chatboxPrinter();
        } catch (IllegalFormatException e) {
            chatbox.setContent("Please check your format again :(");
            chatbox.chatboxPrinter();
        } catch (IllegalTaskNameException e) {
            chatbox.setContent("Did you specify your task name?");
            chatbox.chatboxPrinter();
        } catch (IllegalSavingAction e) {
            chatbox.setContent("Cannot save to file :(");
            chatbox.chatboxPrinter();
        } catch (IllegalTaskIndexException e) {
            chatbox.setContent("Please specify the index of the task :(");
            chatbox.chatboxPrinter();
        }
    }

}