public class Parser {
    private String inputCommand;
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
       String time = input.substring(timeSeparator + 4);
       return time;
   }
   public String parseTask(String  input){
       int commandSeparator = input.indexOf(' ');
       String task = input.substring(commandSeparator + 1);
       return task;
   }
   public String parseTask(String input, int timeSeparator){
       int commandSeparator = input.indexOf(' ');
       String task = input.substring(commandSeparator + 1,timeSeparator);
       return task;
   }
}
