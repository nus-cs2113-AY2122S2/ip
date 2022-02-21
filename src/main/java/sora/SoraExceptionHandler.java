package sora;

/**
 * Focuses on handling exceptions generated by Sora's functions
 */
public class SoraExceptionHandler {
    // Allows SoraExceptionHandler to provide UI outputs to the user
    private SoraUI soraUI;

    public SoraExceptionHandler(SoraUI soraUIObj) {
        this.soraUI = soraUIObj;
    }

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
        }
    }

    public void handleOutOfRangeListReferences() {
        soraUI.printTaskNumOutOfListRange();
    }

    public void handleIllegalCharacterException() {
        soraUI.printIllegalCharacterResponse();
    }

    public void handleEmptyListException() {
        soraUI.printEmptyListExceptionResponse();
    }
}
