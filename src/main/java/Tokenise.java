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
            isKeyword = true;
            break;
        case "mark":
        case "unmark":
            if (tokens.length == 2) {
                try {
                    Integer.parseInt(tokens[1]);
                    tokens[1] = String.valueOf(Integer.parseInt(tokens[1])-1);
                    isKeyword = true;
                } catch (Exception e) {
                    //this user input is not trying to mark or unmark
                    //any previous tasks
                    isKeyword = false;
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
}
