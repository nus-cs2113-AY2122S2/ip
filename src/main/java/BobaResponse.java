import java.util.ArrayList;

/**
 * Utility Class that stores and print responses for the bot
 */
public class BobaResponse {

    /** The list of all the responses*/
    private static ArrayList<String> responses = new ArrayList<>();

    /**
     * Add a response into the list
     * @param response String response of the bot
     */
    public static void addResponse(String response) {
        responses.add(response);
    }

    /**
     * The response the bot gives based on the input by the user.
     */
    public static void printResponse() {
        System.out.println("............................................................");
        for (String response : responses) {
            System.out.println("\t" + response);
        }
        System.out.println("............................................................");
        // Clear out the list for new additions
        responses.clear();
    }

    /**
     * Print the current String in proper format
     * @param response String response of the bot
     */
    public static void printThis(String response){
        System.out.println("............................................................");
        System.out.println("\t" + response);
        System.out.println("............................................................");
    }
}
