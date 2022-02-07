import java.util.Locale;
import java.util.Scanner;

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
    public void unmarkTask(){
        int index= Integer.parseInt(analyst.taskName);
        manager.unmarkTask(index);
    }

    /**
     * Marks task in the list
     */
    public void markTask(){
        int index= Integer.parseInt(analyst.taskName);
        manager.markTask(index);
    }

    /**
     * Listen the instruction and operate during the session
     */
    public void listen() {
        Scanner msg = new Scanner(System.in);
        this.recvMsg = msg.nextLine();
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
        default:
            manager.addTask(analyst.taskName);
        }
    }


}