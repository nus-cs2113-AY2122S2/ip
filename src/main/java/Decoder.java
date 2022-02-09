public class Decoder {
    public static Command parseInput(String userInput) throws DukeException {
        Command newCommand;
        if (userInput.equals("bye")) {
            newCommand = new ExitProgramCommand();
        } else if (userInput.equals("list")) {
            newCommand = new PrintListCommand();
        } else if (userInput.startsWith("mark")) {
            newCommand = new UpdateTaskStatusCommand(userInput, true);
        } else if (userInput.startsWith("unmark")) {
            newCommand = new UpdateTaskStatusCommand(userInput, false);
        } else if (userInput.startsWith("deadline") || userInput.startsWith("event") || userInput.startsWith("todo")) {
            try {
                newCommand = new AddTaskCommand(userInput);
            } catch (DukeException de) {
                throw de;
            }
        } else {
            throw new DukeException(DukeExceptionCause.INVALIDCOMMAND);
        }
        return newCommand;
    }
}
