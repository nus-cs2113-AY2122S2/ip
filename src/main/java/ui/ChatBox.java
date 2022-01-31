package ui;

/**
 *  ui.ChatBox serves for the chat interface between user and Duke
 */
public class ChatBox {

    private String contends = "";
    private static String upperLine = "____________________________________________________________";

    /**
     * Intitializes a ui.ChatBox with Defined contends
     * @param contends The contends in one ui.ChatBox
     */
    public ChatBox(String contends){
        contendsSetter(contends);
    }

    public ChatBox(){
    }


    /**
<<<<<<< HEAD
     * Set the contends of one ui.ChatBox
=======
     * Set the contends of one ui.ChatBox.
>>>>>>> 9dada07e69461ecaf25973bf549e8fd670b8849f
     * @param contends The contends in one ui.ChatBox
     */
    public void contendsSetter(String contends) {
        this.contends = contends;
    }

    /**
     * Get the layout of the ui.ChatBox
     * @return The Layout of the ui.ChatBox
     */
    public String getChatBox() {
        String returnString = upperLine + "\n" + contends + "\n" + upperLine + "\n";
        return returnString;
    }

    /**
     * Print the layout of the ui.ChatBox
     */
    public void printChatBox() {
        String printString = upperLine + "\n" + contends + "\n" + upperLine + "\n";
        System.out.println(printString);
    }


    /** Print the chatBox without creating object
     * @param contends The contends that needs to be printed
     */
    public static void printChatBox(String contends) {
        String printString = upperLine + "\n" + contends  + "\n" + upperLine + "\n";
        System.out.println(printString);
    }

    /**
     * Print the end layout of the ui.ChatBox
     */
    public void endChatBox() {
        System.out.println(upperLine);
    }


}
