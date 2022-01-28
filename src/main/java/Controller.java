import java.util.Locale;
import java.util.Scanner;

public class Controller {
    public String hello = " Hello! I'm Duke\n What can I do for you?";
    public String goodbye = " Bye. Hope to see you again soon!";
    public String recvMsg = "";
    public String replyMsg = "";
    Chatbox chatbox = new Chatbox();

    public void echo(String input) {
        chatbox.setContent(input);
        this.replyMsg = input;
        chatbox.chatboxPrinter();
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
        if(res.contains("bye")){
            this.bye();
        }else{
            this.echo(this.recvMsg);
        }
    }

}