public class Parser {
    private String[] parsedInput;

    public Parser(String line) throws InvalidCommandException, MissingDescriptionException {
        parsedInput = line.split(" ");

        switch (parsedInput[0]) {
        case "todo":
            if (parsedInput.length < 2) {
                throw new MissingDescriptionException();
            }
            break;
        case "deadline":
            // Fallthrough
        case "event":
            // Fallthrough
        case "list":
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            if (parsedInput.length != 2) { //simple workaround for now, need better checks later
                throw new MissingDescriptionException();
            }
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    public String[] getParsedInput() {
        return parsedInput;
    }

}
