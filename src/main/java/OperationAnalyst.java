import java.util.Locale;

public class OperationAnalyst {
    protected String[] keywords;
    protected String rawInput;
    protected String time;
    protected String taskName;
    protected String instruction;

    public OperationAnalyst(String input) throws DukeException {
        this.keywords = input.split(" ");
        this.rawInput = input;
        this.instruction = keywords[0].toLowerCase(Locale.ROOT);
        //System.out.println(this.instruction);
        parseInstruction();
        //System.out.println(this.instruction);
        //System.out.println(this.taskName);
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
    public void parseInstruction() throws DukeException{
        //String name;
        String[] command;
        switch (this.instruction) {
        case "deadline":
            command = rawInput.split("/by ");
            this.taskName = command[0].replace("deadline ", "");
            try {
                this.time = command[1];
            } catch (IndexOutOfBoundsException e){
                throw new IllegalFormatException();
            }
            //System.out.println(this.time);
            break;
        case "event":
            command = rawInput.split("/at ");
            this.taskName = command[0].replace("event ", "");
            try {
                this.time = command[1];
            } catch (IndexOutOfBoundsException e){
                throw new IllegalFormatException();
            }
            break;
        case "todo":
            this.taskName = rawInput.replace("todo ", "");
            break;
        case "mark":
        case "unmark":
        case "delete":
            try {
                this.taskName = this.keywords[1];
            } catch (IndexOutOfBoundsException e){
                throw new IllegalFormatException();
            }
            break;
        case "list" :
            break;
        case "bye" :
            break;
        default:
            this.instruction = "unknown";
            throw new IllegalInstructionException();
        }
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTime() {
        return this.time;
    }
}
