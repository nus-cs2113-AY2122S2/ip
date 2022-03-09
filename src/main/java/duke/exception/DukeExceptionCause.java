package duke.exception;

/**
 * Represents the types of exceptions that is unique to the Duke Program.
 */
public enum DukeExceptionCause {
    InvalidCommand, EventTaskNameEmpty, DeadlineTaskNameEmpty, ToDoTaskNameEmpty, EmptyTaskIndex, TaskIndexOutOfRange,
    InvalidTaskIndex, FileCreationFail, FolderCreationFail, EmptyKeyword
}
