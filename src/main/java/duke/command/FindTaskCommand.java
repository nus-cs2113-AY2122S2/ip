package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;

/**
 * Converts the users input into a FindTaskCommand object.
 * A FindTaskCommand object contains the keyword the user is using to search the Task List.
 */
public class FindTaskCommand extends Command {
    String keyWord;

    public FindTaskCommand(String userInput) throws DukeException {
        super(CommandType.FindTaskCommand);
        setKeyWord(extractKeyword(userInput));
    }

    public String getKeyWord() {
        return keyWord;
    }

    private void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    private String extractKeyword(String userInput) throws DukeException {
        String keyWord = userInput.replace("find", "");
        keyWord = keyWord.trim();
        if (keyWord.isEmpty()) {
            throw new DukeException(DukeExceptionCause.EmptyKeyword);
        }
        return keyWord;
    }
}
