import exceptions.DukeException;
import operations.Operation;

import java.util.Scanner;

import ui.ChatBox;

/**
 * Controller serves for orders
 */
public class Controller {
    private static final String HELLO_WORDS = " Hello! I'm Duke\n" + " What can I do for you?";
    private static final String EXIT_WORDS = "Bye. Hope to see you again soon!";
    private static final String EXIT_COMMAND = "bye";
    ChatBox chatBox;

    /**
     *   Initialize an controller
     */
    public Controller() {
        chatBox = new ChatBox();
        sayHello();
    }

    /**
     *  Say hi when the controller is created
     */
    public void sayHello() {
        chatBox.contendsSetter(HELLO_WORDS);
        chatBox.printChatBox();
    }


    /**
     *  Listen to the i/o and pass the instruction to operationFactory.
     */
    public void listen() {

        Scanner sc = new Scanner(System.in);

        OperationParserFactory operationParserFactory = new OperationParserFactory();

        while (true) {
            try {
                String order = sc.nextLine();
                operationParserFactory.setOrder(order);
                Operation operation = operationParserFactory.getOperation();
                ChatBox.printChatBox(operation.getResult());
                if (operation.getOperationName().equals(EXIT_COMMAND)) {
                    break;
                }

            } catch (DukeException e) {
                ChatBox.printChatBox(String.format("%s\n\n  %s", e.toString(), "Try to enter \"help\" to check the syntax ^-^"));
            }

        }

    }

}
