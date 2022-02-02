public class Decoder {
    public static Command parseInput(String userInput) {
        Command newCommand;
        if (userInput.equals("bye")) {
            newCommand = new ExitProgramCommand();
        } else if (userInput.equals("list")) {
            newCommand = new PrintListCommand();
        } else if (userInput.startsWith("mark")) {
            newCommand = new UpdateTaskStatusCommand(userInput, true);
        } else if (userInput.startsWith("unmark")) {
            newCommand = new UpdateTaskStatusCommand(userInput, false);
        } else {
            newCommand = new AddTaskCommand(userInput);
        }
        return newCommand;
    }
}
