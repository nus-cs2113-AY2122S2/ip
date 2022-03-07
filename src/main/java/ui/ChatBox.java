package ui;

/**
 * ui.ChatBox serves for the chat interface between user and Duke
 */
public class ChatBox {

    private static final String UPPER_LINE = "____________________________________________________________";
    private String contends = "";



    public ChatBox() {
    }


    public void setContends(String contends) {
        this.contends = contends;
    }


    /**
     * Print the layout of the ui.ChatBox
     */
    public void printChatBox() {
        String printString = UPPER_LINE + "\n" + contends + "\n" + UPPER_LINE + "\n";
        System.out.println(printString);
    }


    /** Print the chatBox without creating object
     *
     * @param contends The contends that needs to be printed
     */
    public static void printChatBox(String contends) {
        String printString = UPPER_LINE + "\n" + contends + "\n" + UPPER_LINE + "\n";
        System.out.println(printString);
    }



}
