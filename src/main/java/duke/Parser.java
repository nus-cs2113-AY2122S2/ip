package duke;

import duke.task.TaskList;

/**
 * This class handles I/O interactions with the user. It comprises mainly of methods that print to command line
 * or receive user input from command line.
 */
public class Parser {

    public Parser() {}

    /**
     * This method returns a boolean determining whether user input command starts with the string "deadline".
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if user input starts with "deadline", else <code>false</code>.
     */
    public boolean isDeadline(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_DEADLINE_STATEMENT) {
            // return true if first 8 letters of userInput spell "deadline", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_DEADLINE_STATEMENT).equals("deadline");
        }
        return false;
    }

    /**
     * This method returns a boolean determining whether user input command starts with the string "todo".
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if user input starts with "todo", else <code>false</code>.
     */
    public boolean isTodo(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT) {
            // return true if first 4 letters of userInput spell "todo", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT).equals("todo");
        }
        return false;
    }

    /**
     * This method returns a boolean determining whether user input command starts with the string "event".
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if user input starts with "event", else <code>false</code>.
     */
    public boolean isEvent(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_EVENT_STATEMENT) {
            // return true if first 5 letters of userInput spell "event", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_EVENT_STATEMENT).equals("event");
        }
        return false;
    }

    /**
     * This method returns the todo description from todo input.
     *
     * @param userInput Contains the user input todo command.
     * @return Todo description.
     */
    public String getTodoFromUserInput(String userInput) {
        return userInput.substring(Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT + 1, userInput.length());
    }

    /**
     * This method returns the event description from event input.
     *
     * @param userInput Contains the user input event command.
     * @param eventTimeIdx Index before which the description ends.
     * @return Event description.
     */
    public String getEventFromUserInput(String userInput, int eventTimeIdx) {
        return userInput.substring(Duke.MINIMUM_LENGTH_OF_EVENT_STATEMENT + 1, eventTimeIdx - 1);
    }

    /**
     * This method returns the deadline description from deadline input.
     *
     * @param userInput Contains the user input deadline command.
     * @param dueDateIdx Index before which the description ends.
     * @return Deadline description.
     */
    public String getDeadlineFromUserInput(String userInput, int dueDateIdx) {
        return userInput.substring(Duke.MINIMUM_LENGTH_OF_DEADLINE_STATEMENT + 1, dueDateIdx - 1);
    }

    public String getEventTimeFromUserInput(String userInput, int eventTimeIdx) {
        // ignore /by in the userInput
        return userInput.substring(eventTimeIdx + Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT, userInput.length());
    }


    public String getDueDateFromUserInput(String userInput, int dueDateIdx) {
        // ignore /at in the userInput
        return userInput.substring(dueDateIdx + Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT, userInput.length());
    }

    /**
     * This method returns a boolean determining whether a todo input command is valid or not.
     *
     * @param todo String containing todo command.
     * @return <code>true</code> if todo is invalid, else <code>false</code>.
     */
    public boolean isInvalidTodo(String todo) {
        int lengthOfTodoDescription = todo.length() - Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT - 1;
        return (lengthOfTodoDescription <= 0);
    }

    /**
     * This method returns a boolean determining whether an event input command is valid or not.
     *
     * @param event String containing event command.
     * @return <code>true</code> if event is invalid, else <code>false</code>.
     */
    public boolean isInvalidEvent(String event) {
        int lengthOfEventDescription = event.length() - Duke.MINIMUM_LENGTH_OF_EVENT_STATEMENT - 1;
        if (lengthOfEventDescription <= 0) {
            return true;
        }
        // invalid format
        if (event.indexOf("/") == -1) {
            return true;
        }

        // get string components after "/" in the event input.
        String[] eventTimeSplit = event.substring(event.indexOf("/"), event.length()).split(" ");

        // invalid format or event time is not specified.
        if (eventTimeSplit.length < 2 || !(eventTimeSplit[0].equals("/at"))) {
            return true;
        }
        return false;
    }

    /**
     * This method returns a boolean determining whether a deadline input command is valid or not.
     *
     * @param deadline String containing deadline command.
     * @return <code>true</code> if deadline is invalid, else <code>false</code>.
     */
    public boolean isInvalidDeadline(String deadline) {
        int lengthOfDeadlineDescription = deadline.length() - Duke.MINIMUM_LENGTH_OF_DEADLINE_STATEMENT - 1;
        if (lengthOfDeadlineDescription <= 0) {
            return true;
        }
        // invalid format
        if (deadline.indexOf("/") == -1) {
            return true;
        }

        // get string components after "/" in the deadline input.
        String[] dueDateSplit = deadline.substring(deadline.indexOf("/"), deadline.length()).split(" ");

        // invalid format or due date is not specified.
        if (dueDateSplit.length < 2 || !(dueDateSplit[0].equals("/by"))) {
            return true;
        }
        return false;
    }

    /**
     * This method returns a string describing the todo task stored in the user input.
     *
     * @param userInput String containing user input.
     * @return String containing todo description.
     */
    public String getTodoDescription(String userInput) {
        return getTodoFromUserInput(userInput);
    }



    public String getKeyword(String userInput) {
        String[] inputSplit = userInput.split(" ");
        if (inputSplit.length <= 1){
            return "";
        }
        return inputSplit[1];
    }

    /**
     * This method returns a String Pair representing the event description and corresponding event time
     * stored in the user input.
     *
     * @param userInput String containing user input.
     * @return A pair of strings representing the event description and corresponding event time.
     */
    public StringPair getEventDescriptionAndTime(String userInput) {
        int eventTimeIdx = userInput.indexOf("/");
        String description = getEventFromUserInput(userInput, eventTimeIdx);
        String eventTime = getEventTimeFromUserInput(userInput, eventTimeIdx);
        return new StringPair(description, eventTime);
    }


    /**
     * This method returns a String Pair representing the deadline description and corresponding due date.
     *
     * @param userInput String containing user input.
     * @return A pair of strings representing the deadline description and corresponding due date.
     */
    public StringPair getDeadlineDescriptionAndTime(String userInput){
        int dueDateIdx = userInput.indexOf("/");
        String description = getDeadlineFromUserInput(userInput, dueDateIdx);
        String dueDate = getDueDateFromUserInput(userInput, dueDateIdx);
        return new StringPair(description, dueDate);
    }



    public boolean isFindCommand(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_FIND_STATEMENT) {
            // return true if first 4 letters of userInput spell "mark", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_FIND_STATEMENT).equals("find");
        }
        return false;
    }

    /**
     * This method checks whether <code>userInput</code> is a "mark" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "mark" command else <code>false</code>.
     */
    public boolean isMarkCommand(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_MARK_STATEMENT) {
            // return true if first 4 letters of userInput spell "mark", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_MARK_STATEMENT).equals("mark");
        }
        return false;
    }

    /**
     * This method checks whether <code>userInput</code> is a "unmark" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is an "unmark" command else <code>false</code>.
     */
    public boolean isUnmarkCommand(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_UNMARK_STATEMENT) {
            // return true if first 6 letters of userInput spell "unmark", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_UNMARK_STATEMENT).equals("unmark");
        }
        return false;
    }

    /**
     * This method checks whether <code>userInput</code> is a "list command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "list" command else <code>false</code>.
     */
    public boolean isListCommand(String userInput) {
        return userInput.equals("list");
    }

    /**
     * This method checks whether <code>userInput</code> is a "delete" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "delete" command else <code>false</code>.
     */
    public boolean isDeleteCommand(String userInput) {
        // return true if command is list, else false
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_DELETE_STATEMENT) {
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_DELETE_STATEMENT).equals("delete");
        }
        return false;
    }

    /**
     * This method checks whether <code>userInput</code> is a "bye" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "bye" command else <code>false</code>.
     */
    public boolean isByeCommand(String userInput) {
        return userInput.equals("bye");
    }

    /**
     * This method returns task number else -1 if any errors are encountered.
     *
     * @param taskList ArrayList containing tasks.
     * @param userInput String containing user input.
     * @return Task number extracted from <code>userInput</code>.
     */
    public int getTaskNumber(TaskList taskList, String userInput) {
        if (taskList.getTaskCount() == 0) {
            return Duke.INVALID_TASKNUMBER;
        }
        String[] splitInput = userInput.split(" ");
        if (splitInput.length <= 1) {
            return Duke.INVALID_TASKNUMBER;
        }
        try {
            int taskNumber = Integer.parseInt(splitInput[1]);
            if (taskNumber> taskList.getTaskCount()||taskNumber<1){
                return Duke.INVALID_TASKNUMBER;
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            return Duke.INVALID_TASKNUMBER;
        }
    }
}
