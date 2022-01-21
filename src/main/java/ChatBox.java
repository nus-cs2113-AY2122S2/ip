/**
 *  ChatBox serves for the chat interface between user and Duke
 */
public class ChatBox {

    private String contends = "";
    private String upperLine = "____________________________________________________________";

    /**
     * Intitializes a ChatBox with Defined contends
     * @param _contends The contends in one ChatBox
     */
    public ChatBox(String _contends){
        contendsSetter(_contends);
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
    public String chatBoxGetter()
    {
        String returnString = upperLine + "\n" + contends ;
        return returnString;
    }

    /**
     * Print the layout of the ChatBox
     */
    public void chatBoxPrinter()
    {
        String printString = upperLine + "\n" + contends ;
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
