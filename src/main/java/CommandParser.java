/**
 * Identifies the type of command passed and based on the
 * command input and decide what type of command to return.
 */
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

    /**
     * Identify and return type of task user inputs.
     *
     * @param commandString input entered by user.
     * @return action specify by user.
     * @throws DukeWrongCommandException if wish is invalid.
     */
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

    /**
     * Filters descriptions from user to derived the wish number user has
     * input for deleting, ticking, un-ticking wishes.
     *
     * @param commandString input entered by user.
     * @param wishType      to derive delete, tick, un-tick word length for filtering.
     * @return choice number.
     * @throws DukeWrongCommandException if choice is invalid.
     */
    private int getChoiceNumber(String commandString, String wishType) throws DukeWrongCommandException {
        String choice = commandString.substring(wishType.length()).trim();
        try {
            return Integer.parseInt(choice) - 1;
        } catch (NumberFormatException numError) {
            throw new DukeWrongCommandException("Invalid choice of wish");
        }
    }

    /**
     * Method to derive description entered.
     *
     * @param input   input entered by user.
     * @param command type of wish command.
     * @return description relevant for processing.
     * @throws DukeEmptyStringException if description was not entered/empty.
     */
    public String deriveDescription(String input, String command) throws DukeEmptyStringException {
        String description = input.substring(input.indexOf(command) + command.length());
        description = description.trim();
        if (description.isEmpty()) {
            throw new DukeEmptyStringException();
        }
        return description;
    }

    /**
     * Method to derive time or date needed for a specific wish task.
     *
     * @param input      stores wish task that either need to done or end
     *                   before specific date/time.
     * @param indication indicates if it is from deadline or event wish.
     * @return date/time found.
     */
    public String deriveTimeDate(String input, char indication) {
        int getTimeDateIndex = input.indexOf(indication) + 1;
        String storeTimeDate = input.substring(getTimeDateIndex);
        storeTimeDate = storeTimeDate.trim();
        return storeTimeDate;
    }

    /**
     * Method to derive wish task words user wishes to find.
     *
     * @param userInput input entered by user.
     * @return FindCommand to execute finding wish task.
     * @throws DukeWrongCommandException when description entered by user is empty.
     */
    private FindCommand findTask(String userInput) throws DukeWrongCommandException {
        String description = "";
        try {
            description = deriveDescription(userInput, FIND).toLowerCase();
        } catch (DukeEmptyStringException error) {
            throw new DukeWrongCommandException("The search input for wish: cannot be empty");
        }
        return new FindCommand(description);
    }

    /**
     * Method to derive wish task "event" which will be added into tasklist.
     *
     * @param userInput input entered by user.
     * @return CreateTaskCommand to add new event in tasklist.
     * @throws DukeWrongCommandException when description entered by user is empty or wrong.
     */
    private CreateTaskCommand addEvent(String userInput) throws DukeWrongCommandException {
        String description = "";
        try {
            description = deriveDescription(userInput, EVENT);
        } catch (DukeEmptyStringException error) {
            throw new DukeWrongCommandException("The description for event: cannot be empty");
        }
        String eventAt = deriveTimeDate(description, INDICATE_EVENT).trim();
        if (userInput.replace(EVENT, "").trim().indexOf(INDICATE_EVENT) == 0) {
            throw new DukeWrongCommandException("Incorrect event usage -> 'event: sing @ 3pm'");
        }
        try {
            description = description.substring(0, description.indexOf(INDICATE_EVENT)).trim();
        } catch (StringIndexOutOfBoundsException error) {
            throw new DukeWrongCommandException("Incorrect event usage -> 'event: sing @ 3pm'");
        }
        return new CreateTaskCommand("E", description, eventAt);
    }

    /**
     * Method to derive wish task "Deadline" which will be added into tasklist.
     *
     * @param userInput input entered by user.
     * @return CreateTaskCommand to add new deadline in tasklist.
     * @throws DukeWrongCommandException when description entered by user is empty or wrong.
     */
    private CreateTaskCommand addDeadline(String userInput) throws DukeWrongCommandException {
        String description = "";
        try {
            description = deriveDescription(userInput, "deadline:");
        } catch (DukeEmptyStringException error) {
            throw new DukeWrongCommandException("The description for deadline: cannot be empty");
        }
        String deadlineBy = deriveTimeDate(description, INDICATE_DEADLINE).trim();
        if (userInput.replace(DEADLINE, "").trim().indexOf(INDICATE_DEADLINE) == 0) {
            throw new DukeWrongCommandException("Incorrect deadline usage -> 'deadline: homework ~ 3pm'");
        }
        try {
            description = description.substring(0, description.indexOf(INDICATE_DEADLINE)).trim();
        } catch (StringIndexOutOfBoundsException error) {
            throw new DukeWrongCommandException("Incorrect deadline usage -> 'deadline: homework ~ 3pm'");
        }
        return new CreateTaskCommand("D", description, deadlineBy);
    }

    /**
     * Method to derive wish task "Todo" which will be added into tasklist.
     *
     * @param userInput input entered by user.
     * @return CreateTaskCommand to add new todo in tasklist.
     * @throws DukeWrongCommandException when description entered by user is empty.
     */
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
