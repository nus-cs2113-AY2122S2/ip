public class Parse {
    private String[] parsedInput;
    private boolean isKeyword = false;

    public Parse(String line) {
        parsedInput = line.split(" ");

        switch (parsedInput[0]) {
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            // Fallthrough
        case "list":
            isKeyword = true;
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            if (parsedInput.length == 2) { //simple workaround for now, need better checks later
                isKeyword = true;
            }
            break;
        default:
            isKeyword = false;
            break;
        }
    }

    public String[] getParsedInput() {
        return parsedInput;
    }

    public boolean getIsKeyword() {
        return isKeyword;
    }
}
