package duke;

import duke.task.TaskList;

public class Parser {

    public Parser() {}

    public boolean isDeadline(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_DEADLINE_STATEMENT) {
            // return true if first 8 letters of userInput spell "deadline", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_DEADLINE_STATEMENT).equals("deadline");
        }
        return false;
    }

    public boolean isTodo(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT) {
            // return true if first 4 letters of userInput spell "todo", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT).equals("todo");
        }
        return false;
    }

    public boolean isEvent(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_EVENT_STATEMENT) {
            // return true if first 5 letters of userInput spell "event", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_EVENT_STATEMENT).equals("event");
        }
        return false;
    }

    public String getTodoFromUserInput(String userInput) {
        return userInput.substring(Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT + 1, userInput.length());
    }

    public String getEventFromUserInput(String userInput, int eventTimeIdx) {
        return userInput.substring(Duke.MINIMUM_LENGTH_OF_EVENT_STATEMENT + 1, eventTimeIdx - 1);
    }

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

    public boolean isInvalidTodo(String todo) {
        int lengthOfTodoDescription = todo.length() - Duke.MINIMUM_LENGTH_OF_TODO_STATEMENT - 1;
        return (lengthOfTodoDescription <= 0);
    }

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

    public StringPair getEventDescriptionAndTime(String userInput) {
        int eventTimeIdx = userInput.indexOf("/");
        String description = getEventFromUserInput(userInput, eventTimeIdx);
        String eventTime = getEventTimeFromUserInput(userInput, eventTimeIdx);
        return new StringPair(description, eventTime);
    }

    public StringPair getDeadlineDescriptionAndTime(String userInput) {
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

    public boolean isMarkCommand(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_MARK_STATEMENT) {
            // return true if first 4 letters of userInput spell "mark", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_MARK_STATEMENT).equals("mark");
        }
        return false;
    }

    public boolean isUnmarkCommand(String userInput) {
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_UNMARK_STATEMENT) {
            // return true if first 6 letters of userInput spell "unmark", else false
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_UNMARK_STATEMENT).equals("unmark");
        }
        return false;
    }

    public boolean isListCommand(String userInput) {
        return userInput.equals("list");
    }

    public boolean isDeleteCommand(String userInput) {
        // return true if command is list, else false
        if (userInput.length() >= Duke.MINIMUM_LENGTH_OF_DELETE_STATEMENT) {
            return userInput.substring(0, Duke.MINIMUM_LENGTH_OF_DELETE_STATEMENT).equals("delete");
        }
        return false;
    }

    public boolean isByeCommand(String userInput) {
        return userInput.equals("bye");
    }

    // method returns task number else -1 if any errors encountered.
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
