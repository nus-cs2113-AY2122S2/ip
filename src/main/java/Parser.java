public class Parser {
    private String input;
    private String command;
    private boolean isExiting;

    Parser() {
        this.input = "";
        this.command = "";
        this.isExiting = false;
    }

    private void reset() {
        input = "";
        command = "";
        isExiting = false;
    }

    public void parseString(String userInput) {
        reset();
        input = userInput;
        setCommand();
        setParam(command);
    }

    public void setCommand() {
        this.command = input.split(" ")[0].trim().toLowerCase();
        // throw command invalid exception
    }

    private void setParam(String command) {
        if (command.equals("bye")) {
            isExiting = true;
        }
    }

    public boolean isExiting() {
        return isExiting;
    }

    public String getCommand() {
        return command;
    }

    public String[] getTaskDescription() {
        return splitStringBySlash();
    }

    public int getTaskId() {
        return Integer.parseInt(input.split(" ")[1]);
        //task id not integer
        //no task id
    }

    private String[] splitStringBySlash() {
        String[] splitInput = input.split(" ");
        String[] splitOutput = new String[2];
        String description = "";
        String date = "";
        boolean hasSlash = false;

        for (int i = 1; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by") || splitInput[i].equals("/at")) {
                hasSlash = true;
                continue;
            }
            if (hasSlash) {
                date += splitInput[i] + " ";
            } else {
                description += splitInput[i] + " ";
            }
        }

        splitOutput[0] = description.trim();
        splitOutput[1] = date.trim();
        return splitOutput;
    }
}
