package helper;

import taskitems.exceptions.IllegalInputException;

// Parser class hold various methods that perform parsing of User input
public class Parser {

    // Method to retrieve parameters required to create a Todo task
    // @param (reader) (parameters passed as a single string)
    public String parseTodo (String reader) throws IllegalInputException {
        String parameter = reader;
        if (!parameter.equals("")) {
            return parameter;
        }
        throw new IllegalInputException();
    }

    // Method to retrieve parameters required to create a Deadline task
    // @param (reader) (parameters passed as a single string)
    public String[] parseDeadline (String reader) throws IllegalInputException {
        String[] parameter = reader.trim().split(" /by ");
        if (parameter.length != 2) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (parameter[0].equals("") || parameter[1].equals("")) {
            throw new IllegalInputException();
        }
        return parameter;
    }

    // Method to retrieve parameters required to create an Event task
    // @param (reader) (parameters passed as a single string)
    public String[] parseEvent (String reader) throws IllegalInputException, ArrayIndexOutOfBoundsException  {
        String[] parameter = reader.trim().split(" /at ");
        if (parameter.length != 2) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (parameter[0].equals("") || parameter[1].equals("")) {
            throw new IllegalInputException();
        }
        return parameter;
    }

    // Method to retrieve parameters required to mark a task
    // @param (reader) (parameters passed as a single string)
    public int parseMark (String reader) throws NumberFormatException {
        String parameter = reader.trim();
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException();
        }
    }

    // Method to retrieve parameters required to delete a task
    // @param (reader) (parameters passed as a single string)
    public int parseDelete (String reader) {
        String parameter = reader.trim();
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException();
        }
    }
}
