import java.util.Locale;

public class OperationAnalyst {
    protected String[] keywords;
    protected String rawInput;
    protected String time;
    protected String taskName;
    protected String instruction;

    public OperationAnalyst(String input){
        this.keywords = input.split(" ");
        this.rawInput = input;
        this.instruction = keywords[0].toLowerCase(Locale.ROOT);
        parseInstruction();
    }

    /**
     * Computes the command for further operation, which is the first element in the keywords array
     * @return the command, whether it is deadline, todo, event, list, mark or unmark
     */
    public String getCommand() {
        return this.instruction;
    }

    /**
     * Analyses raw input to determine the task name and time
     */
    public void parseInstruction() {
        //String name;
        String[] command;
        switch (this.instruction) {
        case "deadline":
            command = rawInput.split("/by ");
            this.taskName = command[0].replace("deadline ", "");
            this.time = command[1];
            //System.out.println(this.time);
            break;
        case "event":
            command = rawInput.split("/at ");
            this.taskName = command[0].replace("event ", "");
            this.time = command[1];
            break;
        case "todo":
            this.taskName = rawInput.replace("todo ", "");
            break;
        case "mark":
            this.taskName = this.keywords[1];
            break;
        case "unmark":
            this.taskName = this.keywords[1];
            break;
        default:
            this.taskName = rawInput;
        }
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTime() {
        return this.time;
    }
}
