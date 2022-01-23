/**
 *  ChatBox serves for the chat interface between user and Duke
 */
public class ChatBox {

    private String contends = "";
    private static String upperLine = "____________________________________________________________";

    /**
     * Intitializes a ChatBox with Defined contends
     * @param _contends The contends in one ChatBox
     */
    public ChatBox(String _contends){
        contendsSetter(_contends);
    }

    public ChatBox(){

    }


    /**
     * Set the contends of one ChatBox
     * @param _contends The contends in one ChatBox
     */
    public void contendsSetter(String _contends)
    {
        this.contends = _contends;
    }

    /**
     * Get the layout of the ChatBox
     * @return The Layout of the ChatBox
     */
    public String getChatBox()
    {
        String returnString = upperLine + "\n" + contends + "\n" + upperLine + "\n";
        return returnString;
    }

    /**
     * Print the layout of the ChatBox
     */
    public void chatBoxPrinter()
    {
        String printString = upperLine + "\n" + contends + "\n" + upperLine + "\n";
        System.out.println(printString);
    }


    /** Print the chatBox without creating object
     * @param _contends The contends that needs to be printed
     */
    public static void chatBoxPrinter(String _contends)
    {
        String printString = upperLine + "\n" + _contends  + "\n" + upperLine + "\n";
        System.out.println(printString);
    }

    /**
     * Print the end layout of the ChatBox
     */
    public void chatBoxEnd()
    {
        System.out.println(upperLine);
    }


}
