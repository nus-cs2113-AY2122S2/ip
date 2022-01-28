import java.util.Locale;
import java.util.Scanner;

public class Controller {
    public String hello = "Hello! I'm Duke :P\nWhat can I do for you?";
    public String goodbye = "Bye. Hope to see you again soon! ;)";
    public String recvMsg = "";
    public String replyMsg = "";
    Chatbox chatbox = new Chatbox();
    TaskManager manager = new TaskManager();

    public void addTask(String input) {
        manager.addTask(input);
    }

    public void listTask(){
        manager.listTask();
    }

    public void greet() {
        chatbox.setContent(hello);
        chatbox.chatboxPrinter();
    }

    public void bye() {
        chatbox.setContent(goodbye);
        chatbox.chatboxPrinter();
        System.exit(0);
    }



    public void listen() {
        Scanner msg = new Scanner(System.in);
        this.recvMsg = msg.nextLine();
        String res = this.recvMsg.toLowerCase(Locale.ROOT);
        if(res.equals("bye")){
            this.bye();
        }else if(res.equals("list")) {
            this.listTask();
        }else {
            this.addTask(this.recvMsg);
        }
    }

}