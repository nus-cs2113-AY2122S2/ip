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

    public static String getEventTaskDescription(String input) {
        int firstSpaceIndex = input.indexOf(" ");
        int slashIndex = input.indexOf("/at");
        // +1 to exclude " " and -1 to exclude "/"
        String description = input.substring(firstSpaceIndex + 1, slashIndex - 1);
        return description.trim();
    }

    public static String getEventDateTime(String input) {
        int slashIndex = input.indexOf("/at");
        // +3 to exclude "/at"
        String dateTime = input.substring(slashIndex + 3);
        return dateTime.trim();
    }

    public static String getToDoTaskDescription(String input) throws DukeException {
        if (input.contains(" ")) {
            int firstSpaceIndex = input.indexOf(" ");
            // +1 to exclude " "
            String description = input.substring(firstSpaceIndex + 1).trim();
            // check if it is just spaces and no words for the todo_task description
            if (description.isEmpty()){
                throw new DukeException("Oops! The description of a todo cannot be empty!");
            }
            return description;
        }
        throw new DukeException("Oops! The description of a todo cannot be empty!");
    }
}
