import java.util.Scanner;
/**
 * Controller serves for orders
 */
public class Controller {
    private String helloWords = " Hello! I'm Duke\n" + " What can I do for you?";
    private String exitWords = "Bye. Hope to see you again soon!";
    ChatBox chatBox;

    /**
     *   Initialize an controller
     */
    public Controller()
    {
        chatBox = new ChatBox();
        sayHello();
    }

    /**
     *  Say hi when the controller is created
     */
    public void sayHello() {
        chatBox.contendsSetter(helloWords);
        chatBox.chatBoxPrinter();
    }


    /**
     *  Say good bye before the controller end
     */
    public void sayGoodBye() {
        chatBox.contendsSetter(exitWords);
        chatBox.chatBoxPrinter();
        chatBox.chatBoxEnd();
    }


    /**
     *  Listen to the i/o and
     */
    public void listen(){

        Scanner sc = new Scanner(System.in);

        while(true){
            try{
                String order = sc.nextLine();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }






}
