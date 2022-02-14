package Duke;

import java.util.ArrayList;
import java.util.List;

public class Command {
    /**
     * The original user input (raw command).
     */
    private final String rawCommand;
    /**
     * The functional unit in raw command.
     */
    private final String[] commandTokens;

    Command(String rawCommand) {
        this.rawCommand = rawCommand;
        commandTokens = parseCommand(rawCommand);
    }

    /**
     * Split the raw command (a string contains space and quotes) into tokens,
     * recognizing string in quotes as a whole string. Automatically
     * add a quote at the end of the string if there is an odd number
     * of quotes.
     *
     * @param raw raw command string
     * @return a string array containing tokens
     */
    public String[] parseCommand(String raw) {
        int leftIndex = 0;
        boolean inQuotes = false;
        List<String> tokens = new ArrayList<String>();
        raw = raw.trim();
        for (int i = 0; i < raw.length(); i++) {
            switch (raw.charAt(i)) {
            case ' ':
                if (inQuotes) {
                    continue;
                } else if (leftIndex != -1) {
                    tokens.add(raw.substring(leftIndex, i));
                    leftIndex = -1;
                }
                break;
            case '\"':
                if (inQuotes) {
                    tokens.add(raw.substring(leftIndex, i));
                    leftIndex = -1;
                    inQuotes = false;
                } else {
                    inQuotes = true;
                    leftIndex = i + 1;
                }
                break;
            default:
                if (!inQuotes && leftIndex == -1) {
                    leftIndex = i;
                }
                break;
            }
        }
        if (leftIndex != -1) {
            tokens.add(raw.substring(leftIndex));
        }
        return tokens.toArray(new String[0]);
    }


    /**
     * Get original user input string.
     * @return the original user input (raw string)
     */
    public String getRawCommand() {
        return rawCommand;
    }

    /**
     * Get tokens from original input string.
     * @return the tokens/args
     */
    public String[] getCommandTokens() {
        return commandTokens;
    }
}
