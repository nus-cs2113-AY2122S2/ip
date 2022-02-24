package ui;

/**
 *  ui.ChatBox serves for the chat interface between user and Duke
 */
public class ChatBox {

    private String contends = "";
    private static String UPPER_LINE = "____________________________________________________________";

    /**
     * Intitializes a ui.ChatBox with Defined contends
     * @param contends The contends in one ui.ChatBox
     */
    public ChatBox(String contends) {
        contendsSetter(contends);
    }

    public ChatBox() {
    }


    /**
     * Set the contends of one ui.ChatBox.
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
        String returnString = UPPER_LINE + "\n" + contends + "\n" + UPPER_LINE + "\n";
        return returnString;
    }

    /**
     * Print the layout of the ui.ChatBox
     */
    public void printChatBox() {
        String printString = UPPER_LINE + "\n" + contends + "\n" + UPPER_LINE + "\n";
        System.out.println(printString);
    }


    /** Print the chatBox without creating object
     * @param contends The contends that needs to be printed
     */
    public static void printChatBox(String contends) {
        String printString = UPPER_LINE + "\n" + contends + "\n" + UPPER_LINE + "\n";
        System.out.println(printString);
    }

    /**
     * Print the end layout of the ui.ChatBox
     */
    public void endChatBox() {
        System.out.println(UPPER_LINE);
    }


}
