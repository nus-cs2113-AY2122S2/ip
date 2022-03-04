package Duke.Parser;

public class Parser {
    private final String inputCommand;
    public Parser(String inputCommand){
        this.inputCommand = inputCommand;
    }
    public String parseCommand(){

        int commandSeparator = inputCommand.indexOf(' ');
        if (commandSeparator == -1) {
            return inputCommand;
        } else {
            return inputCommand.substring(0, commandSeparator);
        }
   }

   public String parseTime(String input){
       int timeSeparator = input.indexOf('/');
       return input.substring(timeSeparator + 4);
   }
   public String parseTask(String  input){
       int commandSeparator = input.indexOf(' ');
       return input.substring(commandSeparator + 1);
   }
   public String parseTask(String input, int timeSeparator){
       int commandSeparator = input.indexOf(' ');
       return input.substring(commandSeparator + 1,timeSeparator);
   }
}
