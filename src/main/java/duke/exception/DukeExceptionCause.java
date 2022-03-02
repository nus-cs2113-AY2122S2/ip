package duke.exception;

/**
 * Represents the types of exceptions that is unique to the Duke Program.
 */
public enum DukeExceptionCause {
    INVALIDCOMMAND,
    EVENTTASKNAMEEMPTY,
    DEADLINETASKNAMEEMPTY,
    TODOTASKNAMEEMPTY,
    EMPTYTASKINDEX,
    TASKINDEXOUTOFRANGE,
    INVALIDTASKINDEX,
    FILECREATIONFAIL,
    FOLDERCREATIONFAIL,
    EMPTYKEYWORD
}
