package sora;

/**
 * This class handles the exceptions thrown by the various components in Sora.
 */
public class SoraExceptionHandler {
    // Allows SoraExceptionHandler to provide UI outputs to the user
    private SoraUI soraUI;

    /**
     * Constructs a SoraExceptionHandler object with an instance of SoraUI.
     *
     * @param soraUIObj An instance of SoraUI.
     */
    public SoraExceptionHandler(SoraUI soraUIObj) {
        this.soraUI = soraUIObj;
    }

    /**
     * Determines the type of InvalidCommandException that has been thrown and carries out the appropriate
     * actions.
     *
     * @param e The InvalidCommandException that has been thrown.
     */
    public void handleInvalidCommandException(InvalidCommandException e) {
        String errorMessage = e.getMessage();

        switch (errorMessage) {
        case InvalidCommandException.NO_SUCH_COMMAND_MSG:
            soraUI.printCommandNotUnderstood();
            break;
        case InvalidCommandException.TODO_NO_DESCRIPTION:
            soraUI.printTodoMissingDescription();
            break;
        case InvalidCommandException.EVENT_MISSING_FLAG:
            soraUI.printEventMissingFlag();
            break;
        case InvalidCommandException.EVENT_NO_DESCRIPTION:
            soraUI.printEventMissingDescription();
            break;
        case InvalidCommandException.EVENT_NO_PERIOD:
            soraUI.printEventMissingPeriod();
            break;
        case InvalidCommandException.EVENT_INVALID_FLAGS:
            soraUI.printEventInvalidFlags();
            break;
        case InvalidCommandException.DEADLINE_MISSING_FLAG:
            soraUI.printDeadlineMissingFlag();
            break;
        case InvalidCommandException.DEADLINE_NO_DESCRIPTION:
            soraUI.printDeadlineMissingDescription();
            break;
        case InvalidCommandException.DEADLINE_NO_DUE_DATE:
            soraUI.printDeadlineNoDueDate();
            break;
        case InvalidCommandException.DEADLINE_INVALID_FLAGS:
            soraUI.printDeadlineInvalidFlags();
            break;
        case InvalidCommandException.FIND_NO_SEARCH_STRING:
            soraUI.printMissingSearchStringResponse();
            break;
        default:
            // Unexpected error has occurred. Terminate the program to be safe.
            soraUI.printUncategorisedInvalidCommandExceptionMessage();
            System.exit(-1);
        }
    }

    /**
     * Handles situations where the user tries to reference a task that does not exist in Sora's task list.
     */
    public void handleOutOfRangeListReferences() {
        soraUI.printTaskNumOutOfListRange();
    }

    /**
     * Handles situations where the user has entered an illegal character.
     */
    public void handleIllegalCharacterException() {
        soraUI.printIllegalCharacterResponse();
    }

    /**
     * Handles situations where the user tries to interact with an empty list.
     */
    public void handleEmptyListException() {
        soraUI.printEmptyListExceptionResponse();
    }

    /**
     * Handles situations where the user references an uninterpretable task number.
     */
    public void handleInvalidTaskNumber() {
        soraUI.printInvalidTaskNumber();
    }

    /**
     * Handles situations where the user enters an invalid date-time format.
     */
    public void handleInvalidDateTimeInputFormat() {
        soraUI.printInvalidDateTimeInputFormatResponse();
    }
}
