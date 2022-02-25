public class Parser {
    /**
     * Helper for addTask
     * Extracts information about Task object from line of user input
     * Reports information in array of Strings
     * @param inputLine line of user input
     * @return array of Strings (task type, description, additional info)
     */
    public String[] extractTaskInfo(String inputLine) throws DukeException {
        String type;
        String description = "";
        String additionalInfo;
        int additionalInfoIndex = inputLine.length(); // Index where description should end

        // Extract the type of task
        type = inputLine.split("\\s")[0].trim();
        // If the task type is not a todo, deadline, or event, throw a DukeException
        if ((!type.equals("todo")) && (!type.equals("deadline")) && (!type.equals("event"))) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        // Extract the deadline (for Deadline tasks) or time (for Event tasks), if applicable
        if (type.equals("deadline")) {
            // If the task doesn't have a deadline, throw a DukeException
            if (!inputLine.contains("/by") || inputLine.endsWith("/by")) {
                throw new DukeException("OOPS!!! Task of type deadline must have a deadline specified.");
            }
            additionalInfo = inputLine.substring(inputLine.indexOf("/by") + 4);
            // If the task has an empty deadline, throw a DukeException
            if (additionalInfo.trim().isEmpty()) {
                throw new DukeException("OOPS!!! Type of task deadline must not have an empty deadline.");
            }
            additionalInfoIndex = inputLine.indexOf("/by");
        } else if (type.equals("event")) {
            // If the task is an event and doesn't have a time, throw a DukeException
            if (!inputLine.contains("/at") || inputLine.endsWith("/at")) {
                throw new DukeException("OOPS!!! Task of type event must have a time specified.");
            }
            additionalInfo = inputLine.substring(inputLine.indexOf("/at") + 4);
            // If the task has an empty time, throw a DukeException
            if (additionalInfo.trim().isEmpty()) {
                throw new DukeException("OOPS! Task of type event must not have an empty time.");
            }
            additionalInfoIndex = inputLine.indexOf("/at");
        } else {
            additionalInfo = "";
        }

        // Extract the description
        // If the description is empty, throw a DukeException
        try {
            description = inputLine.substring(type.length(), additionalInfoIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
        }
        if (description.trim().isEmpty()) {
            throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
        }
        return new String[] { type.trim(), description.trim(), additionalInfo.trim() };
    }
}
