import taskitems.exceptions.IllegalInputException;


public class Parser {

    public String parseTodo (String reader) throws IllegalInputException {
        String parameter = reader;
        if (!parameter.equals("")) {
            return parameter;
        }
        throw new IllegalInputException();
    }

    public String[] parseDeadline (String reader) throws IllegalInputException {
        String[] parameter = reader.trim().split(" /by ");
        if (parameter.length != 2) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (parameter[0].equals("") || parameter[1].equals("")) {
            throw new IllegalInputException();
        }
        return parameter;
    }

    public String[] parseEvent (String reader) throws IllegalInputException, ArrayIndexOutOfBoundsException  {
        String[] parameter = reader.trim().split(" /at ");
        if (parameter.length != 2) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (parameter[0].equals("") || parameter[1].equals("")) {
            throw new IllegalInputException();
        }
        return parameter;
    }
    public int parseMark (String reader) throws NumberFormatException {
        String parameter = reader.trim();
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException();
        }
    }

    public int parseDelete (String reader) {
        String parameter = reader.trim();
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException();
        }
    }
}
