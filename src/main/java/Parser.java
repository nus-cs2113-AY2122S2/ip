public class Parser {

    public static String parseCommand(String userInput) {
        String command = userInput.trim();
        try {
            if (command.isEmpty()) {
                throw new DukeException(Ui.EMPTY_INPUT_MESSAGE);
            }
            command = userInput.split(" ")[0];
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.EMPTY_INPUT_MESSAGE);
        }
        return command;
    }

    public static int parseTaskIndex(String userInput) {

        int index = 0;

        try {
            index = Integer.parseInt(
                    userInput.substring(userInput.indexOf(" ") + 1)) - 1;
            if (index < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println(Ui.ILLEGAL_INDEX_MESSAGE);
        }
        return index;
    }

    public static String parseDate(String separator, String userInput) {

        String date = "";

        try {
            date = userInput.substring(userInput.indexOf(separator) + 4);
            date = date.trim();
            if (date.isEmpty()) {
                throw new DukeException(Ui.EMPTY_DATE_MESSAGE);
            }
        } catch (StringIndexOutOfBoundsException e) {
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static String parseDescription(String separator, String userInput, String taskType) {

        String description = "";

        try {
            if (taskType.equalsIgnoreCase(TaskManager.TODO_COMMAND) ||
                    taskType.equalsIgnoreCase(Command.FIND_COMMAND)) {
                description = userInput.substring(userInput.indexOf(" ") + 1);
            }
            else {
                description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(separator));
            }
            description = description.trim();

            if (description.isEmpty()) {
                throw new DukeException(Ui.EMPTY_DESCRIPTION_MESSAGE);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
        }
        return description;
    }

    public static String parseSavedTaskType(String data) {
        String taskType = "";
        try {
            taskType = data.substring(data.indexOf("[") + 1, data.indexOf("]"));
            if (taskType.trim().isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.LOAD_FAILED_MESSAGE);
        }
        return taskType;
    }

    public static String parseSavedTaskDescription(String data, String separator, String taskType) {
        String description = "";
        try {
            switch (taskType) {
            case "T":
                description = data.substring(7);
                break;
            case "D":
            case "E":
                description = data.substring(7, data.indexOf(separator));
                break;
            default:
                throw new DukeException(Ui.LOAD_FAILED_MESSAGE);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return description;
    }

    public static String parseSavedTaskDate(String data, String separator) {
        String date = "";
        try {
            date = data.substring(data.indexOf(separator) + 5, data.indexOf(")"));
            if (date.trim().isEmpty()) {
                throw new DukeException(Ui.LOAD_FAILED_MESSAGE);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }


}
