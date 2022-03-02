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
        super(CommandType.FINDTASKS);
        try {
            setKeyWord(extractKeyword(userInput));
        } catch (DukeException de) {
            throw de;
        }
    }

    public String getKeyWord() {
        return keyWord;
    }

    private void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    private String extractKeyword(String userInput) throws DukeException {
        String keyWord;
        userInput = userInput.replace("find", "");
        keyWord = trimInput(userInput);
        if (keyWord.isEmpty()) {
            throw new DukeException(DukeExceptionCause.EMPTYKEYWORD);
        }
        return keyWord;
    }

    private String trimInput(String userInput) {
        return userInput.trim();
    }
}
