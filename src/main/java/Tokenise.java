import java.util.Arrays;
/**
 * Tokenise is to help split user input
 * into individual strings for sanitisation
 * and other checks
 */
public class Tokenise {
    private String[] tokens = new String[] {};
    private int markIndex;
    private String description;
    private String time;

    public Tokenise(String userInput) throws DukeException {
        tokens = userInput.split(" ");

        try {
            switch (tokens[0]) {
            case "list":
                if (tokens.length != 1) {
                    throw new DukeException("list", "list");
                }
                break;
            case "deadline":
                if (tokens.length < 2) {
                    throw new DukeException("no description", "no description");
                }
                int deadlineBy = findIndex(tokens, "/by");
                if (deadlineBy > 0) {
                    //remove keyword deadline and deadline time from raw user input
                    this.description = combineToken(tokens, 1, deadlineBy);
                    this.time = combineToken(tokens, deadlineBy + 1);
                } else {
                    throw new DukeException("no timing", "no timing");
                }
                break;
            case "events":
                if (tokens.length < 2) {
                    throw new DukeException("no description", "no description");
                }
                int eventAt = findIndex(tokens, "/at");
                if (eventAt > 0) {
                    //remove keyword deadline and deadline time from raw user input
                    this.description = combineToken(tokens, 1, eventAt);
                    this.time = combineToken(tokens, eventAt + 1);
                } else {
                    throw new DukeException("no timing", "no timing");
                }
            case "todo":
                if (tokens.length < 2) {
                    throw new DukeException("no description", "no description");
                }
                this.description = combineToken(tokens, 1);
                break;
            case "mark":
            case "unmark":
                //checking if value after mark or unmark is a number
                if (tokens.length != 2) {
                    System.out.printf("OOPS!!! %s must have the number of task to %s",
                            tokens[0], tokens[0]);
                }
                try {
                    markIndex = Integer.parseInt(tokens[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.printf("%s needs a number as an input", tokens[0]);
                }
                break;
            default:
                throw new DukeException("no command", "no command");
            }
        } catch (DukeException e) {
            if (e.getDescription().equals("no command")) {
                System.out.printf("OOPS!!! I'm sorry, but I don't know what that means :-(\n");

            } else if (e.getDescription().equals("no description")) {
                System.out.printf("OOPS!!! The description of a %s cannot be empty!\n",
                        tokens[0]);
            } else if (e.getDescription().equals("no timing")) {
                System.out.printf(Duke.wrapMessage("OOPS!!! The time of this %s cannot be empty!\n"),
                        tokens[0]);
            } else if (e.getDescription().equals("list")) {
                System.out.println(Duke.wrapMessage(
                        "OOPS!!! List should not have any other text after!"));
            }
        }
    }

    public int getMarkIndex() {
        return markIndex;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String[] getTokens() {
        return tokens;
    }

    /**
     * Finds the index of word in the string array
     * mimicking the use of an array list
     * @param tokens string to search of in tokens array
     * @param word string to search of in tokens array
     * @return index of word in tokens array, or -1 if word is not found
     */
    private int findIndex(String[] tokens, String word) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This will combine the Token into a String for the actual Task
     *
     * @param tokens User input token
     * @param startIndex Where to start copying the tokens
     * @return combinedToken String of the combined tokens
     */
    private String combineToken(String[] tokens, int startIndex) {
        String combinedToken = "";
        for (int i = startIndex; i < tokens.length; i++) {
            combinedToken = combinedToken + tokens[i] + " ";
        }
        return combinedToken.trim();
    }

    /**
     * This will combine the Token into a String for the actual Task
     *
     * @param tokens User input token
     * @param startIndex Where to start copying the tokens
     * @param endIndex Where to end copying the tokens
     * @return combinedToken String of the combined tokens
     */
    private String combineToken(String[] tokens, int startIndex, int endIndex) {
        String combinedToken = "";
        for (int i = startIndex + 1; i < endIndex; i++) {
            combinedToken = combinedToken + tokens[i] + " ";
        }
        return combinedToken.trim();
    }
}
