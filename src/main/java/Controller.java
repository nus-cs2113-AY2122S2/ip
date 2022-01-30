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

    public void unmarkTask(String input){
        String[] keywords = input.split(" ");
        int index= Integer.parseInt(keywords[1]);
        manager.unmarkTask(index);
    }

    public void markTask(String input){
        String[] keywords = input.split(" ");
        int index= Integer.parseInt(keywords[1]);
        manager.markTask(index);
    }

    public void replyMsgPrinter(){
        chatbox.setContent(this.replyMsg);
        chatbox.chatboxPrinter();
    }

    public void listen() {
        Scanner msg = new Scanner(System.in);
        this.recvMsg = msg.nextLine();
        String[] keyword = this.recvMsg.toLowerCase(Locale.ROOT).split(" ");
        switch (keyword[0]) {
        case "bye":
            this.bye();
            break;
        case "list":
            this.listTask();
            break;
        case "mark":
            this.markTask(this.recvMsg);
            break;
        case "unmark":
            this.unmarkTask(this.recvMsg);
            break;
        default:
            this.addTask(this.recvMsg);
        }
    }

}