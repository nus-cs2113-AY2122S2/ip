public class CommandParser {

    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static String getDeadlineTaskDescription(String input) {
        int firstSpaceIndex = input.indexOf(" ");
        int slashIndex = input.indexOf("/by");
        // +1 to exclude " " and -1 to exclude "/"
        String description = input.substring(firstSpaceIndex + 1, slashIndex - 1);
        return description.trim();
    }

    public static String getDeadlineDate(String input) {
        int slashIndex = input.indexOf("/by");
        // +3 to exclude "/by"
        String date = input.substring(slashIndex + 3);
        return date.trim();
    }

    public static String getEventTaskDescription(String input) throws DukeException, StringIndexOutOfBoundsException {
        if (input.contains(" ") && input.contains("/at")) {
            int firstSpaceIndex = input.indexOf(" ");
            int slashIndex = input.indexOf("/at");
            // +1 to exclude " " and -1 to exclude "/"
            String description = input.substring(firstSpaceIndex + 1, slashIndex - 1).trim();
            if (description.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            return description;
        }
        throw new DukeException("Oops! It seems that you left out the /at in your command!");
    }

    public static String getEventDateTime(String input) throws DukeException, StringIndexOutOfBoundsException {
        if (input.contains("/at")) {
            int slashIndex = input.indexOf("/at");
            // +3 to exclude "/at"
            String dateTime = input.substring(slashIndex + 3).trim();
            if (dateTime.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            return dateTime;
        }
        throw new DukeException("Oops! It seems that you left out the /at in your command!");
    }

    public static String getToDoTaskDescription(String input) throws DukeException, StringIndexOutOfBoundsException {
        if (input.contains(" ")) {
            int firstSpaceIndex = input.indexOf(" ");
            // +1 to exclude " "
            String description = input.substring(firstSpaceIndex + 1).trim();
            if (description.isEmpty()) {
                throw new StringIndexOutOfBoundsException();
            }
            return description;
        }
        throw new DukeException("Oops! The description of a todo task cannot be empty!");
    }
}
