import java.util.Locale;
import java.util.Scanner;

public class Controller {
    protected String HELLO_WORDS = "Hello! I'm Duke :P\nWhat can I do for you?";
    protected String GOODBYE_WORDS = "Bye. Hope to see you again soon! ;)";
    protected String recvMsg = "";
    protected String replyMsg = "";
    Chatbox chatbox = new Chatbox();
    TaskManager manager = new TaskManager();
    OperationAnalyst analyst;

    public void greet() {
        chatbox.setContent(HELLO_WORDS);
        chatbox.chatboxPrinter();
    }

    public void bye() {
        chatbox.setContent(GOODBYE_WORDS);
        chatbox.chatboxPrinter();
        System.exit(0);
    }

    public void unmarkTask(){
        //String[] keywords = input.split(" ");
        int index= Integer.parseInt(analyst.taskName);
        manager.unmarkTask(index);
    }

    public void markTask(){
        //String[] keywords = input.split(" ");
        int index= Integer.parseInt(analyst.taskName);
        manager.markTask(index);
    }
    /*
    public void addTask(String input) {
        manager.addTask(input);
    }

    public void addToDo(String input) {
        String name = input.replace("todo", "");
        manager.addToDo(name);
    }

    public void addDeadline(String input) {
        String[] keywords = input.split("/by ");
        String by = keywords[1];
        String name = keywords[0].replace("deadline ", "");
        manager.addDeadline(analyst.taskName, analyst.time);
    }

    public void addEvent(String input) {
        manager.addEvent(analyst.taskName, analyst.time);
    }

    public void listTask(){
        manager.listTask();
    }


    public void greet() {
        chatbox.setContent(HELLO_WORDS);
        chatbox.chatboxPrinter();
    }

    public void bye() {
        chatbox.setContent(GOODBYE_WORDS);
        chatbox.chatboxPrinter();
        System.exit(0);
    }

    public void replyMsgPrinter(){
        chatbox.setContent(this.replyMsg);
        chatbox.chatboxPrinter();
    }

     */

    public void listen() {
        Scanner msg = new Scanner(System.in);
        this.recvMsg = msg.nextLine();
        analyst = new OperationAnalyst(this.recvMsg);
        String command = analyst.getCommand();
        //String[] keyword = this.recvMsg.toLowerCase(Locale.ROOT).split(" ");
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
        default:
            manager.addTask(analyst.taskName);
        }
    }


}