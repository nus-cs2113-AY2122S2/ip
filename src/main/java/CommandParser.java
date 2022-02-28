public class CommandParser {
    static final public String BYE = "bye";
    static final public String LIST = "list";
    static final public String UNTICK = "untick";
    static final public String TICK = "tick";
    static final public String DELETE = "delete";
    static final public String TODO = "todo:";
    static final public String DEADLINE = "deadline:";
    static final public String EVENT = "event:";
    static final public String FIND = "find";
    private final char INDICATE_DEADLINE = '~';
    private final char INDICATE_EVENT = '@';

    public Command parse(String commandString) throws DukeWrongCommandException {
        if (commandString.equals(BYE)) {
            return new ExitCommand();
        } else if (commandString.equals(LIST)) {
            return new ListTaskCommand();
        } else if (commandString.startsWith(UNTICK)) {
            int choiceNumber = getChoiceNumber(commandString, UNTICK);
            return new CompleteTaskCommand(false, choiceNumber);
        } else if (commandString.startsWith(TICK)) {
            int choiceNumber = getChoiceNumber(commandString, TICK);
            return new CompleteTaskCommand(true, choiceNumber);
        } else if (commandString.startsWith(DELETE)) {
            int choiceNumber = getChoiceNumber(commandString, DELETE);
            return new DeleteCommand(choiceNumber);
        } else if (commandString.startsWith(TODO)) {
            return addTodo(commandString);
        } else if (commandString.startsWith(DEADLINE)) {
            return addDeadline(commandString);
        } else if (commandString.startsWith(EVENT)) {
            return addEvent(commandString);
        } else if (commandString.startsWith((FIND))) {
            return findTask(commandString);
        }
        throw new DukeWrongCommandException("Invalid Wish Command");
    }

    private int getChoiceNumber(String commandString, String wishType) throws DukeWrongCommandException {
        String choice = commandString.substring(wishType.length()).trim();
        try {
            return Integer.parseInt(choice) - 1;
        } catch (NumberFormatException numError) {
            throw new DukeWrongCommandException("Invalid choice of wish");
        }
    }

    public String deriveDescription(String input, String command) throws DukeEmptyStringException {
        String description = input.substring(input.indexOf(command) + command.length());
        description = description.trim();
        if (description.isEmpty()) {
            throw new DukeEmptyStringException();
        }
        return description;
    }

    public String deriveTimeDate(String input, char indication) {
        String storeTimeDate = input.substring(input.indexOf(indication) + 1);
        storeTimeDate = storeTimeDate.trim();
        return storeTimeDate;
    }

    private FindCommand findTask(String userInput) throws DukeWrongCommandException {
        String description = "";
        try {
            description = deriveDescription(userInput, FIND);
        } catch (DukeEmptyStringException e) {
            throw new DukeWrongCommandException("The search input for wish: cannot be empty");
        }
        return new FindCommand(description);
    }

    private CreateTaskCommand addEvent(String userInput) throws DukeWrongCommandException {
        String description = "";
        try {
            description = deriveDescription(userInput, EVENT);
        } catch (DukeEmptyStringException e) {
            throw new DukeWrongCommandException("The description for event: cannot be empty");
        }
        String eventAt = deriveTimeDate(description, INDICATE_EVENT).trim();
        try {
            description = description.substring(0, description.indexOf(INDICATE_EVENT)).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeWrongCommandException("Incorrect event usage -> 'event: sing @ 3pm'");
        }
        return new CreateTaskCommand("E", description, eventAt);
    }

    private CreateTaskCommand addDeadline(String userInput) throws DukeWrongCommandException {
        String description = "";
        try {
            description = deriveDescription(userInput, "deadline:");
        } catch (DukeEmptyStringException error) {
            throw new DukeWrongCommandException("The description for deadline: cannot be empty");
        }
        String deadlineBy = deriveTimeDate(description, INDICATE_DEADLINE).trim();
        try {
            description = description.substring(0, description.indexOf(INDICATE_DEADLINE)).trim();
        } catch (StringIndexOutOfBoundsException error) {
            throw new DukeWrongCommandException("Incorrect deadline usage -> 'deadline: homework ~ 3pm'");
        }
        return new CreateTaskCommand("D", description, deadlineBy);
    }

    private CreateTaskCommand addTodo(String userInput) throws DukeWrongCommandException {
        String description = "";
        try {
            description = deriveDescription(userInput, "todo:").trim();
        } catch (DukeEmptyStringException error) {
            throw new DukeWrongCommandException("The description for todo: cannot be empty");
        }
        return new CreateTaskCommand("T", description);
    }
}
