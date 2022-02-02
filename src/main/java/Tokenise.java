import java.util.Arrays;
/**
 * Tokenise is to help split user input
 * into individual strings for sanitisation
 * and other checks
 */
public class Tokenise {
    private String[] tokens = new String[] {};
    private Boolean isKeyword = false;

    public Tokenise(String userInput) {
        tokens = userInput.split(" ");

        switch (tokens[0]) {
        case "list":
        case "todo":
        case "deadline":
            //try catch for adding into
            //Classes to check if date is
            //added for the keyword
        case "event":
            isKeyword = true;
            break;
        case "mark":
        case "unmark":
            if (tokens.length == 2) {
                try {
                    Integer.parseInt(tokens[1]);
                    tokens[1] = String.valueOf(Integer.parseInt(tokens[1])-1);
                    isKeyword = true;
                } catch (NumberFormatException e) {
                    //user did not mark with a number
                    //currently just added as normal task
                    isKeyword = false;
                    //System.out.println("Please use a number at the end");
                }
            }
            break;
        default:
            isKeyword = false;
        }
    }

    public String[] getTokens() {
        return tokens;
    }

    public Boolean getIsKeyword() {
        return isKeyword;
    }

    public String removeKeyword() {
        String stringWithoutKeyword = "";
        for (int i = 1; i < tokens.length; i++) {
            stringWithoutKeyword = stringWithoutKeyword
                    + " " + tokens[i];
        }
        return stringWithoutKeyword.trim();

    }
}
