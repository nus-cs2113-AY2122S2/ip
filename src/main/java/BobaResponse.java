import java.util.ArrayList;

public class BobaResponse {

    private static ArrayList<String> responses = new ArrayList<>();

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
        responses.clear();
    }

    public static void printThis(String response){
        System.out.println("............................................................");
        System.out.println("\t" + response);
        System.out.println("............................................................");
    }
}
