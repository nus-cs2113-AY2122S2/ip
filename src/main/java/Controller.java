import java.util.Scanner;
/**
 * Controller serves for orders
 */
public class Controller {
    private String helloWords = " Hello! I'm Duke\n" + " What can I do for you?";
    private String exitWords = "Bye. Hope to see you again soon!";
    private String EXIT_COMMAND = "bye";
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
     *  Listen to the i/o and pass the instruction to operationFactory
     */
    public void listen(){

        Scanner sc = new Scanner(System.in);

        OperationFactory operationFactory = new OperationFactory();

        while(true){
            try{
                String order = sc.nextLine();
                operationFactory.orderSetter(order);
                Operation operation = operationFactory.makeOperation();
                ChatBox.chatBoxPrinter(operation.getResult());
                if (operation.getOperationName().equals("bye")){
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }



        }

    }

}
