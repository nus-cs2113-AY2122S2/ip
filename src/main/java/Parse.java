public class Parse {
    private String[] parsedInput;
    private boolean isKeyword = false;

    public Parse(String line) {
        parsedInput = line.split(" ");

        switch (parsedInput[0]) {
            case "list":
                isKeyword = true;
                break;
            case "mark":
            case "unmark":
                if (parsedInput.length == 2) { //simple workaround for now, need better checks later
                    isKeyword = true;
                }
                break;
            default:
                isKeyword = false;
        }
    }

    public String[] getParsedInput() {
        return parsedInput;
    }

    public boolean getIsKeyword() {
        return isKeyword;
    }
}
